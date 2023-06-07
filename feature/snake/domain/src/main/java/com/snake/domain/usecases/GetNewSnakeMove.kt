package com.snake.domain.usecases

import com.snake.domain.models.ActionType
import com.snake.domain.models.Food
import com.snake.domain.models.SnakeAction
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

const val BOARD_SIZE = 16

class GetNewSnakeMove {

    private val mutex = Mutex()

    suspend operator fun invoke(food: Food, snakeBody: List<Food>, move: Pair<Int, Int>): SnakeAction {
        val newSnakeHeadPosition = snakeBody.first().let { body ->
            mutex.withLock {
                getSnakeHeadNewPosition(body = body, move = move)
            }
        }

        return when {
            snakeEatFruit(food = food, snakeHead = newSnakeHeadPosition) -> {
                SnakeAction(
                    points = food.type.points,
                    body = food,
                    actionType = ActionType.EAT,
                )
            }
            snakeCrash(snakeBody = snakeBody, snakeHead = newSnakeHeadPosition) -> {
                SnakeAction(
                    body = newSnakeHeadPosition,
                    actionType = ActionType.CRASH,
                )
            }
            else -> {
                SnakeAction(
                    body = newSnakeHeadPosition,
                    actionType = ActionType.MOVE,
                )
            }
        }
    }

    private fun snakeCrash(snakeBody: List<Food>, snakeHead: Food): Boolean {
        return (snakeBody.map { it.position }.contains(snakeHead.position))
    }

    private fun snakeEatFruit(food: Food, snakeHead: Food): Boolean {
        return (snakeHead.position == food.position)
    }

    private fun getSnakeHeadNewPosition(body: Food, move: Pair<Int, Int>): Food {
        return Food(
            position = Pair(
                (body.position.first + move.first + BOARD_SIZE) % BOARD_SIZE,
                (body.position.second + move.second + BOARD_SIZE) % BOARD_SIZE,
            ),
            type = body.type,
        )
    }
}

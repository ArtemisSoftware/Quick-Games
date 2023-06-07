package com.snake.presentation

import androidx.lifecycle.viewModelScope
import com.snake.domain.models.ActionType
import com.snake.domain.models.Food
import com.snake.domain.models.FoodType
import com.snake.domain.models.Settings
import com.snake.domain.usecases.GetGameSettingsUseCase
import com.snake.domain.usecases.GetNewSnakeMove
import com.snake.presentation.composables.BOARD_SIZE
import com.snake.presentation.composables.UiViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import java.util.Random
import javax.inject.Inject

@HiltViewModel
class SnakeViewModel @Inject constructor(
    // private val getGameSettingsUseCase: GetGameSettingsUseCase = GetGameSettingsUseCase(),
) : UiViewModel() {
    private val getGameSettingsUseCase: GetGameSettingsUseCase = GetGameSettingsUseCase()

    private val _state = MutableStateFlow(
        SnakeState(
            food = Food(position = Pair(5, 5), type = FoodType.FRUIT),
            snake = listOf(Food(position = Pair(7, 7), type = FoodType.FRUIT)),
        ),
    )
    val state = _state.asStateFlow()

    private var settings: Settings = getGameSettingsUseCase()
    private var speed = settings.speed
    private var snakeLength = settings.initialSnakeSize
    private var score = 0

    private val mutex = Mutex()

    private var move = Pair(1, 0)
        set(value) {
            viewModelScope.launch {
                mutex.withLock {
                    field = value
                }
            }
        }

    init {
        // updateGame()
        setupGame()
        playGame()
    }

    fun onTriggerEvent(event: SnakeEvents) {
        when (event) {
            is SnakeEvents.MoveSnake -> {
                move = event.position
            }
        }
    }

    private fun setupGame() {
        val settings = GetGameSettingsUseCase().invoke()

        _state.update {
            it.copy(
                food = Food(position = Pair(5, 5), type = FoodType.FRUIT),
                snake = listOf(Food(position = Pair(7, 7), type = FoodType.FRUIT)),
                length = settings.initialSnakeSize,
                isPlaying = true,
            )
        }
    }

    private fun playGame() {
        viewModelScope.launch {
            while (_state.value.isPlaying) {
                delay(speed)
                _state.update {
                    val snakeAction = GetNewSnakeMove().invoke(food = it.food, snakeBody = it.snake, move = move)
                    val snakeBody = listOf(snakeAction.body) + it.snake.take(it.length - 1)

                    it.copy(
                        food = getFoodPosition_(food = it.food, snakeBody = snakeBody),
                        snake = snakeBody,
                        score = it.score + snakeAction.points,
                        length = when (snakeAction.actionType) {
                            ActionType.CRASH, ActionType.MOVE -> it.length
                            ActionType.EAT -> it.length + 1
                        },
                        isPlaying = !(snakeAction.actionType == ActionType.CRASH),
                    )
                }
            }
        }
    }

    private fun getFoodPosition_(food: Food, snakeBody: List<Food>): Food {
        var invalidFood = true
        var possibleFood = food
        val snakePositions = snakeBody.map { it.position }

        while (invalidFood) {
            if (snakePositions.contains(possibleFood.position)) {
                possibleFood = Food(
                    position = Pair(
                        Random().nextInt(BOARD_SIZE),
                        Random().nextInt(BOARD_SIZE),
                    ),
                    type = FoodType.values().random(),
                )
            } else {
                invalidFood = false
            }
        }

        return possibleFood
    }

    private fun updateGame() {
        viewModelScope.launch {
            while (true) {
                delay(speed)
                _state.update {
                    val newSnakePosition = it.snake.first().let { body ->
                        mutex.withLock {
                            getSnakeNewPosition(body = body)
                        }
                    }

                    val finalPosition = checkSnakeEatFruit(food = it.food, newFood = newSnakePosition)
                    checkSnakeCrash(snake = it.snake.map { bodyPosition -> bodyPosition.position }, newFood = newSnakePosition)

                    it.copy(
                        food = getFoodPosition(food = it.food, newFood = newSnakePosition),
                        snake = listOf(finalPosition) + it.snake.take(snakeLength - 1),
                        score = score,
                    )
                }
            }
        }
    }

    private fun getSnakeNewPosition(body: Food): Food {
        return Food(
            position = Pair(
                (body.position.first + move.first + BOARD_SIZE) % BOARD_SIZE,
                (body.position.second + move.second + BOARD_SIZE) % BOARD_SIZE,
            ),
            type = body.type,
        )
    }

    private fun getFoodPosition(food: Food, newFood: Food): Food {
        return if (newFood.position == food.position) {
            Food(
                position = Pair(
                    Random().nextInt(BOARD_SIZE),
                    Random().nextInt(BOARD_SIZE),
                ),
                type = FoodType.values().random(),
            )
        } else {
            food
        }
    }

    private fun checkSnakeEatFruit(food: Food, newFood: Food): Food {
        return if (newFood.position == food.position) {
            snakeLength++
            score += food.type.points
            food
        } else {
            newFood
        }
    }

    private fun checkSnakeCrash(snake: List<Pair<Int, Int>>, newFood: Food) {
        if (snake.contains(newFood.position)) {
            snakeLength = settings.initialSnakeSize
        }
    }
}

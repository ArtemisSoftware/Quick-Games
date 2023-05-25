package com.snake.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.snake.presentation.composables.BOARD_SIZE
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import java.util.Random

class SnakeViewModel constructor() : ViewModel() {

    private val _state = MutableStateFlow(SnakeState(food = Pair(5, 5), snake = listOf(Pair(7, 7))))
    val state = _state.asStateFlow()

    private val snakeLengthDefault = 1 // TODO: must come from data store
    private var snakeLength = snakeLengthDefault

    private val speed = 350L

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
        updateGame()
    }

    fun onTriggerEvent(event: SnakeEvents) {
        when (event) {
            is SnakeEvents.MoveSnake -> {
                move = event.position
            }
        }
    }

    private fun updateGame() {
        viewModelScope.launch {
            while (true) {
                delay(speed)
                _state.update {
                    val newPosition = it.snake.first().let { position ->
                        mutex.withLock {
                            getSnakeNewPosition(position = position)
                        }
                    }

                    checkSnakeEatFruit(food = it.food, newPosition = newPosition)
                    checkSnakeCrash(snake = it.snake, newPosition = newPosition)

                    it.copy(
                        food = getFoodPosition(foodPosition = it.food, newPosition = newPosition),
                        snake = listOf(newPosition) + it.snake.take(snakeLength - 1),
                    )
                }
            }
        }
    }

    private fun getSnakeNewPosition(position: Pair<Int, Int>): Pair<Int, Int> {
        return Pair(
            (position.first + move.first + BOARD_SIZE) % BOARD_SIZE,
            (position.second + move.second + BOARD_SIZE) % BOARD_SIZE,
        )
    }

    private fun getFoodPosition(foodPosition: Pair<Int, Int>, newPosition: Pair<Int, Int>): Pair<Int, Int> {
        return if (newPosition == foodPosition) {
            Pair(
                Random().nextInt(BOARD_SIZE),
                Random().nextInt(BOARD_SIZE),
            )
        } else {
            foodPosition
        }
    }

    private fun checkSnakeEatFruit(food: Pair<Int, Int>, newPosition: Pair<Int, Int>) {
        if (newPosition == food) {
            snakeLength++
        }
    }

    private fun checkSnakeCrash(snake: List<Pair<Int, Int>>, newPosition: Pair<Int, Int>) {
        if (snake.contains(newPosition)) {
            snakeLength = snakeLengthDefault
        }
    }
}

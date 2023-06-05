package com.snake.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.snake.domain.models.Food
import com.snake.domain.models.FoodType
import com.snake.domain.models.Settings
import com.snake.domain.usecases.GetGameSettingsUseCase
import com.snake.presentation.composables.BOARD_SIZE
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import java.util.Random

class SnakeViewModel constructor(
    private val getGameSettingsUseCase: GetGameSettingsUseCase = GetGameSettingsUseCase()
) : ViewModel() {

    private val _state = MutableStateFlow(
        SnakeState(
            food = Food(position = Pair(5, 5), type = FoodType.FRUIT),
            snake = listOf(Pair(7, 7))
        )
    )
    val state = _state.asStateFlow()

    private val snakeLengthDefault = 1 // TODO: must come from data store
    private var snakeLength = snakeLengthDefault

    private var settings: Settings = getGameSettingsUseCase()
    private var speed = settings.speed

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
                        food = getFoodPosition(food = it.food, newPosition = newPosition),
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

    private fun getFoodPosition(food: Food, newPosition: Pair<Int, Int>): Food {
        return if (newPosition == food.position) {
            Food(
                position = Pair(
                    Random().nextInt(BOARD_SIZE),
                    Random().nextInt(BOARD_SIZE),
                ),
                type = FoodType.values().random()
            )
        } else {
            food
        }
    }

    private fun checkSnakeEatFruit(food: Food, newPosition: Pair<Int, Int>) {
        if (newPosition == food.position) {
            snakeLength++
        }
    }

    private fun checkSnakeCrash(snake: List<Pair<Int, Int>>, newPosition: Pair<Int, Int>) {
        if (snake.contains(newPosition)) {
            snakeLength = settings.initialSnakeSize
        }
    }
}

package com.snake.presentation

import com.snake.domain.models.Food

data class SnakeState(
    val food: Food,
    val snake: List<Pair<Int, Int>>,
)

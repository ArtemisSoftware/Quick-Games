package com.snake.presentation

data class SnakeState(
    val food: Pair<Int, Int>,
    val snake: List<Pair<Int, Int>>,
)

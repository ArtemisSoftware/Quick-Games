package com.snake.domain.models

data class Food(
    val position: Pair<Int, Int>,
    val type: FoodType
)

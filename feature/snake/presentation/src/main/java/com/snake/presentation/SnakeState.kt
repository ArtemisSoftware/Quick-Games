package com.snake.presentation

import com.snake.domain.models.Food

data class SnakeState(
    val food: Food,
    val snake: List<Food>,
    val score: Int = 0,
    val maxScore: Int = 0,
    val foods: Int = 0,
    val length: Int = 0,
    val isPlaying: Boolean = false
)

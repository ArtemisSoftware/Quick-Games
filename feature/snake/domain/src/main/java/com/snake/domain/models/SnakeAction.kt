package com.snake.domain.models

data class SnakeAction(
    val points: Int = 0,
    val body: Food,
    val actionType: ActionType
)

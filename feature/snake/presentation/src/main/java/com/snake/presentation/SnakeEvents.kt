package com.snake.presentation

sealed class SnakeEvents {
    data class MoveSnake(val position: Pair<Int, Int>) : SnakeEvents()
}

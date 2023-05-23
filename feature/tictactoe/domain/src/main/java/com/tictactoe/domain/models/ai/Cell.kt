package com.tictactoe.domain.models.ai

enum class Cell(val y: Int, val x: Int) {
    TOP_LEFT(y = 0, x = 0),
    TOP_CENTER(y = 0, x = 1),
    TOP_RIGHT(y = 0, x = 2),
    CENTER_LEFT(y = 1, x = 0),
    CENTER_CENTER(y = 1, x = 1),
    CENTER_RIGHT(y = 1, x = 2),
    BOTTOM_LEFT(y = 2, x = 0),
    BOTTOM_CENTER(y = 2, x = 1),
    BOTTOM_RIGHT(y = 2, x = 2),
    ;

    companion object {

        fun getCell(y: Int, x: Int) = values().find { it.x == x && it.y == y }
    }
}

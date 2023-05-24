package com.tictactoe.domain.models.ai

import com.tictactoe.domain.models.Move

enum class Cell(val move: Move) {
    TOP_LEFT(move = Move(y = 0, x = 0)),
    CENTER_LEFT(move = Move(y = 1, x = 0)),
    BOTTOM_LEFT(move = Move(y = 2, x = 0)),

    TOP_CENTER(move = Move(y = 0, x = 1)),
    CENTER_CENTER(move = Move(y = 1, x = 1)),
    BOTTOM_CENTER(move = Move(y = 2, x = 1)),

    TOP_RIGHT(move = Move(y = 0, x = 2)),
    CENTER_RIGHT(move = Move(y = 1, x = 2)),
    BOTTOM_RIGHT(move = Move(y = 2, x = 2)),
    ;

    companion object {

        fun getCell(y: Int, x: Int) = values().find { it.move.x == x && it.move.y == y }
    }
}

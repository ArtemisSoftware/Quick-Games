package com.tictactoe.domain.mappers

import com.tictactoe.domain.models.MoveType
import com.tictactoe.domain.models.ai.Board
import com.tictactoe.domain.models.ai.Cell
import com.tictactoe.domain.models.ai.CellState

fun Array<Array<MoveType?>>.toBoard(): Board {
    val board: MutableMap<Cell, CellState> = mutableMapOf()

    this.forEachIndexed { y, _ ->
        this[y].forEachIndexed { x, move ->

            move?.let { moves ->
                Cell.getCell(y = y, x = x)?.let { cell ->
                    board[cell] = moves.toCellState()
                }
            }
        }
    }

    return Board(
        board = board,
    )
}

fun MoveType.toCellState(): CellState {
    return when (this) {
        MoveType.X -> CellState.Star
        MoveType.O -> CellState.Circle
    }
}

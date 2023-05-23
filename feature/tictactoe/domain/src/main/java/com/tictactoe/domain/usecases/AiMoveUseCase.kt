package com.tictactoe.domain.usecases

import com.tictactoe.domain.mappers.toBoard
import com.tictactoe.domain.models.MoveType
import com.tictactoe.domain.models.ai.Cell
import com.tictactoe.domain.models.ai.CellState

class AiMoveUseCase {

    fun invoke(movesPlayed: Array<Array<MoveType?>>): Pair<Int, Int> {
        val mainBoard = movesPlayed.toBoard()

        val circleWinningCell = mainBoard.findNextWinningMove(CellState.Circle)
        val startWinningCell = mainBoard.findNextWinningMove(CellState.Star)
        return when {
            // If the AI can win, place a circle in that spot
            circleWinningCell != null -> Pair(circleWinningCell.y, circleWinningCell.x)
            // If the AI is about to lose, place a circle in a blocking spot
            startWinningCell != null -> Pair(startWinningCell.y, startWinningCell.x)
            // Prioritize the middle
            mainBoard.setCell(Cell.CENTER_CENTER, CellState.Circle) -> Pair(Cell.CENTER_CENTER.y, Cell.CENTER_CENTER.x)
            // Otherwise place a circle in a random spot
            else -> {
                var result = Pair(Cell.CENTER_CENTER.y, Cell.CENTER_CENTER.x)
                do {
                    val nextCell = Cell.values().random()
                    val placeSuccess = mainBoard.setCell(nextCell, CellState.Circle)
                    result = Pair(nextCell.y, nextCell.x)
                }
                while (!placeSuccess)
                result
            }
        }
    }
}

package com.tictactoe.domain.usecases

import com.tictactoe.domain.mappers.toBoard
import com.tictactoe.domain.models.Move
import com.tictactoe.domain.models.MoveType
import com.tictactoe.domain.models.ai.Board
import com.tictactoe.domain.models.ai.Cell
import com.tictactoe.domain.models.ai.CellState

class AiMoveUseCase {

    fun invoke(movesPlayed: Array<Array<MoveType?>>): Move {
        val mainBoard = movesPlayed.toBoard()

        val circleWinningCell = mainBoard.findNextWinningMove(CellState.Circle)
        val startWinningCell = mainBoard.findNextWinningMove(CellState.Star)
        val result = when {

            // If the AI is about to lose, place a circle in a blocking spot
            startWinningCell != null ->
                startWinningCell

            // If the AI can win, place a circle in that spot
            circleWinningCell != null ->
                circleWinningCell

            // Prioritize the middle
            mainBoard.isCellAvailable(Cell.CENTER_CENTER) ->
                Cell.CENTER_CENTER
            // Otherwise place a circle in a random spot
            else -> {
                randomPlay(mainBoard)
            }
        }

        return Move(x = result.move.x, y = result.move.y)
    }

    private fun randomPlay(board: Board): Cell {
        while (true) {
            val nextCell = Cell.values().random()
            val isAvailable = board.isCellAvailable(nextCell)

            if (isAvailable) {
                return nextCell
            }
        }
    }
}

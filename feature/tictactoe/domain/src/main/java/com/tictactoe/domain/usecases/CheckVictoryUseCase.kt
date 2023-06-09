package com.tictactoe.domain.usecases

import com.tictactoe.domain.models.MoveType
import com.tictactoe.domain.Player
import com.tictactoe.domain.models.VictoryType

class CheckVictoryUseCase {

    operator fun invoke(movesPlayed: Array<Array<MoveType?>>, player: Player): VictoryType? {
        with(player) {
            when {
                movesPlayed[0][0] == moveType && movesPlayed[0][1] == moveType && movesPlayed[0][2] == moveType -> {
                    return VictoryType.HORIZONTAL_TOP
                }
                movesPlayed[1][0] == moveType && movesPlayed[1][1] == moveType && movesPlayed[1][2] == moveType -> {
                    return VictoryType.HORIZONTAL_MIDDLE
                }
                movesPlayed[2][0] == moveType && movesPlayed[2][1] == moveType && movesPlayed[2][2] == moveType -> {
                    return VictoryType.HORIZONTAL_BOTTOM
                }
                movesPlayed[0][0] == moveType && movesPlayed[1][0] == moveType && movesPlayed[2][0] == moveType -> {
                    return VictoryType.VERTICAL_LEFT
                }
                movesPlayed[0][1] == moveType && movesPlayed[1][1] == moveType && movesPlayed[2][1] == moveType -> {
                    return VictoryType.VERTICAL_MIDDLE
                }
                movesPlayed[0][2] == moveType && movesPlayed[1][2] == moveType && movesPlayed[2][2] == moveType -> {
                    return VictoryType.VERTICAL_RIGHT
                }
                movesPlayed[0][0] == moveType && movesPlayed[1][1] == moveType && movesPlayed[2][2] == moveType -> {
                    return VictoryType.DIAGONAL_RIGHT
                }
                movesPlayed[2][0] == moveType && movesPlayed[1][1] == moveType && movesPlayed[0][2] == moveType -> {
                    return VictoryType.DIAGONAL_LEFT
                }
                movesPlayed[0].none { it == null } && movesPlayed[1].none { it == null } && movesPlayed[2].none { it == null } -> {
                    return VictoryType.DRAW
                }
                else -> return null
            }
        }
    }
}

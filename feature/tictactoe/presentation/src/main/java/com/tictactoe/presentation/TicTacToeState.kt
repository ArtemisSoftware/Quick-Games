package com.tictactoe.presentation

import com.tictactoe.domain.models.MoveType
import com.tictactoe.domain.Player
import com.tictactoe.domain.models.VictoryType

data class TicTacToeState(
    val players: List<Player> = emptyList(),
    val movesPlayed: Array<Array<MoveType?>> = getEmptyMoveSet(),
    val currentPlayer: Player? = null,
    val victoryType: VictoryType? = null
) {
    companion object {
        fun getEmptyMoveSet(): Array<Array<MoveType?>> = Array(3) { arrayOfNulls(3) }
    }
}

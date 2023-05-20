package com.tictactoe.presentation

import com.tictactoe.domain.MoveType
import com.tictactoe.domain.Player

data class TicTacToeState(
    val players: List<Player> = emptyList(),
    val movesPlayed: Array<Array<MoveType?>> = Array(3) { arrayOfNulls(3) },
)

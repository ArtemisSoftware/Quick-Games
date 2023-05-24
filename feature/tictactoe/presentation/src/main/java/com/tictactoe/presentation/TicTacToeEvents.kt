package com.tictactoe.presentation

import com.tictactoe.domain.models.Move

sealed class TicTacToeEvents {
    object RestartGame : TicTacToeEvents()
    data class PlayMove(val move: Move) : TicTacToeEvents()
}

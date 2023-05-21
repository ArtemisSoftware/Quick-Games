package com.tictactoe.presentation

sealed class TicTacToeEvents {
    object RestartGame : TicTacToeEvents()
    data class PlayMove(val cellNo: Int) : TicTacToeEvents()
}

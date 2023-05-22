package com.tictactoe.presentation

sealed class TicTacToeEvents {
    object RestartGame : TicTacToeEvents()
    data class PlayMove(val x: Int, val y: Int) : TicTacToeEvents()
}

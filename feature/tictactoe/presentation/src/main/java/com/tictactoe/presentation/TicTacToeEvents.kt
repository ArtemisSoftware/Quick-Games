package com.tictactoe.presentation

sealed class TicTacToeEvents {
    object PlayAgain : TicTacToeEvents()
    data class PlayMove(val cellNo: Int) : TicTacToeEvents()
}

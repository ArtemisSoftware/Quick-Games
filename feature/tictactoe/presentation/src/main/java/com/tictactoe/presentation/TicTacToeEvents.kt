package com.tictactoe.presentation

sealed class TicTacToeEvents {
    object PlayAgain : TicTacToeEvents()
    data class MovePlayed(val cellNo: Int) : TicTacToeEvents()
}

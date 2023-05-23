package com.tictactoe.domain.models.ai

sealed class CellState() {
    object Blank : CellState()
    object Star : CellState()
    object Circle : CellState()
}

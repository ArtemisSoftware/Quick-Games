package com.tictactoe.presentation

import androidx.lifecycle.ViewModel
import com.tictactoe.domain.Player
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class TicTacToeViewModel constructor() : ViewModel() {

    private val _state = MutableStateFlow(TicTacToeState())
    val state: StateFlow<TicTacToeState> = _state.asStateFlow()

    init {
        startGame()
    }

    fun onTriggerEvent(event: TicTacToeEvents) {
        when (event) {
            is TicTacToeEvents.PlayMove -> {
                playMove(x = event.x, y = event.y)
            }
            TicTacToeEvents.RestartGame -> {
                restartGame()
            }
        }
    }

    private fun startGame() = with(_state) {
        update {
            it.copy(
                players = listOf(Player.mockPlayer, Player.mockCpuPlayer),
                currentPlayer = Player.mockPlayer,
            )
        }
    }

    private fun playMove(x: Int, y: Int) = with(_state) {
        update {
            val newMoveSet = it.movesPlayed.clone()
            newMoveSet[y][x] = it.currentPlayer?.moveType
            it.copy(
                movesPlayed = newMoveSet,
            )
        }

        changeTurn()
    }

    private fun restartGame() = with(_state) {
        update {
            it.copy(
                currentPlayer = it.players[0],
                movesPlayed = TicTacToeState.getEmptyMoveSet(),
            )
        }
    }

    private fun changeTurn() = with(_state) {
        update {
            it.copy(
                currentPlayer = it.players.find { player -> player.alias != it.currentPlayer?.alias } ?: it.players.first(),
            )
        }
    }
}

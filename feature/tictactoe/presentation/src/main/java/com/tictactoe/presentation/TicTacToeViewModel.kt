package com.tictactoe.presentation

import androidx.lifecycle.ViewModel
import com.tictactoe.domain.Player
import com.tictactoe.domain.models.MoveType
import com.tictactoe.domain.models.VictoryType
import com.tictactoe.domain.usecases.AiMoveUseCase
import com.tictactoe.domain.usecases.CheckVictoryUseCase
import com.tictactoe.domain.usecases.GetPlayersUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class TicTacToeViewModel constructor(
    private val checkVictoryUseCase: CheckVictoryUseCase = CheckVictoryUseCase(),
    private val getPlayersUseCase: GetPlayersUseCase = GetPlayersUseCase(),
    private val aiMoveUseCase: AiMoveUseCase = AiMoveUseCase(),
) : ViewModel() {

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

    private fun startGame() {
        val result = getPlayersUseCase()

        with(_state) {
            update {
                it.copy(
                    players = result,
                    currentPlayer = result[0],
                )
            }
        }
    }

    private fun playMove(x: Int, y: Int) = with(_state.value) {
        if (movesPlayed[y][x] == null && victoryType == null) {
            currentPlayer?.let { currentPlayer ->

                val newMoveSet = movesPlayed.clone()
                newMoveSet[y][x] = currentPlayer.moveType

                val victoryType = checkVictoryUseCase(movesPlayed = newMoveSet, player = currentPlayer)
                updateMoveResult(victoryType = victoryType, movesPlayed = newMoveSet)
            }
        }
    }

    private fun updateMoveResult(victoryType: VictoryType?, movesPlayed: Array<Array<MoveType?>>) = with(_state) {
        victoryType?.let { victory ->

            update {
                it.copy(
                    movesPlayed = movesPlayed,
                    victoryType = victory,
                )
            }

            // TODO: save result
        } ?: run {
            val currentPlayer = changeCurrentPlayer()
            update {
                it.copy(
                    movesPlayed = movesPlayed,
                    currentPlayer = currentPlayer,
                )
            }

            if (currentPlayer is Player.Cpu) {
                aiTurn()
            }
        }
    }

    private fun aiTurn() {
        val move = aiMoveUseCase.invoke(_state.value.movesPlayed)
        playMove(move.first, move.second)
    }

    private fun restartGame() = with(_state) {
        val currentPlayer = changeCurrentPlayer()
        update {
            it.copy(
                currentPlayer = currentPlayer,
                movesPlayed = TicTacToeState.getEmptyMoveSet(),
                victoryType = null,
            )
        }
        if (currentPlayer is Player.Cpu) {
            aiTurn()
        }
    }

    private fun changeCurrentPlayer() = with(_state.value) {
        players.find { player -> player.alias != currentPlayer?.alias } ?: players.first()
    }
}

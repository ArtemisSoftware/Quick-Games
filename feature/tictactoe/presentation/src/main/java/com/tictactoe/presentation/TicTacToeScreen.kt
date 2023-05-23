package com.tictactoe.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tictactoe.domain.Player
import com.tictactoe.domain.models.MoveType
import com.tictactoe.presentation.composables.Announcement
import com.tictactoe.presentation.composables.GameBoard
import com.tictactoe.presentation.composables.QGButton
import com.tictactoe.presentation.composables.ScoreBoard

@Composable
fun TicTacToeScreen(
    events: (TicTacToeEvents) -> Unit,
    state: TicTacToeState,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
//            .background(GrayBackground)
            .padding(horizontal = 30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
    ) {
        ScoreBoard(
            modifier = Modifier.fillMaxWidth(),
            players = state.players,
        )

        Announcement(victoryType = state.victoryType, currentPlayer = state.currentPlayer)

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .shadow(
                    elevation = 2.dp,
                    shape = RoundedCornerShape(4.dp),
                )
                .clip(RoundedCornerShape(4.dp)),
//                .background(GrayBackground)
            contentAlignment = Alignment.Center,
        ) {
            GameBoard(
                movesPlayed = state.movesPlayed,
                modifier = Modifier.size(300.dp),
                victoryType = state.victoryType,
                onTap = { x, y ->
                    events(TicTacToeEvents.PlayMove(x, y))
                },
            )
        }

        Box(
            modifier = Modifier.fillMaxWidth(),
        ) {
            QGButton(
                modifier = Modifier.align(Alignment.CenterEnd),
                textId = if (state.victoryType == null) R.string.restart_game else R.string.new_game,
                onClick = {
                    events(TicTacToeEvents.RestartGame)
                },
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun TicTacToeScreenPreview() {
    TicTacToeScreen(
        events = {},
        state = TicTacToeState(
            players = listOf(Player.mockPlayer, Player.mockPlayer),
            currentPlayer = Player.mockPlayer,
            movesPlayed = arrayOf(
                arrayOf(MoveType.X, null, null),
                arrayOf(null, MoveType.O, MoveType.O),
                arrayOf(null, MoveType.X, null),
            ),
        ),
    )
}

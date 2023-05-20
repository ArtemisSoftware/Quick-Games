package com.tictactoe.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tictactoe.domain.Player
import com.tictactoe.presentation.composables.GameBoard
import com.tictactoe.presentation.composables.PlayerOptions
import com.tictactoe.presentation.composables.ScoreBoard

@Composable
fun TicTacToeScreen() {
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
            xPlayer = Player.mockPlayer,
            oPlayer = Player.mockPlayer,
        )
        Text(
            text = "Tic Tac Toe",
            fontSize = 50.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Cursive,
        )
        Box(
            modifier = Modifier
                .fillMaxWidth(),
//                .aspectRatio(1f)
//                .shadow(
//                    elevation = 10.dp,
//                    shape = RoundedCornerShape(20.dp)
//                )
//                .clip(RoundedCornerShape(20.dp))
//                .background(GrayBackground)
            contentAlignment = Alignment.Center,
        ) {
            // Board(boardSize = 300.dp)
//            GameBoard(
//                modifier = Modifier.size(300.dp),
//            )
        }
        PlayerOptions(modifier = Modifier.fillMaxWidth())
    }
}

@Preview(showBackground = true)
@Composable
private fun TicTacToeScreenPreview() {
    TicTacToeScreen()
}

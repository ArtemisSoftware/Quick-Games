package com.tictactoe.presentation.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@Composable
fun ScoreBoard(
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        BoardInfo(title = "Player 'O'", points = "0")
        BoardInfo(title = "Draw", points = "0")
        BoardInfo(title = "Player 'X'", points = "0")
    }
}

@Composable
private fun BoardInfo(
    title: String,
    points: String,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(text = title, fontSize = 16.sp)
        Text(text = " : ", fontSize = 16.sp)
        Text(text = points, fontSize = 16.sp)
    }
}

@Preview(showBackground = true)
@Composable
private fun ScoreBoardPreview() {
    ScoreBoard(modifier = Modifier.fillMaxWidth())
}

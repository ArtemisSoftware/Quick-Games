package com.tictactoe.presentation.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tictactoe.domain.Player

@Composable
fun ScoreBoard(
    oPlayer: Player,
    xPlayer: Player,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        PlayerInfo(
            player = oPlayer,
            content = {
                Circle(size = 20.dp)
            },
        )
        BoardInfo(title = "Draw", points = "0")
        PlayerInfo(
            player = xPlayer,
            content = {
                Cross(crossSize = 20.dp)
            },
        )
    }
}

@Composable
private fun PlayerInfo(
    player: Player,
    content: @Composable () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        content()
        Text(text = player.alias, fontSize = 16.sp)
        Text(text = " : ", fontSize = 16.sp)
        Text(text = 10.toString(), fontSize = 16.sp)
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
        Box {
            Circle(size = 20.dp)
            Cross(crossSize = 20.dp)
        }

        Text(text = title, fontSize = 16.sp)
        Text(text = " : ", fontSize = 16.sp)
        Text(text = points, fontSize = 16.sp)
    }
}

@Preview(showBackground = true)
@Composable
private fun ScoreBoardPreview() {
    ScoreBoard(
        modifier = Modifier.fillMaxWidth(),
        xPlayer = Player.mockPlayer,
        oPlayer = Player.mockPlayer,
    )
}

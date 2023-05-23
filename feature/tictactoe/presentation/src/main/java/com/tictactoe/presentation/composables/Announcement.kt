package com.tictactoe.presentation.composables

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.tictactoe.domain.Player
import com.tictactoe.domain.models.VictoryType
import com.tictactoe.presentation.R

@Composable
fun Announcement(victoryType: VictoryType?, currentPlayer: Player?) {
    when (victoryType) {
        VictoryType.HORIZONTAL_TOP,
        VictoryType.HORIZONTAL_MIDDLE,
        VictoryType.HORIZONTAL_BOTTOM,
        VictoryType.VERTICAL_LEFT,
        VictoryType.VERTICAL_MIDDLE,
        VictoryType.VERTICAL_RIGHT,
        VictoryType.DIAGONAL_RIGHT,
        VictoryType.DIAGONAL_LEFT,
        -> {
            Text(
                text = currentPlayer?.let { "${it.alias} won" } ?: "",
                fontSize = 50.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Cursive,
                // color = BlueCustom
            )
        }
        VictoryType.DRAW -> {
            Text(
                text = stringResource(id = R.string.draw),
                fontSize = 50.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Cursive,
                // color = BlueCustom
            )
        }
        null -> {
            Text(
                text = currentPlayer?.let { "${it.alias}'s turn" } ?: "",
                fontSize = 24.sp,
                fontStyle = FontStyle.Italic,
                fontWeight = FontWeight.Bold,
            )
        }
    }
}

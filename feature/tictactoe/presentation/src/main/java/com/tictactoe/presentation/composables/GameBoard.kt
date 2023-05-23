package com.tictactoe.presentation.composables

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tictactoe.domain.MoveType
import com.tictactoe.domain.models.VictoryType

@Composable
fun GameBoard(
    movesPlayed: Array<Array<MoveType?>>,
    modifier: Modifier = Modifier,
    xColor: Color = Color.Green,
    oColor: Color = Color.Red,
    onTap: (x: Int, y: Int) -> Unit,
) {
    Canvas(
        modifier = modifier
            .padding(10.dp)
            .pointerInput(true) {
                detectTapGestures {
                    val x = (3 * it.x.toInt() / size.width)
                    val y = (3 * it.y.toInt() / size.height)
                    onTap(x, y)
                }
            },
    ) {
        board()
        movesPlayed.forEachIndexed { y, _ ->
            movesPlayed[y].forEachIndexed { x, move ->

                val offset = Offset(
                    x = x * size.width * (1 / 3f) + size.width / 6f,
                    y = y * size.height * (1 / 3f) + size.height / 6f,
                )

                when (move) {
                    MoveType.X -> {
                        cross(color = xColor, offset)
                    }
                    MoveType.O -> {
                        circle(color = oColor, offset)
                    }
                    null -> {}
                }
            }
        }
        victoryLine(victoryType = VictoryType.VERTICAL_MIDDLE)
    }
}

@Preview(showBackground = true)
@Composable
fun GameBoardPreview() {
    GameBoard(
        movesPlayed = arrayOf(
            arrayOf(MoveType.X, null, null),
            arrayOf(null, MoveType.O, MoveType.O),
            arrayOf(null, MoveType.X, null),
        ),
        onTap = { _, _ -> },
        modifier = Modifier.size(300.dp),
    )
}

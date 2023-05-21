package com.tictactoe.presentation.composables

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

fun DrawScope.board(
    divisionColor: Color = Color.Gray,
) {
    drawLine(
        color = divisionColor,
        strokeWidth = 5f,
        cap = StrokeCap.Round,
        start = Offset(x = size.width / 3, y = 0f),
        end = Offset(x = size.width / 3, y = size.height),
    )
    drawLine(
        color = divisionColor,
        strokeWidth = 5f,
        cap = StrokeCap.Round,
        start = Offset(x = size.width * 2 / 3, y = 0f),
        end = Offset(x = size.width * 2 / 3, y = size.height),
    )
    drawLine(
        color = divisionColor,
        strokeWidth = 5f,
        cap = StrokeCap.Round,
        start = Offset(x = 0f, y = size.height / 3),
        end = Offset(x = size.width, y = size.height / 3),
    )
    drawLine(
        color = divisionColor,
        strokeWidth = 5f,
        cap = StrokeCap.Round,
        start = Offset(x = 0f, y = size.height * 2 / 3),
        end = Offset(x = size.width, y = size.height * 2 / 3),
    )
}

@Preview(showBackground = true)
@Composable
private fun BoardPreview() {
    Canvas(
        modifier = Modifier.size(300.dp),
    ) {
        board()
    }
}

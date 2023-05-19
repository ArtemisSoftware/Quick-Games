package com.tictactoe.presentation.composables

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun Board(
    boardSize: Dp,
    modifier: Modifier = Modifier,
    divisionColor: Color = Color.Gray,
) {
    Canvas(
        modifier = Modifier
            .size(boardSize)
            .padding(10.dp)
            .then(modifier),
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
}

@Preview(showBackground = true)
@Composable
private fun BoardPreview() {
    Board(boardSize = 300.dp)
}

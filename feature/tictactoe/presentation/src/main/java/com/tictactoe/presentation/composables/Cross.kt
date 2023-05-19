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
fun Cross(
    crossSize: Dp,
    modifier: Modifier = Modifier,
    color: Color = Color.Blue,
) {
    Canvas(
        modifier = Modifier
            .size(crossSize)
            .padding(5.dp)
            .then(modifier),
    ) {
        drawLine(
            color = color,
            strokeWidth = 20f,
            cap = StrokeCap.Round,
            start = Offset(x = 0f, y = 0f),
            end = Offset(x = size.width, y = size.height),
        )
        drawLine(
            color = color,
            strokeWidth = 20f,
            cap = StrokeCap.Round,
            start = Offset(x = 0f, y = size.height),
            end = Offset(x = size.width, y = 0f),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CrossPreview() {
    Cross(crossSize = 60.dp)
}

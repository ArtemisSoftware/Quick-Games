package com.tictactoe.presentation.composables

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun Circle(
    size: Dp,
    modifier: Modifier = Modifier,
    color: Color = Color.Blue,
) {
    Canvas(
        modifier = Modifier
            .size(size)
            .padding(5.dp)
            .then(modifier),
    ) {
        drawCircle(
            color = color,
            style = Stroke(width = 20f),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CirclePreview() {
    Circle(size = 60.dp)
}

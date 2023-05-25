package com.snake.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SnakeBody(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .background(
                DarkGreen,
                RoundedCornerShape(4.dp),
            ),
    )
}

@Preview(showBackground = true)
@Composable
private fun SnakeBodyPreview() {
    SnakeBody(
        modifier = Modifier
            .size(42.dp),
    )
}

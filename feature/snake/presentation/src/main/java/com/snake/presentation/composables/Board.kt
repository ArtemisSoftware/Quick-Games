package com.snake.presentation.composables

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.snake.presentation.SnakeState

val LightGreen = Color(0xFF8DA25A)
val DarkGreen = Color(0xFF2D321D)
const val BOARD_SIZE = 16

@Composable
fun Board(state: SnakeState) {
    BoxWithConstraints(
        modifier = Modifier.padding(16.dp),
    ) {
        val tileSize = maxWidth / BOARD_SIZE

        Box(
            Modifier
                .size(maxWidth)
                .border(2.dp, DarkGreen),
        )

        Food(
            modifier = Modifier
                .offset(
                    x = tileSize * state.food.first,
                    y = tileSize * state.food.second,
                )
                .size(tileSize),
        )

        state.snake.forEach {
            SnakeBody(
                modifier = Modifier
                    .offset(
                        x = tileSize * it.first,
                        y = tileSize * it.second,
                    )
                    .size(tileSize),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun BoardPreview() {
    Board(
        state = SnakeState(
            food = Pair(6, 5),
            snake = listOf(Pair(7, 7)),
        ),
    )
}

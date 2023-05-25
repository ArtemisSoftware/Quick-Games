package com.snake.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import com.snake.presentation.composables.Board
import com.snake.presentation.composables.GamePad

@Composable
fun SnakeScreen(
    state: SnakeState,
    event: (SnakeEvents) -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Board(state = state)

        GamePad(
            onDirectionChange = {
                event.invoke(SnakeEvents.MoveSnake(it))
            },
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun BoardPreview() {
    SnakeScreen(
        state = SnakeState(
            food = Pair(5, 5),
            snake = listOf(Pair(7, 7)),
        ),
        event = {},
    )
}

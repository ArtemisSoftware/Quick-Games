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
import com.snake.domain.models.Food
import com.snake.domain.models.FoodType
import com.snake.presentation.SnakeState

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

        SnakeFood(
            modifier = Modifier
                .offset(
                    x = tileSize * state.food.position.first,
                    y = tileSize * state.food.position.second,
                )
                .size(tileSize),
            foodType = state.food.type,
        )

        state.snake.forEach {
            SnakeBody(
                modifier = Modifier
                    .offset(
                        x = tileSize * it.position.first,
                        y = tileSize * it.position.second,
                    )
                    .size(tileSize),
                foodType = it.type,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun BoardPreview() {
    Board(
        state = SnakeState(
            food = Food(Pair(6, 5), FoodType.NEON),
            snake = listOf(
                Food(Pair(7, 7), FoodType.FRUIT),
                Food(Pair(7, 8), FoodType.NEON),
            ),
        ),
    )
}

package com.snake.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.snake.domain.models.Food
import com.snake.domain.models.FoodType
import com.snake.presentation.composables.Board
import com.snake.presentation.composables.GamePad
import com.snake.presentation.composables.ManageUIEvents
import com.snake.presentation.composables.ScoreBoard

@Composable
fun SnakeScreen(
    viewModel: SnakeViewModel = hiltViewModel(),
    navController: NavHostController,
) {
    SnakeScreenContent(
        state = viewModel.state.collectAsState().value,
        event = viewModel::onTriggerEvent,
    )

    ManageUIEvents(
        uiEvent = viewModel.uiEvent,
        onNavigate = {
            navController.navigate(route = it.route)
        },
        onPopBackStack = {
            navController.popBackStack()
        },
    )
}

@Composable
private fun SnakeScreenContent(
    state: SnakeState,
    event: (SnakeEvents) -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Board(state = state)
        ScoreBoard(
            modifier = Modifier.fillMaxWidth(),
            score = state.score,
            maxScore = state.maxScore,
            foods = state.foods,
        )
        GamePad(
            onDirectionChange = {
                event.invoke(SnakeEvents.MoveSnake(it))
            },
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SnakeScreenContentPreview() {
    SnakeScreenContent(
        state = SnakeState(
            food = Food(position = Pair(5, 5), type = FoodType.FRUIT),
            snake = listOf(Food(position = Pair(7, 7), type = FoodType.FRUIT)),
        ),
        event = {},
    )
}

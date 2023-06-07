package com.snake.presentation.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp

@Composable
fun ScoreBoard(
    modifier: Modifier = Modifier,
    score: Int,
    maxScore: Int,
    foods: Int
) {
    Column(
        modifier = modifier,
    ) {
        Row {
            Text(text = "Score: ", fontSize = 16.sp)
            Text(text = score.toString(), fontSize = 16.sp)
        }
        Row {
            Text(text = "Max Score: ", fontSize = 16.sp)
            Text(text = maxScore.toString(), fontSize = 16.sp)
        }
        Row {
            Text(text = "Foods: ", fontSize = 16.sp)
            Text(text = foods.toString(), fontSize = 16.sp)
        }
    }
}

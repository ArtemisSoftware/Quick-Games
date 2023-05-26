package com.snake.presentation.highscore.composables

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.snake.domain.models.HighScore
import com.snake.presentation.ui.theme.SnakeTheme

@Composable
fun HighScoreItem(
    modifier: Modifier = Modifier,
    highScore: HighScore,
) {
    Row(
        modifier = modifier
            .padding(8.dp),
    ) {
        Text(
            text = highScore.playerName,
            modifier = Modifier.weight(1f),
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.displayLarge,
            textAlign = TextAlign.Center,
        )
        Text(
            text = highScore.score.toString(),
            modifier = Modifier.weight(1f),
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.displayLarge,
            textAlign = TextAlign.Center,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun HighScoreItemPreview() {
    SnakeTheme {
        HighScoreItem(
            highScore = HighScore(playerName = "Master", score = 100),
            modifier = Modifier
                .fillMaxWidth(),

        )
    }
}

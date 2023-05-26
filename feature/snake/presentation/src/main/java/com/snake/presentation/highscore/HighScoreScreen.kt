package com.snake.presentation.highscore

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.snake.domain.models.HighScore
import com.snake.presentation.R
import com.snake.presentation.composables.QGTopBar
import com.snake.presentation.highscore.composables.HighScoreItem
import com.snake.presentation.ui.theme.SnakeTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HighScoreScreen(
    state: HighScoreState,
/*navController: NavHostController*/
) {
    Scaffold(
        topBar = {
            QGTopBar(
                textId = R.string.high_score,
                onBackClicked = {},
            )
        },
        content = { contentPadding ->

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        top = contentPadding.calculateTopPadding(),
                        bottom = 16.dp,
                        start = 16.dp,
                        end = 16.dp,
                    )
                    .border(
                        width = 2.dp,
                        color = MaterialTheme.colorScheme.onBackground,
                    ),
            ) {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                ) {
                    Text(
                        text = stringResource(R.string.player_name),
                        modifier = Modifier.weight(1f),
                        color = MaterialTheme.colorScheme.primary,
                        style = MaterialTheme.typography.displayLarge,
                        textAlign = TextAlign.Center,
                    )

                    Text(
                        text = stringResource(R.string.score),
                        modifier = Modifier.weight(1f),
                        color = MaterialTheme.colorScheme.primary,
                        style = MaterialTheme.typography.displayLarge,
                        textAlign = TextAlign.Center,
                    )
                }
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(items = state.highScores) {
                        HighScoreItem(
                            modifier = Modifier.fillMaxWidth(),
                            highScore = it,
                        )
                    }
                }
            }
        },
    )
}

@Preview(showBackground = true)
@Composable
private fun MainMenuScreenPreview() {
    SnakeTheme {
        HighScoreScreen(
            state = HighScoreState(
                highScores = listOf(
                    HighScore(
                        playerName = "Gamer 1",
                        score = 10,
                    ),
                ),
            ),
        )
    }
}

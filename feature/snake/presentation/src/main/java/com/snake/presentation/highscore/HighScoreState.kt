package com.snake.presentation.highscore

import com.snake.domain.models.HighScore

data class HighScoreState(
    val highScores: List<HighScore> = emptyList()
)

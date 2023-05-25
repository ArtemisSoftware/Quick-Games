package com.snake.domain.usecases

import com.snake.domain.models.Settings

class GetGameSettingsUseCase {

    operator fun invoke(): Settings {
        return Settings(speed = 350, initialSnakeSize = 4)
    }
}

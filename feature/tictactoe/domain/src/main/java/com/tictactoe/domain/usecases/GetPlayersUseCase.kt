package com.tictactoe.domain.usecases

import com.tictactoe.domain.Player

class GetPlayersUseCase {

    operator fun invoke(): List<Player> {
        return listOf(Player.mockPlayer, Player.mockCpuPlayer)
    }
}

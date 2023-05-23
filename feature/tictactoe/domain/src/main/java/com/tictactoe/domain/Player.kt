package com.tictactoe.domain

import com.tictactoe.domain.models.MoveType

sealed class Player(val alias: String, val moveType: MoveType) {

    data class Human(val name: String, val move: MoveType) : Player(alias = name, moveType = move)
    data class Cpu(val name: String) : Player(alias = name, moveType = MoveType.X)

    companion object {
        val mockPlayer = Player.Human(name = "Pupil", move = MoveType.O)
        val mockAdversaryPlayer = Player.Human(name = "Adversary", move = MoveType.X)
        val mockCpuPlayer = Player.Cpu(name = "GrandMaster")
    }
}

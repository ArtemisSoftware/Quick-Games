package com.tictactoe.domain

sealed class Player(val alias: String, val moveType: MoveType) {

    data class Human(val name: String, val move: MoveType) : Player(alias = name, moveType = move)
    data class Cpu(val name: String) : Player(alias = name, moveType = MoveType.X)

    companion object {
        val mockPlayer = Player.Human(name = "Sagat", move = MoveType.O)
        val mockCpuPlayer = Player.Cpu(name = "GrandMaster")
    }
}

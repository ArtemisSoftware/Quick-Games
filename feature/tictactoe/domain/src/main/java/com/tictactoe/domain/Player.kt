package com.tictactoe.domain

sealed class Player(val alias: String) {

    data class Human(val name: String) : Player(alias = name)
    data class Cpu(val name: String) : Player(alias = name)

    companion object {
        val mockPlayer = Player.Human(name = "Sagat")
    }
}

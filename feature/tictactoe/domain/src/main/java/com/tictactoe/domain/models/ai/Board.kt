package com.tictactoe.domain.models.ai

data class Board(private val board: MutableMap<Cell, CellState> = mutableMapOf()) {

    /**
     * Set the cell state
     *
     * @param cell: The Cell to assign a state to
     * @param state: The State to assign
     *
     * @return true iff the state was set successfully. If the cell has already been set,
     * false will be returned
     */
    fun setCell(cell: Cell, state: CellState): Boolean {
        if (board.containsKey(cell)) {
            return false
        }
        return true
    }

    fun isCellAvailable(cell: Cell) = !board.containsKey(cell)

    /**
     * Find the next winning move for the current cell state
     *
     * @return The cell of the winning move for the provided state.
     * If there is no winning move on this turn, return null
     */
    fun findNextWinningMove(state: CellState): Cell? = when {
        Cell.TOP_LEFT wins state -> Cell.TOP_LEFT
        Cell.TOP_CENTER wins state -> Cell.TOP_CENTER
        Cell.TOP_RIGHT wins state -> Cell.TOP_RIGHT
        Cell.CENTER_LEFT wins state -> Cell.CENTER_LEFT
        Cell.CENTER_CENTER wins state -> Cell.CENTER_CENTER
        Cell.CENTER_RIGHT wins state -> Cell.CENTER_RIGHT
        Cell.BOTTOM_LEFT wins state -> Cell.BOTTOM_LEFT
        Cell.BOTTOM_CENTER wins state -> Cell.BOTTOM_CENTER
        Cell.BOTTOM_RIGHT wins state -> Cell.BOTTOM_RIGHT
        else -> null
    }

    private infix fun Cell.wins(state: CellState): Boolean {
        if (board.containsKey(this)) {
            return false
        }
        board[this] = state
        val hasWon = stateWon(state)
        board.remove(this)
        return hasWon
    }

    private fun stateWon(state: CellState): Boolean {
        fun testState(vararg cells: Cell) = cells.all { cell ->
            board[cell] == state
        }

        return testState(Cell.TOP_LEFT, Cell.CENTER_LEFT, Cell.BOTTOM_LEFT) ||
            testState(Cell.TOP_CENTER, Cell.CENTER_CENTER, Cell.BOTTOM_CENTER) ||
            testState(Cell.TOP_RIGHT, Cell.CENTER_RIGHT, Cell.BOTTOM_RIGHT) ||
            testState(Cell.TOP_LEFT, Cell.TOP_CENTER, Cell.TOP_RIGHT) ||
            testState(Cell.CENTER_LEFT, Cell.CENTER_CENTER, Cell.CENTER_RIGHT) ||
            testState(Cell.BOTTOM_LEFT, Cell.BOTTOM_CENTER, Cell.BOTTOM_RIGHT) ||
            testState(Cell.TOP_LEFT, Cell.CENTER_CENTER, Cell.BOTTOM_RIGHT) ||
            testState(Cell.BOTTOM_LEFT, Cell.CENTER_CENTER, Cell.TOP_RIGHT)
    }
}

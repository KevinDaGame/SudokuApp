package model

class SudokuBlock(cells: List<SudokuCell>) : SudokuGroup(cells) {
    fun isLastX(x: Int, y: Int): Boolean {
        return cells.filter { it.y == y }.none { it.x > x }
    }

    fun isLastY(x: Int, y: Int): Boolean {
        return cells.filter { it.x == x }.none { it.y > y }
    }
}
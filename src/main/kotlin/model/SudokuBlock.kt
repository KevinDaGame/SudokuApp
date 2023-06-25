package model

class SudokuBlock(children: List<SudokuCell>) : SudokuGroup(children) {
    private fun isFirstX(x: Int, y: Int): Boolean {
        return getCells().filter { it.y == y }.none { it.x < x }
    }

    private fun isLastX(x: Int, y: Int): Boolean {
        return getCells().filter { it.y == y }.none { it.x > x }
    }

    private fun isFirstY(x: Int, y: Int): Boolean {
        return getCells().filter { it.x == x }.none { it.y < y }
    }

    private fun isLastY(x: Int, y: Int): Boolean {
        return getCells().filter { it.x == x }.none { it.y > y }
    }

    fun getBorders(x: Int, y: Int): List<BorderDirection> {
        val borders = mutableListOf<BorderDirection>()
        if (isLastY(x, y)) {
            borders.add(BorderDirection.DOWN)
        }
        if (isFirstY(x, y)) {
            borders.add(BorderDirection.UP)
        }
        if (isFirstX(x, y)) {
            borders.add(BorderDirection.LEFT)
        }
        if (isLastX(x, y)) {
            borders.add(BorderDirection.RIGHT)
        }
        return borders
    }
}
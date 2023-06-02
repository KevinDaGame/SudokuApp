package model

open class SudokuGroup(val cells: List<SudokuCell>) : ICheckable {
    override fun isSolved(): Boolean {
        return cells.all { it.isSolved() } && getInvalidCells().isEmpty()
    }

    override fun getInvalidCells(): List<SudokuCell> {
        //get all cells whose value occurs more than once
        val cells = cells.flatMap { it.getInvalidCells() }
        return cells.minus(cells.distinctBy { it.value.value }.toSet())
    }
}
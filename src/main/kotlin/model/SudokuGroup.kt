package model

open class SudokuGroup(val cells: List<SudokuCell>) : ICheckable {
    override fun isSolved(): Boolean {
        return cells.all { it.isSolved() } && getInvalidCells().isEmpty()
    }

    override fun getInvalidCells(): List<SudokuCell> {
        val invalidCells = mutableListOf<SudokuCell>()
        for (cell in cells) {
            invalidCells.addAll(cell.getInvalidCells())
        }
        // get all numbers that occur more than once and return all cells with that value
        val duplicateValues = invalidCells.groupingBy { it.value.value }.eachCount().filterValues { it > 1 }.keys
        return invalidCells.filter { it.value.value in duplicateValues }.filter { it.value.state !== CellState.PROVIDED }
    }
}
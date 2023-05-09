package model

open class SudokuGroup(val cells: List<SudokuCell>): ICheckable {
    override fun isSolved(): Boolean {
        return cells.all {it.isSolved()} && getInvalidCells().isEmpty()
    }

    override fun getInvalidCells(): List<SudokuCell> {
        val invalidCells = mutableListOf<SudokuCell>()
        for (cell in cells) {
            invalidCells.addAll(cell.getInvalidCells())
        }
        val duplicateValues = invalidCells.groupingBy { it.value }.eachCount().filterValues { it >= 2 }.keys
        return invalidCells.filter { it.value.isDefinitive && it.value.value != 0}.filter { it.value in duplicateValues }
    }
}
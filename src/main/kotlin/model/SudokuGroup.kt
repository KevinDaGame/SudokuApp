package model

open class SudokuGroup(val cells: List<SudokuCell>) : ICheckable {
    override fun isSolved(): Boolean {
        return cells.all { it.isSolved() } && getInvalidCells().isEmpty()
    }

    override fun getInvalidCells(): List<SudokuCell> {
        //get all cells whose value occurs more than once
        val cells = cells.flatMap { it.getInvalidCells() }
        // get all numbers that occur more than once and return all cells with that value
        val duplicateValues = cells.groupingBy { it.value.value }.eachCount().filterValues { it > 1 }.keys
        return cells.filter { it.value.value in duplicateValues }.filter { it.value.state !== CellState.PROVIDED }
    }
}
package model

open class SudokuGroup(private val children: List<ICheckable>) : ICheckable {
    override fun isSolved(): Boolean {
        return children.all { it.isSolved() } && getInvalidCells().isEmpty()
    }

    override fun getCells(): List<SudokuCell> {
        return children.flatMap { it.getCells() }
    }

    override fun getInvalidCells(): List<SudokuCell> {
        //get all cells whose value occurs more than once
        val cells = children.flatMap { it.getInvalidCells() }
        // get all numbers that occur more than once and return all cells with that value
        val duplicateValues = cells.groupingBy { it.value.value }.eachCount().filterValues { it > 1 }.keys
        return cells.filter { it.value.value in duplicateValues }.filter { it.value.state !== CellState.PROVIDED }
    }
}
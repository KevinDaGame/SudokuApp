package model

interface ICheckable {
    /**
     * Returns true if all cells are filled with definitive values and there are no duplicate values in any group.
     */
    fun isSolved(): Boolean

    /**
     * Returns a list of all cells in the model.
     */
    fun getCells(): List<SudokuCell>

    /**
     * Returns a list of cells that are invalid. A cell is invalid if it has a definitive value which is not 0 and has a duplicate in a group it belongs to.
     */
    fun getInvalidCells(): List<SudokuCell>

}
package controller

import model.*

class SudokuController(val model: SudokuModel) : Controller {
    fun getBlocks(): List<SudokuBlock> {
        return model.sudokuGroups.filterIsInstance<SudokuBlock>()
    }

    fun setCellValue(x: Int, y: Int, value: Int, state: CellState) {
        model.sudokuGroups.flatMap { it.cells }.first { it.x == x && it.y == y }.value = CellValue(value, state)
    }

    fun getInvalidCells(): List<SudokuCell> {
        return model.getInvalidCells()
    }
}
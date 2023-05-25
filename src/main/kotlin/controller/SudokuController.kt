package controller

import model.CellValue
import model.SudokuBlock
import model.SudokuCell
import model.SudokuModel

class SudokuController(val model: SudokuModel) : Controller {
    fun getBlocks(): List<SudokuBlock> {
        return model.sudokuGroups.filterIsInstance<SudokuBlock>()
    }

    fun setCellValue(x: Int, y: Int, value: Int, definitive: Boolean = false) {
        model.sudokuGroups.flatMap { it.cells }.first { it.x == x && it.y == y }.value = CellValue(value, definitive)
    }

    fun getInvalidCells(): List<SudokuCell> {
        return model.getInvalidCells()
    }
}
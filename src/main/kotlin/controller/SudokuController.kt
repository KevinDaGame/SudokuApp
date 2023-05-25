package controller

import model.*

class SudokuController(val model: SudokuModel) : Controller {
    fun getBlocks(): List<SudokuBlock> {
        return model.sudokuGroups.filterIsInstance<SudokuBlock>()
    }

    fun setCellValue(x: Int, y: Int, value: Int, state: CellState) {
        val cell = model.sudokuGroups.flatMap { it.cells }.first { it.x == x && it.y == y }
        if (cell.value.state == CellState.PROVIDED) return
        cell.value = CellValue(value, state)
    }

    fun getInvalidCells(): List<SudokuCell> {
        return model.getInvalidCells()
    }
}
package controller

import model.*

class SudokuController(val model: SudokuModel) : Controller {
    fun getBlocks(): List<SudokuBlock> {
        return model.sudokuGroups.filterIsInstance<SudokuBlock>()
    }

    fun setCellValue(x: Int, y: Int, value: Int, state: CellState) {
        val cell = model.sudokuGroups.flatMap { it.cells }.first { it.x == x && it.y == y }
        if (cell.value.state == CellState.PROVIDED) return
        if (cell.value.value == value || value == 0) cell.value = CellValue(0, CellState.EMPTY)
        else cell.value = CellValue(value, state)
    }

    fun getInvalidCells(): List<SudokuCell> {
        return model.getInvalidCells()
    }
}
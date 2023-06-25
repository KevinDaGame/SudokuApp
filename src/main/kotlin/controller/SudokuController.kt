package controller

import model.*

class SudokuController(val model: SudokuModel) : Controller {
    fun getBlocks(): List<SudokuBlock> {
        return model.children.filterIsInstance<SudokuBlock>()
    }

    fun setCellValue(x: Int, y: Int, value: Int, state: CellState) {
        try {
            if(value.digitToChar() !in model.validCharacters) return
        }
        catch (e: IllegalArgumentException) {
            return
        }

        if(state == CellState.PROVIDED) return

        val cell = model.getCells().first { it.x == x && it.y == y }
        if (cell.value.state == CellState.PROVIDED) return
        if (cell.value.value == value || value == 0) cell.value = CellValue(0, CellState.EMPTY)
        else cell.value = CellValue(value, state)
    }

    fun getInvalidCells(): List<SudokuCell> {
        return model.getInvalidCells()
    }
}
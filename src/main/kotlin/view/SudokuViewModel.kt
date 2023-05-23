package view

import model.BorderDirection
import model.SudokuBlock
import model.SudokuCell

class SudokuViewModel(blocks: List<SudokuBlock>, val width: Int, val height: Int) {
    val board: Array<Array<ViewSudokuCell?>>
    init {
        val constructBoard = Array(height) { arrayOfNulls<ViewSudokuCell>(width) }
        for (block in blocks) {
            for (cell in block.cells) {
                constructBoard[cell.y][cell.x] = ViewSudokuCell(cell, block.getBorders(cell.x, cell.y))
            }
        }
        board = constructBoard
    }
}

data class ViewSudokuCell(val sudokuCell: SudokuCell, val borders: List<BorderDirection>) {
    val x get() = sudokuCell.x
    val y get() = sudokuCell.y
    val value get() = sudokuCell.value

}
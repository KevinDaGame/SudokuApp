package view

import model.SudokuBlock
import model.SudokuCell

class SudokuViewModel(blocks: List<SudokuBlock>, width: Int, height: Int) {
    val board: Array<Array<ViewSudokuCell?>>
    init {
        val constructBoard = Array(height) { arrayOfNulls<ViewSudokuCell>(width) }
        for (block in blocks) {
            for (cell in block.cells) {
                constructBoard[cell.y][cell.x] = ViewSudokuCell(cell, cell.x < width && block.isLastX(cell.x, cell.y), cell.y < height && block.isLastY(cell.x, cell.y))
            }
        }
        board = constructBoard
    }
}

data class ViewSudokuCell(val sudokuCell: SudokuCell, val borderX: Boolean, val borderY: Boolean) {
    val x get() = sudokuCell.x
    val y get() = sudokuCell.y
    val value get() = sudokuCell.value

}
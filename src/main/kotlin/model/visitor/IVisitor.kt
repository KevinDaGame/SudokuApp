package model.visitor

import model.SudokuModel

interface IVisitor {
    fun visit(sudoku: SudokuModel)
}
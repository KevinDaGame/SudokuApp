package model.visitor

import model.*

class SolveSudokuVisitor : IVisitor {
    override fun visit(sudoku: SudokuModel) {
        val board = Array(sudoku.width) { arrayOfNulls<SudokuCell>(sudoku.height) }

        for (group in sudoku.sudokuGroups) {
            if (group is SudokuBlock) {
                for (cell in group.cells) {
                    if (cell.value.state == CellState.DEFINITIVE) {
                        cell.value = CellValue(0, CellState.EMPTY)
                    }
                    board[cell.y][cell.x] = cell

                }
            }
        }

        solveSudoku(board, sudoku)
    }

    private fun solveSudoku(board: Array<Array<SudokuCell?>>, sudoku: SudokuModel): Boolean {
        for (row in board.indices) {
            for (col in board[0].indices) {
                val cell = board[row][col] ?: continue
                // find an empty cell
                if (cell.value.state == CellState.EMPTY) {

                    // try each number from 1 to 9
                    for (num in 1..9) {

                        // place num
                        cell.value = CellValue(num, CellState.DEFINITIVE)
                        // check if placing num at (row, col) is valid
                        if (sudoku.sudokuGroups.filter { it.cells.contains(cell) }
                                .all { it.getInvalidCells().isEmpty() }) {

                            // recursively solve the rest of the board
                            if (solveSudoku(board, sudoku)) {
                                return true
                            }

                            cell.value = CellValue(0, CellState.EMPTY)
                        } else {
                            cell.value = CellValue(0, CellState.EMPTY)
                        }
                    }

                    // If we've tried all numbers and none worked, backtrack
                    return false
                }
            }
        }

        // If we've filled all empty cells, we have a solution!
        return true
    }

}
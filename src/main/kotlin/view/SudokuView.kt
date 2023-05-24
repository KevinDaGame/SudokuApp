package view

import controller.SudokuController
import io.github.shuoros.jterminal.JTerminal
import io.github.shuoros.jterminal.ansi.Color
import model.BorderDirection

class SudokuView(private val controller: SudokuController) : IView {
    private var x: Int = 0
    private var y: Int = 0

    override fun render() {
        printSudoku()
    }

    private fun printSudoku() {
        val viewModel = SudokuViewModel(controller.getBlocks(), controller.model.width, controller.model.height)
        val board = Array(viewModel.width * 2 + 1) { Array(viewModel.height * 4 + 1) { ConsoleChar() } }

        for ((y, row) in viewModel.board.withIndex()) {
            printRow(board, y, row)
        }

        showCursor(board)
        for (line in board) {
            for (cell in line) {
                JTerminal.print(cell.char.toString(), cell.color, cell.background)
            }
            JTerminal.println("")
        }
    }

    private fun showCursor(board: Array<Array<ConsoleChar>>) {
        board[y * 2 + 1][x * 4 + 2] =
            ConsoleChar(board[y * 2 + 1][x * 4 + 2].char, background = Color.RED)
    }

    private fun printRow(board: Array<Array<ConsoleChar>>, y: Int, row: Array<ViewSudokuCell?>) {
        for ((x, cell) in row.withIndex()) {
            if (cell != null) {
                board[y * 2 + 1][x * 4 + 2] = ConsoleChar(cell.value.value.digitToChar())
                addBorders(cell, board, y, x)

            }

        }

    }

    private fun addBorders(cell: ViewSudokuCell, board: Array<Array<ConsoleChar>>, y: Int, x: Int) {
        val borderChar = ConsoleChar('█', Color.SILVER)
        for (border in cell.borders) {
            when (border) {
                BorderDirection.UP -> {
                    board[y * 2][x * 4] = borderChar
                    board[y * 2][x * 4 + 1] = borderChar
                    board[y * 2][x * 4 + 2] = borderChar
                    board[y * 2][x * 4 + 3] = borderChar
                }

                BorderDirection.DOWN -> {
                    board[y * 2 + 2][x * 4] = borderChar
                    board[y * 2 + 2][x * 4 + 1] = borderChar
                    board[y * 2 + 2][x * 4 + 2] = borderChar
                    board[y * 2 + 2][x * 4 + 3] = borderChar
                }

                BorderDirection.LEFT -> {
                    board[y * 2][x * 4] = borderChar
                    board[y * 2 + 1][x * 4] = borderChar
                    board[y * 2 + 2][x * 4] = borderChar
                }

                BorderDirection.RIGHT -> {
                    board[y * 2][x * 4 + 4] = borderChar
                    board[y * 2 + 1][x * 4 + 4] = borderChar
                    board[y * 2 + 2][x * 4 + 4] = borderChar
                }
            }
        }
    }

    override fun handleInput(input: Char) {
        //handle arrows
        when (input) {
            'w' -> y--
            'a' -> x--
            's' -> y++
            'd' -> x++
            else -> {
                val value = input.toString().toIntOrNull()
                if (value != null) {
                    controller.setCellValue(x, y, value)
                }
            }
        }
    }

}
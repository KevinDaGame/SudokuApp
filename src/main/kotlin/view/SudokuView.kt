package view

import controller.SudokuController
import io.github.shuoros.jterminal.JTerminal
import io.github.shuoros.jterminal.ansi.Color
import model.BorderDirection
import model.CellState
import model.SudokuCell

class SudokuView(private val controller: SudokuController) : IView {
    private var x: Int = 0
    private var y: Int = 0
    private var editorMode: EditorMode = EditorMode.DEFINITIVE
    private var showInvalidCells: Boolean = false

    override fun render() {
        printSudoku()
    }

    private fun printSudoku() {
        val viewModel = SudokuViewModel(controller.getBlocks(), controller.model.width, controller.model.height)
        val board = Array(viewModel.width * 2 + 1) { Array(viewModel.height * 4 + 1) { ConsoleChar() } }
        val invalidCells = if (showInvalidCells) controller.getInvalidCells() else emptyList()

        for ((y, row) in viewModel.board.withIndex()) {
            printRow(board, y, row, invalidCells)
        }

        showCursor(board)
        for (line in board) {
            for (cell in line) {
                JTerminal.print(cell.char.toString(), cell.color, cell.background)
            }
            JTerminal.println("")
        }
    }

    private fun printRow(
        board: Array<Array<ConsoleChar>>,
        y: Int,
        row: Array<ViewSudokuCell?>,
        invalidCells: List<SudokuCell>
    ) {
        for ((x, cell) in row.withIndex()) {
            if (cell != null) {
                board[y * 2 + 1][x * 4 + 2] =
                    getCellChar(cell, invalidCells.filter { it.x == x && it.y == y }.isNotEmpty())
                addBorders(cell, board, y, x)

            }

        }

    }

    private fun getCellChar(cell: ViewSudokuCell, isInvalid: Boolean): ConsoleChar {
        val value = if (cell.value.value > 0) cell.value.value.digitToChar() else '_'
        var foreground = when (cell.value.state) {
            CellState.EMPTY -> Color.WHITE
            CellState.DEFINITIVE -> Color.WHITE
            CellState.PENCIL -> Color.BLUE
            CellState.PROVIDED -> Color.YELLOW
        }
        if (isInvalid) foreground = Color.RED
        return ConsoleChar(value, foreground)
    }

    private fun addBorders(cell: ViewSudokuCell, board: Array<Array<ConsoleChar>>, y: Int, x: Int) {
        val borderChar = ConsoleChar('█', if (editorMode == EditorMode.DEFINITIVE) Color.WHITE else Color.SILVER)
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

    private fun showCursor(board: Array<Array<ConsoleChar>>) {
        val cell = board[y * 2 + 1][x * 4 + 2]
        board[y * 2 + 1][x * 4 + 2] = ConsoleChar(cell.char, cell.color, Color.GREEN)
    }

    override fun handleInput(input: Char): Boolean {
        //handle arrows
        when (input) {
            'w' -> {
                y--
                return true
            }
            'a' -> {
                x--
                return true
            }
            's' -> {
                y++
                return true
            }

            'd' -> {
                x++
                return true
            }

            ' ' -> {
                editorMode = editorMode.getNext()
                return false
            }

            'c' -> {
                showInvalidCells = !showInvalidCells
                return false
            }

            else -> {
                val value = input.toString().toIntOrNull()
                if (value != null) {
                    controller.setCellValue(
                        x,
                        y,
                        value,
                        if (editorMode == EditorMode.DEFINITIVE) CellState.DEFINITIVE else CellState.PENCIL
                    )
                }
                return false
            }
        }
    }

}
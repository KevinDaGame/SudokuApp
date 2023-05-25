package view

import controller.SudokuController
import io.github.shuoros.jterminal.JTerminal
import io.github.shuoros.jterminal.ansi.Color
import model.BorderDirection
import model.CellState
import model.SudokuCell
import java.lang.Integer.max
import java.lang.Integer.min

class SudokuView(private val controller: SudokuController) : IView {
    private var x: Int = 0
        set(value) {
            field = min(max(value, 0), controller.model.width - 1)
        }
    private var y: Int = 0
        set(value) {
            field = min(max(value, 0), controller.model.height - 1)
        }
    private var editorMode: EditorMode = EditorMode.DEFINITIVE
    private var showInvalidCells: Boolean = false
    private var showPencil: Boolean = false

    override fun render() {
        printSudoku()
        showInfoText()
    }

    private fun showInfoText() {
        JTerminal.println("Controls: w: up, a: left, s: down, d: right, space: toggle editor mode, c: toggle invalid cells, p: toggle pencil mode, [0-9]: set cell value, q: return to sudoku selection")

        JTerminal.print("Editor mode: ")
        JTerminal.println(editorMode.toString(), Color.YELLOW)

        JTerminal.print("Invalid cells: ")
        JTerminal.println(if (showInvalidCells) "shown" else "hidden", Color.YELLOW)

        JTerminal.print("Pencil mode: ")
        JTerminal.println(if (showPencil) "shown" else "hidden", Color.YELLOW)

        JTerminal.print("Colors: ")
        JTerminal.print("definitive", Color.WHITE)
        JTerminal.print(", ")
        JTerminal.print("provided", Color.YELLOW)
        JTerminal.print(", ")
        JTerminal.print("pencil", Color.BLUE)
        JTerminal.print(", ")
        JTerminal.print("invalid", Color.RED)
        JTerminal.print(", ")
        JTerminal.println("cursor", Color.GREEN)

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
        val value = getDisplayValue(cell)
        var foreground = when (cell.value.state) {
            CellState.EMPTY -> Color.WHITE
            CellState.DEFINITIVE -> Color.WHITE
            CellState.PENCIL -> Color.BLUE
            CellState.PROVIDED -> Color.YELLOW
        }
        if (isInvalid) foreground = Color.RED
        return ConsoleChar(value, foreground)
    }

    private fun getDisplayValue(cell: ViewSudokuCell): Char {
        return when(cell.value.state) {
            CellState.EMPTY -> '_'
            CellState.DEFINITIVE -> cell.value.value.digitToChar()
            CellState.PROVIDED -> cell.value.value.digitToChar()
            CellState.PENCIL -> {
                if (showPencil) {
                    cell.value.value.digitToChar()
                } else {
                    '_'
                }
            }
        }

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
            'p' -> {
                showPencil = !showPencil
                return false
            }
            'q' -> {
                ViewManager.instance.activeView = FileView()
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
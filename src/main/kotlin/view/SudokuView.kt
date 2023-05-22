package view

import controller.SudokuController
import io.github.shuoros.jterminal.JTerminal
import io.github.shuoros.jterminal.ansi.Color
import model.BorderDirection

class SudokuView(private val controller: SudokuController) : IView {
    private var x: Int = 0
    private var y: Int = 0

    init {
        while (true) {
            render()
            getInput()
        }
    }

    override fun render() {
        printSudoku()
        println("x: $x, y: $y")

    }

    private fun printSudoku() {
        println("\n".repeat(100)) // clear screen

        val viewModel = SudokuViewModel(controller.getBlocks(), controller.model.width, controller.model.height)
        val board = Array(viewModel.width * 2 + 1) { Array(viewModel.height * 2 + 1) { ConsoleChar() } }
        for ((y, row) in viewModel.board.withIndex()) {
            for ((x, cell) in row.withIndex()) {
                if (cell != null) {
                    board[y * 2 + 1][x * 2 + 1] = ConsoleChar(cell.value.value.digitToChar())


                    val horizontalBorder = ConsoleChar('-', Color.SILVER)
                    val verticalBorder = ConsoleChar('|', Color.SILVER)
                    for (border in cell.borders) {
                        when (border) {
                            BorderDirection.UP -> {
                                board[y * 2][x * 2 + 1] = horizontalBorder
                                if (!cell.borders.contains(BorderDirection.RIGHT)) {
                                    board[y * 2][x * 2 + 2] = horizontalBorder
                                }
                            }

                            BorderDirection.DOWN -> {
                                board[y * 2 + 2][x * 2 + 1] = horizontalBorder
                                if (!cell.borders.contains(BorderDirection.RIGHT)) {
                                    board[y * 2 + 2][x * 2 + 2] = horizontalBorder
                                }
                            }

                            BorderDirection.LEFT -> {
                                board[y * 2 + 1][x * 2] = verticalBorder
                                board[y * 2 + 2][x * 2] = verticalBorder
                                if(cell.borders.contains(BorderDirection.UP)) {
                                    board[y * 2][x * 2] = verticalBorder
                                }

                            }

                            BorderDirection.RIGHT -> {
                                board[y * 2 + 1][x * 2 + 2] = verticalBorder
                                board[y * 2 + 2][x * 2 + 2] = verticalBorder
                                if(cell.borders.contains(BorderDirection.UP)) {
                                    board[y * 2][x * 2 + 2] = verticalBorder
                                }
                            }
                        }
                    }


                }

            }
        }

        //show cursor
        board[y * 2][x * 2] = ConsoleChar(board[y*2][x*2].char, background = Color.RED)
        board[y * 2][x * 2 + 1] = ConsoleChar(board[y*2][x * 2 + 1].char, background = Color.RED)
        board[y * 2][x * 2 + 2] = ConsoleChar(board[y*2][x * 2 + 2].char, background = Color.RED)
        board[y * 2 + 1][x * 2] = ConsoleChar(board[y*2 + 1][x * 2].char, background = Color.RED)
        board[y * 2 + 1][x * 2 + 2] = ConsoleChar(board[y*2 + 1][x * 2 + 2].char, background = Color.RED)
        board[y * 2 + 2][x * 2] = ConsoleChar(board[y*2 + 2][x * 2].char, background = Color.RED)
        board[y * 2 + 2][x * 2 + 1] = ConsoleChar(board[y*2 + 2][x * 2 + 1].char, background = Color.RED)
        board[y * 2 + 2][x * 2 + 2] = ConsoleChar(board[y*2 + 2][x * 2 + 2].char, background = Color.RED)



        for (line in board) {
            for (cell in line) {
                JTerminal.print(cell.char.toString(), cell.color, cell.background)
            }
            println()
        }
    }

    fun getInput() {
        val input = System.`in`.read().toChar()
        println(input)
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
package view

import controller.SudokuController
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
        val board = Array(viewModel.width * 2 + 1) { Array(viewModel.height * 2 + 1) { ' ' } }
        for ((y, row) in viewModel.board.withIndex()) {
            for ((x, cell) in row.withIndex()) {
                if (cell != null) {
                    board[y * 2 + 1][x * 2 + 1] = cell.value.value.digitToChar()

                    for (border in cell.borders) {
                        when (border) {
                            BorderDirection.UP -> {
                                board[y * 2][x * 2 + 1] = '-'
                                if (!cell.borders.contains(BorderDirection.RIGHT)) {
                                    board[y * 2][x * 2 + 2] = '-'
                                }
                            }

                            BorderDirection.DOWN -> {
                                board[y * 2 + 2][x * 2 + 1] = '-'
                                if (!cell.borders.contains(BorderDirection.RIGHT)) {
                                    board[y * 2 + 2][x * 2 + 2] = '-'
                                }
                            }

                            BorderDirection.LEFT -> {
                                board[y * 2 + 1][x * 2] = '|'
                                board[y * 2 + 2][x * 2] = '|'
                                if(cell.borders.contains(BorderDirection.UP)) {
                                    board[y * 2][x * 2] = '|'
                                }

                            }

                            BorderDirection.RIGHT -> {
                                board[y * 2 + 1][x * 2 + 2] = '|'
                                board[y * 2 + 2][x * 2 + 2] = '|'
                                if(cell.borders.contains(BorderDirection.UP)) {
                                    board[y * 2][x * 2 + 2] = '|'
                                }
                            }
                        }
                    }


                }

            }
        }

        //show cursor
        board[y * 2][x * 2] = '#'
        board[y * 2][x * 2 + 1] = '#'
        board[y * 2][x * 2 + 2] = '#'
        board[y * 2 + 1][x * 2] = '#'
        board[y * 2 + 1][x * 2 + 2] = '#'
        board[y * 2 + 2][x * 2] = '#'
        board[y * 2 + 2][x * 2 + 1] = '#'
        board[y * 2 + 2][x * 2 + 2] = '#'



        for (line in board) {
            for (cell in line) {
                print(cell)
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
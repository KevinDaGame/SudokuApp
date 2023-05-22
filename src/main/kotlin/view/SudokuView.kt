package view

import controller.SudokuController
import model.SudokuCell
import java.lang.StringBuilder

class SudokuView(private val controller: SudokuController) : IView {
    var x: Int = 0
    var y: Int = 0

    init {
        while (true) {
            render()
            getInput()
        }
    }

    override fun render() {
        val blocks = controller.getBlocks()
        val board = Array(controller.model.height) { arrayOfNulls<SudokuCell>(controller.model.width) }
        val lines = Array(controller.model.height) { Array(controller.model.width) { Pair(false, false) } }

        for (block in blocks) {
            for (cell in block.cells) {
                board[cell.y][cell.x] = cell
                var borderX = false
                var borderY = false
                if (cell.x < controller.model.width && block.isLastX(cell.x, cell.y)) {
                    borderX = true
                }
                if (cell.y < controller.model.height && block.isLastY(cell.x, cell.y)) {
                    borderY = true
                }
                lines[cell.y][cell.x] = Pair(borderX, borderY)

            }
        }

        printHorizontalBorder(controller.model.width)
        for (i in 0..controller.model.height - 1) {
            printRow(board[i], lines[i])

        }
        println("x: $x, y: $y")

    }

    private fun printHorizontalBorder(width: Int) {
        println("|-----|-----|-----|")
    }

    private fun printRow(row: Array<SudokuCell?>, line: Array<Pair<Boolean, Boolean>>) {
        val horizontalLine = StringBuilder()
        var isFirst = true
        for (cell in row) {
            if (cell != null) {
                if (isFirst) {
                    print("|")
                    horizontalLine.append("|")
                    isFirst = false
                }
                print(cell.value.value)

                val currentLine = line[cell.x]
                if (currentLine.second) {
                    if (!currentLine.first) {
                        horizontalLine.append("-")
                    }
                    horizontalLine.append("-")
                } else {
                    if (!currentLine.first) {
                        horizontalLine.append(" ")
                    }
                    horizontalLine.append(" ")
                }
                if (currentLine.first) {
                    print("|")
                    horizontalLine.append("|")
                } else {
                    print(" ")
                }
            } else {
                if (!isFirst) {
                    print(" ")
                    horizontalLine.append(" ")
                    isFirst = true
                    continue
                }
                print("  ")
                horizontalLine.append("  ")
            }
        }
        println()
        println(horizontalLine)
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
package view

import controller.SudokuController
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
       printSudoku()
        println("x: $x, y: $y")

    }

    private fun printHorizontalBorder(width: Int) {
        println("|-----|-----|-----|")
    }

    private fun printSudoku() {
        val viewModel = SudokuViewModel(controller.getBlocks(), controller.model.width, controller.model.height)


        printHorizontalBorder(controller.model.width)
        for(line in viewModel.board) {
            printRow(line)
        }
    }

    private fun printRow(line: Array<ViewSudokuCell?>) {
        val horizontalLine = StringBuilder()
        var isFirst = true
        for (cell in line) {
            if (cell != null) {
                if (isFirst) {
                    print("|")
                    horizontalLine.append("|")
                    isFirst = false
                }
                print(cell.value.value)
                if (cell.borderY) {
                    if (!cell.borderX) {
                        horizontalLine.append("-")
                    }
                    horizontalLine.append("-")
                } else {
                    if (!cell.borderX) {
                        horizontalLine.append(" ")
                    }
                    horizontalLine.append(" ")
                }
                if (cell.borderX) {
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
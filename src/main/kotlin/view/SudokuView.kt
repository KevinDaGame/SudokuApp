package view

import controller.SudokuController
import model.SudokuCell

class SudokuView(private val controller: SudokuController) : IView {
    var x: Int = 1
    var y: Int = 1
    init {
        while(true) {
            render()
            getInput()
        }
    }
    override fun render() {
        val blocks = controller.getBlocks()
        val board = Array(controller.model.height) { arrayOfNulls<SudokuCell>(controller.model.width) }

        for (cell in blocks.flatMap { it.cells }) {
            board[cell.y][cell.x] = cell
        }
        println("-".repeat(controller.model.width))
        for (row in board) {
            print("|")
            for (cell in row) {
                if (cell != null) {
                    print(cell.value.value)
                } else {
                    print(" ")
                }
            }
            println("|")
        }
        println("-".repeat(controller.model.width))
        println("x: $x, y: $y")

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
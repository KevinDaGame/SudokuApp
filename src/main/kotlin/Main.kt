import controller.SudokuController
import fileReader.NxNFileReader
import fileReader.SamuraiFileReader
import view.SudokuView
import java.io.File

fun main(args: Array<String>) {
//    val file1 = File("src/main/resources/puzzle.samurai")
//    val fileReader1 = SamuraiFileReader()

    val file1 = File("src/main/resources/puzzle.9x9")
    val fileReader1 = NxNFileReader(9)

    val sudoku1 = fileReader1.parseSudoku(file1)

    val sudokuController = SudokuController(sudoku1)
    val sudokuView = SudokuView(sudokuController)
}
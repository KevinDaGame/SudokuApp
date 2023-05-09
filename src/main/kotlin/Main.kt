import fileReader.NxNFileReader
import fileReader.SamuraiFileReader
import java.io.File

fun main(args: Array<String>) {
    val file1 = File("src/main/resources/puzzle.9x9")
    val fileReader1 = NxNFileReader(9)
    val sudoku1 = fileReader1.parseSudoku(file1)

    val file2 = File("src/main/resources/puzzle.samurai")
    val fileReader2 = SamuraiFileReader()
    val sudoku2 = fileReader2.parseSudoku(file2)

    val solved = File("src/main/resources/solved.9x9")
    val solvedReader = NxNFileReader(9)
    val solvedSudoku = solvedReader.parseSudoku(solved)
    println(sudoku1.isSolved())
    val invalid = sudoku1.getInvalidCells()
    println(invalid)
}
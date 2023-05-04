import fileReader.NxNFileReader
import java.io.File

fun main(args: Array<String>) {
    val file = File("src/main/resources/puzzle.9x9")
    val fileReader = NxNFileReader(9)
    fileReader.parseSudoku(file)
}
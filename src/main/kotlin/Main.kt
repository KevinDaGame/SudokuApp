import fileReader.NxNFileReader
import fileReader.SamuraiFileReader
import java.io.File

fun main(args: Array<String>) {
    val file1 = File("src/main/resources/puzzle.9x9")
    val fileReader1 = NxNFileReader(9)
    fileReader1.parseSudoku(file1)

    val file2 = File("src/main/resources/puzzle.samurai")
    val fileReader2 = SamuraiFileReader()
    fileReader2.parseSudoku(file2)
}
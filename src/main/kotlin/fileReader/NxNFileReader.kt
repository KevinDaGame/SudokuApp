package fileReader

import model.*
import java.io.File

class NxNFileReader(private val size: Int) : IFileReader {
    private fun calcBlockWidth(): Int {
        when (size) {
            4 -> return 2
            6 -> return 3
            9 -> return 3
        }
        throw IllegalArgumentException("Invalid size")
    }

    private fun calcBlockHeight(): Int {
        when (size) {
            4 -> return 2
            6 -> return 2
            9 -> return 3
        }
        throw IllegalArgumentException("Invalid size")
    }

    override fun parseSudoku(file: File): SudokuModel {
        val builder = SudokuModelBuilder()
        builder.height(size)
        builder.width(size)

        val content = readFile(file)

        val grid = Array(size) { row ->
            Array(size) { col ->
                val index = row * size + col
                SudokuCell(CellValue(content[index].toString().toInt(), true), col, row)
            }
        }

        builder.addGroups(createRows(grid))
        builder.addGroups(createColumns(grid))
        builder.addGroups(createBlocks(grid, calcBlockWidth(), calcBlockHeight()))

        return builder.build()
    }

    companion object {
        fun register() {
            FileReaderFactory.registerFileReader(NxNFileReader(4), "4x4")
            FileReaderFactory.registerFileReader(NxNFileReader(6), "6x6")
            FileReaderFactory.registerFileReader(NxNFileReader(9), "9x9")
        }
    }
}
package fileReader

import model.CellValue
import model.SudokuCell
import model.SudokuGroup
import model.SudokuModel
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
        val content = readFile(file)

        val grid = Array(size) { row ->
            Array(size) { col ->
                val index = row * size + col
                SudokuCell(CellValue(content[index].toString().toInt(), true), row, col)
            }
        }

        val groups = mutableListOf<SudokuGroup>()

        groups.addAll(checkRows(grid))
        groups.addAll(checkColumns(grid))
        groups.addAll(checkBlocks(grid, calcBlockWidth(), calcBlockHeight()))

        return SudokuModel(groups, size, size)
    }
}
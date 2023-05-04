package fileReader

import model.SudokuCell
import model.SudokuGroup
import model.SudokuModel
import java.io.File
import java.util.*

interface IFileReader {
    /**
     * reads the file and returns the content as a string
     * @param file the file to read
     * @return the content of the file as a string
     */
    fun readFile(file: File): String {
        val scanner = Scanner(file)
        val contentBuilder = StringBuilder()
        while (scanner.hasNextLine()) {
            contentBuilder.append(scanner.nextLine())
        }
        return contentBuilder.toString()
    }

    fun parseSudoku(file: File): SudokuModel

    /**
     * default method for checking rows
     * @param sudoku the sudoku to check
     * @return a list of SudokuGroups
     */
    fun checkRows(sudoku: Array<Array<SudokuCell>>): List<SudokuGroup> {
        val groups = mutableListOf<SudokuGroup>()
        for (element in sudoku) {
            groups.add(SudokuGroup(element.toList()))
        }
        return groups
    }

    /**
     * default method for checking columns
     * @param sudoku the sudoku to check
     * @return a list of SudokuGroups
     */
    fun checkColumns(sudoku: Array<Array<SudokuCell>>): List<SudokuGroup> {
        val groups = mutableListOf<SudokuGroup>()
        for (i in sudoku.indices) {
            val column = mutableListOf<SudokuCell>()
            for (j in sudoku[i].indices) {
                column.add(sudoku[j][i])
            }
            groups.add(SudokuGroup(column))
        }
        return groups
    }

    /**
     * default method for checking blocks
     * @param sudoku the sudoku to check
     * @param blockWidth the width of the block
     * @param blockHeight the height of the block
     * @return a list of SudokuGroups
     */
    fun checkBlocks(sudoku: Array<Array<SudokuCell>>, blockWidth: Int, blockHeight: Int): List<SudokuGroup> {
        val groups = mutableListOf<SudokuGroup>()
        for (i in 1..sudoku.size / blockWidth) {
            for (j in 1..sudoku.size / blockHeight) {
                val block = mutableListOf<SudokuCell>()
                for (k in 1..blockWidth) {
                    for (l in 1..blockHeight) {
                        block.add(sudoku[(i - 1) * blockWidth + k - 1][(j - 1) * blockHeight + l - 1])
                    }
                }
                groups.add(SudokuGroup(block))
            }
        }
        return groups

    }


}
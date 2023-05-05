package fileReader

import model.CellValue
import model.SudokuCell
import model.SudokuGroup
import model.SudokuModel
import java.io.File

class SamuraiFileReader : IFileReader {
    override fun parseSudoku(file: File): SudokuModel {
        val content = readFile(file).chunked(81)
        val groups = mutableListOf<SudokuGroup>()
        val grids = mutableListOf<Array<Array<SudokuCell>>>()
        for (i in 0..4) {
            val startCoords = getStartCoordinates(i)
            val grid = Array(9) { row ->
                Array(9) { col ->
                    val index = row * 9 + col
                    SudokuCell(
                        CellValue(content[i][index].toString().toInt(), true),
                        startCoords.first + col,
                        startCoords.second + row
                    )
                }
            }
            grids.add(grid)

            groups.addAll(checkRows(grid))
            groups.addAll(checkColumns(grid))
            groups.addAll(checkBlocks(grid, 3, 3))
        }
        return SudokuModel(groups, 21, 21)
    }

    /**
     * Returns the starting coordinates of the grid at the given index.
     * @param index The index of the grid.
     * @return The starting coordinates of the grid in the format (x, y).
     * @TODO: This is a bit of a hack. It would be better to be able to calculate the coordinates. However, currently this is out of the scope of the project.
     */
    private fun getStartCoordinates(index: Int): Pair<Int, Int> {
        when (index) {
            0 -> return Pair(0, 0)
            1 -> return Pair(12, 0)
            2 -> return Pair(6, 6)
            3 -> return Pair(0, 12)
            4 -> return Pair(12, 12)
        }
        throw IllegalArgumentException("Invalid index")
    }
}
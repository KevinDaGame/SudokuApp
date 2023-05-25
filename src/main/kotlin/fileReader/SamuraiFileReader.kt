package fileReader

import model.CellValue
import model.SudokuCell
import model.SudokuModel
import model.SudokuModelBuilder
import java.io.File

class SamuraiFileReader : IFileReader {
    override fun parseSudoku(file: File): SudokuModel {
        val builder = SudokuModelBuilder()
        builder.height(21)
        builder.width(21)
        val content = readFile(file).chunked(81)

        val grids = mutableListOf<Array<Array<SudokuCell>>>()
        val cells = HashMap<Pair<Int, Int>, SudokuCell>()
        for (i in 0..4) {
            val startCoords = getStartCoordinates(i)
            val grid = Array(9) { row ->
                Array(9) { col ->
                    val index = row * 9 + col
                    val value = CellValue(content[i][index].toString().toInt(), content[i][index].toString().toInt() > 0)
                    val cellCoords = Pair(startCoords.first + col, startCoords.second + row)
                    val cell = cells.getOrPut(cellCoords) {
                        SudokuCell(
                            value,
                            cellCoords.first,
                            cellCoords.second
                        )
                    }

                    //overwrite if previous is 0 and current is not 0
                    if (cell.value.value == 0 && value.value != 0) {
                        cell.value = value
                    }
                    cell
                }
            }
            grids.add(grid)

            builder.addGroups(createRows(grid))
            builder.addGroups(createColumns(grid))
            builder.addGroups(createBlocks(grid, 3, 3))
        }
        return builder.build()
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

    companion object {
        fun register() {
            FileReaderFactory.registerFileReader(SamuraiFileReader(), "samurai")
        }
    }
}
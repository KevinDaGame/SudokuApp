package fileReader

import model.*
import java.io.File

class JigsawFileReader : IFileReader {
    override fun parseSudoku(file: File): SudokuModel {
        val builder = SudokuModelBuilder()
        builder.height(9)
        builder.width(9)
        builder.addValidCharacters(('1'.. '9').toSet())

        val content = readFile(file).replace("SumoCueV1=", "")
        val fields = content.split("=")
        val blocks = mutableMapOf<Int, MutableList<SudokuCell>>()

        for (i in 0..8) {
            blocks[i] = mutableListOf()
        }

        val grid = Array(9) { row ->
            Array(9) { col ->
                val index = row * 9 + col
                val value = fields[index][0].digitToInt()
                val cell = getCell(value, col, row)
                blocks[fields[index][2].digitToInt()]?.add(cell)
                cell


            }
        }

        builder.addGroups(createRows(grid))
        builder.addGroups(createColumns(grid))
        builder.addGroups(blocks.map { SudokuBlock(it.value) })

        return builder.build()
    }

    companion object {
        fun register() {
            FileReaderFactory.registerFileReader(JigsawFileReader(), "jigsaw")
        }
    }
}
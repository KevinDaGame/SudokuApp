package fileReader

import model.SudokuModel
import java.io.File

class NxNFileReader(val size: Int, val file: File): IFileReader {
    override fun parseSudoku(file: File): SudokuModel {
        TODO("Not yet implemented")
    }
}
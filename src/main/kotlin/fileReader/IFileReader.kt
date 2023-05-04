package fileReader

import model.SudokuModel
import java.io.File

interface IFileReader {
    fun parseSudoku(file: File): SudokuModel
}
package controller

import fileReader.FileReaderFactory
import view.SudokuView
import view.ViewManager
import java.io.File

class FileController : Controller {
    fun openSudoku(file: File) {

        println(file.extension)
        val fileReader = FileReaderFactory.getFileReader(file.extension)
        if (fileReader == null) {
            println("No file reader found for extension ${file.extension}")
            return
        }
        val sudoku = fileReader.parseSudoku(file)
        ViewManager.instance.activeView = SudokuView(SudokuController(sudoku))
    }
}
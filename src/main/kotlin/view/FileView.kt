package view

import controller.SudokuController
import fileReader.NxNFileReader
import java.io.File
import javax.swing.JFileChooser


class FileView: IView {
    override fun render() {
        println("Open a sudoku\n" +
                "0: Open a file\n" +
                "1: Open standard sudoku"
        )
    }

    override fun handleInput(input: Char) {
        when(input) {
            '0' -> {
                val file = openFilePicker()
                if(file != null) {
                    openSudoku(file)
                }
            }
            '1' -> {
                val file = File("C:\\Users\\kevin\\Documents\\GitHub\\SudokuApp\\src\\main\\resources\\puzzles\\puzzle.9x9")
                openSudoku(file)
            }
        }
    }

    private fun openSudoku(file: File) {
        val fileReader = NxNFileReader(9)
        val sudoku = fileReader.parseSudoku(file)
        ViewManager.instance.activeView = SudokuView(SudokuController(sudoku))
    }

    private fun openFilePicker(): File? {
        val fc = JFileChooser()
        return if (JFileChooser.APPROVE_OPTION == fc.showOpenDialog(null)) {
            fc.selectedFile
        } else {
            null
        }
    }
}
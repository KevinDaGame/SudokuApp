package view

import controller.SudokuController
import fileReader.NxNFileReader
import fileReader.SamuraiFileReader
import io.github.shuoros.jterminal.JTerminal
import java.io.File
import javax.swing.JFileChooser


class FileView: IView {
    override fun render() {
        JTerminal.println("Open a sudoku\n" +
                "0: Open a file\n" +
                "1: Open standard sudoku\n" +
                "2: Samurai\n" +
                "3: Go back\n"
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
            '2' -> {
                val file = File("C:\\Users\\kevin\\Documents\\GitHub\\SudokuApp\\src\\main\\resources\\puzzles\\puzzle.samurai")
                openSudoku(file, true)
            }
            '3' -> ViewManager.instance.activeView = MainView()
        }
    }

    private fun openSudoku(file: File, isSamurai: Boolean = false) {

        val fileReader = if (isSamurai) SamuraiFileReader() else NxNFileReader(9)
        val sudoku = fileReader.parseSudoku(file)
        ViewManager.instance.activeView = SudokuView(SudokuController(sudoku))
    }

    private fun openFilePicker(): File? {
        JTerminal.println("Opening file picker")
        val fc = JFileChooser()
        return if (JFileChooser.APPROVE_OPTION == fc.showOpenDialog(null)) {
            fc.selectedFile
        } else {
            null
        }
    }
}
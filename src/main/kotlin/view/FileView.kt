package view

import controller.FileController
import io.github.shuoros.jterminal.JTerminal
import java.io.File
import javax.swing.JFileChooser


class FileView : IView {
    private val controller: FileController = FileController()
    override fun render() {
        JTerminal.println(
            "Open a sudoku\n" +
                    "0: Open a file\n" +
                    "1: Open standard sudoku(dev only)\n" +
                    "2: Samurai(dev only)\n" +
                    "3: Go back\n"
        )
    }

    override fun handleInput(input: Char) {
        when (input) {
            '0' -> {
                val file = openFilePicker()
                if (file != null) {
                    controller.openSudoku(file)
                }
            }

            '1' -> {
                val file =
                    File("C:\\Users\\kevin\\Documents\\GitHub\\SudokuApp\\src\\main\\resources\\puzzles\\puzzle.9x9")
                controller.openSudoku(file)
            }

            '2' -> {
                val file =
                    File("C:\\Users\\kevin\\Documents\\GitHub\\SudokuApp\\src\\main\\resources\\puzzles\\puzzle.samurai")
                controller.openSudoku(file)
            }

            '3' -> ViewManager.instance.activeView = MainView()
        }
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
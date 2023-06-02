package view

import controller.FileController
import io.github.shuoros.jterminal.JTerminal
import java.io.File
import javax.swing.JFileChooser


class FileView : IView {
    private val controller: FileController = FileController()
    private val puzzleDir = System.getProperty("user.dir") + "\\src\\main\\resources\\puzzles"
    override fun render() {
        JTerminal.println(
            "Choose a sudoku\n" +
                    "0: Open a file\n" +
                    "1: Open standard sudoku(dev only)\n" +
                    "2: Samurai(dev only)\n" +
                    "3: Jigsaw(dev only)\n" +
                    "4: 4x4(dev only)\n" +
                    "5: 6x6(dev only)\n" +
                    "6: Go back\n"
        )
    }

    override fun handleInput(input: Char): Boolean {
        when (input) {
            '0' -> {
                val file = openFilePicker()
                if (file != null) {
                    controller.openSudoku(file)
                }
            }

            '1' -> {
                val file =
                    File("$puzzleDir\\puzzle.9x9")
                controller.openSudoku(file)
            }

            '2' -> {
                val file =
                    File("$puzzleDir\\puzzle.samurai")
                controller.openSudoku(file)
            }

            '3' -> {
                val file = File("$puzzleDir\\puzzle.jigsaw")
                controller.openSudoku(file)
            }
            '4' -> {
                val file = File("$puzzleDir\\puzzle.4x4")
                controller.openSudoku(file)
            }
            '5' -> {
                val file = File("$puzzleDir\\puzzle.6x6")
                controller.openSudoku(file)
            }
            '6' -> ViewManager.instance.activeView = MainView()
        }
        return false
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
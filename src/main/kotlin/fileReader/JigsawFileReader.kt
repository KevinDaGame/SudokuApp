package fileReader

import model.SudokuModel
import java.io.File

class JigsawFileReader: IFileReader {
    override fun parseSudoku(file: File): SudokuModel {
        TODO("Not yet implemented")
    }

    companion object {
        fun register() {
            FileReaderFactory.registerFileReader(JigsawFileReader(), "jigsaw")
        }
    }
}
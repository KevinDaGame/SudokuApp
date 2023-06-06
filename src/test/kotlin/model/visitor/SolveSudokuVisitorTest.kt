package model.visitor

import fileReader.NxNFileReader
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.io.File

class SolveSudokuVisitorTest {

    @Test
    fun visit() {
        // Arrange
        val fileReader = NxNFileReader(9)
        val file = File(System.getProperty("user.dir") + "\\src\\test\\resources", "puzzle.9x9")
        val model = fileReader.parseSudoku(file)
        val visitor = SolveSudokuVisitor()
        // Act
        model.accept(visitor)

        // Assert
        assertTrue(model.isSolved())

    }
}
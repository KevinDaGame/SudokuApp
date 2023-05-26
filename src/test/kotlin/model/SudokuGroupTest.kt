package model

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class SudokuGroupTest {

    @Test
    fun isSolved_True_if_Correct() {
        // Arrange
        val correctCells = mutableListOf<SudokuCell>()
        for (i in 1..9) {
            correctCells.add(SudokuCell(CellValue(i, CellState.DEFINITIVE), 0, 0))
        }
        val validGroup = SudokuGroup(correctCells)
        // Act
        val validGroupResult = validGroup.isSolved()

        // Assert
        assertTrue(validGroupResult)
    }

    @Test
    fun isSolved_False_if_incorrect() {
        // Arrange
        val incorrectCells = mutableListOf<SudokuCell>()
        for (i in 1..8) {
            incorrectCells.add(SudokuCell(CellValue(i, CellState.DEFINITIVE), 0, 0))
        }
        incorrectCells.add(SudokuCell(CellValue(8, CellState.DEFINITIVE), 0, 0))
        val invalidGroup = SudokuGroup(incorrectCells)
        // Act
        val invalidGroupResult = invalidGroup.isSolved()

        // Assert
        assertFalse(invalidGroupResult)
    }

    @Test
    fun isSolved_False_if_incomplete() {
        // Arrange
        val incompleteCells = mutableListOf<SudokuCell>()
        for (i in 1..8) {
            incompleteCells.add(SudokuCell(CellValue(i, CellState.DEFINITIVE), 0, 0))
        }
        incompleteCells.add(SudokuCell(CellValue(0, CellState.DEFINITIVE), 0, 0))
        val incompleteGroup = SudokuGroup(incompleteCells)
        // Act
        val incompleteGroupResult = incompleteGroup.isSolved()

        // Assert
        assertFalse(incompleteGroupResult)
    }

    @Test
    fun isSolved_False_if_temporary() {
        // Arrange
        val temporaryCells = mutableListOf<SudokuCell>()
        for (i in 1..8) {
            temporaryCells.add(SudokuCell(CellValue(i, CellState.DEFINITIVE), 0, 0))
        }
        temporaryCells.add(SudokuCell(CellValue(9, CellState.PENCIL), 0, 0))
        val temporaryGroup = SudokuGroup(temporaryCells)
        // Act
        val temporaryGroupResult = temporaryGroup.isSolved()

        // Assert
        assertFalse(temporaryGroupResult)
    }

    @Test
    fun getInvalidCells_Empty_If_Valid() {
        //Arrange
        val correctCells = mutableListOf<SudokuCell>()
        for (i in 1..9) {
            correctCells.add(SudokuCell(CellValue(i, CellState.DEFINITIVE), 0, 0))
        }

        val validGroup = SudokuGroup(correctCells)
        //Act
        val result = validGroup.getInvalidCells()
        //Assert
        assertTrue(result.isEmpty())


    }

    @Test
    fun getInvalidCells_Not_Empty_If_Invalid() {
        //Arrange
        val invalidCells = mutableListOf<SudokuCell>()
        for (i in 1..8) {
            invalidCells.add(SudokuCell(CellValue(i, CellState.DEFINITIVE), 0, 0))
        }
        invalidCells.add(SudokuCell(CellValue(8, CellState.DEFINITIVE), 0, 0))

        val invalidGroup = SudokuGroup(invalidCells)
        //Act
        val result = invalidGroup.getInvalidCells()
        //Assert
        assertFalse(result.isEmpty())


    }
}
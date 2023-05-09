package model

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class SudokuCellTest {

    @Test
    fun isSolved() {
        // Arrange
        val validCell = SudokuCell(CellValue(1, true), 0, 0)
        val unconfirmedCell = SudokuCell(CellValue(1, false), 0, 0)
        val emptyCell = SudokuCell(CellValue(0, false), 0, 0)
        // Act
        val validCellSolved = validCell.isSolved()
        val unconfirmedCellSolved = unconfirmedCell.isSolved()
        val emptyCellSolved = emptyCell.isSolved()
        // Assert
        assertTrue(validCellSolved)
        assertFalse(unconfirmedCellSolved)
        assertFalse(emptyCellSolved)
    }

    @Test
    fun getInvalidCells() {
        // Arrange
        val validCell = SudokuCell(CellValue(1, true), 0, 0)
        val unconfirmedCell = SudokuCell(CellValue(1, false), 0, 0)
        val emptyCell = SudokuCell(CellValue(0, false), 0, 0)
        // Act
        val validCellSolved = validCell.getInvalidCells()
        val unconfirmedCellSolved = unconfirmedCell.getInvalidCells()
        val emptyCellSolved = emptyCell.getInvalidCells()
        // Assert
        assertEquals(1, validCellSolved.size)
        assertEquals(0, unconfirmedCellSolved.size)
        assertEquals(0, emptyCellSolved.size)

    }
}
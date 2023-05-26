package model

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class SudokuCellTest {

    @Test
    fun isSolved() {
        // Arrange
        val validCell = SudokuCell(CellValue(1, CellState.DEFINITIVE), 0, 0)
        val unconfirmedCell = SudokuCell(CellValue(1, CellState.PENCIL), 0, 0)
        val emptyCell = SudokuCell(CellValue(0, CellState.EMPTY), 0, 0)
        val providedCell = SudokuCell(CellValue(1, CellState.PROVIDED), 0, 0)
        // Act
        val validCellSolved = validCell.isSolved()
        val unconfirmedCellSolved = unconfirmedCell.isSolved()
        val emptyCellSolved = emptyCell.isSolved()
        val providedCellSolved = providedCell.isSolved()
        // Assert
        assertTrue(validCellSolved)
        assertTrue(providedCellSolved)
        assertFalse(unconfirmedCellSolved)
        assertFalse(emptyCellSolved)
    }

    @Test
    fun getInvalidCells() {
        // Arrange
        val validCell = SudokuCell(CellValue(1, CellState.DEFINITIVE), 0, 0)
        val unconfirmedCell = SudokuCell(CellValue(1, CellState.PENCIL), 0, 0)
        val emptyCell = SudokuCell(CellValue(0, CellState.EMPTY), 0, 0)
        val providedCell = SudokuCell(CellValue(1, CellState.PROVIDED), 0, 0)
        // Act
        val validCellSolved = validCell.getInvalidCells()
        val providedCellSolved = providedCell.getInvalidCells()
        val unconfirmedCellSolved = unconfirmedCell.getInvalidCells()
        val emptyCellSolved = emptyCell.getInvalidCells()
        // Assert
        assertEquals(1, validCellSolved.size)
        assertEquals(1, providedCellSolved.size)
        assertEquals(0, unconfirmedCellSolved.size)
        assertEquals(0, emptyCellSolved.size)

    }
}
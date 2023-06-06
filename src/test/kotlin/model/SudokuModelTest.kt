package model

import model.visitor.IVisitor
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.mockito.Mockito
import org.mockito.Mockito.mock

class SudokuModelTest {

    @Test
    fun isSolved_valid() {
        //Arrange
        val validModel = getValidModel()
        //act
        val result = validModel.isSolved()
        //assert
        assertTrue(result)
    }

    @Test
    fun isSolved_invalid() {
        //Arrange
        val invalidModel = getInvalidModel()
        //act
        val result = invalidModel.isSolved()
        //assert
        assertFalse(result)
    }

    @Test
    fun isSolved_empty() {
        //Arrange
        val emptyModel = getEmptyModel()
        //act
        val result = emptyModel.isSolved()
        //assert
        assertFalse(result)
    }


    @Test
    fun getInvalidCells_empty_if_no_invalid() {
        //Arrange
        val validModel = getValidModel()
        //act
        val result = validModel.getInvalidCells()
        //assert
        assertTrue(result.isEmpty())
    }

    @Test
    fun getInvalidCells_empty_if_all_empty() {
        //Arrange
        val emptyModel = getEmptyModel()
        //act
        val result = emptyModel.getInvalidCells()
        //assert
        assertTrue(result.isEmpty())
    }

    @Test
    fun getInvalidCells_returns_invalid_cells() {
        //Arrange
        val invalidModel = getInvalidModel()
        //act
        val result = invalidModel.getInvalidCells()
        //assert
        assertEquals(81, result.size)
    }
    @Test
    fun accept() {
        //Arrange
        val model = getValidModel()
        val visitor = mock(IVisitor::class.java)
        //act
        model.accept(visitor)
        //assert
        Mockito.verify(visitor).visit(model)
    }

    private fun getValidModel(): SudokuModel {
        val groups = mutableListOf<SudokuGroup>()
        for (i in 0..8) {
            val cells = mutableListOf<SudokuCell>()
            for (j in 0..8) {
                cells.add(SudokuCell(CellValue(j + 1, CellState.DEFINITIVE), j, i))
            }
            groups.add(SudokuGroup(cells))
        }
        return SudokuModel(groups, setOf('1', '2', '3', '4', '5', '6', '7', '8', '9'), 9, 9)
    }

    private fun getInvalidModel(): SudokuModel {
        val groups = mutableListOf<SudokuGroup>()
        for (i in 0..8) {
            val cells = mutableListOf<SudokuCell>()
            for (j in 0..8) {
                cells.add(SudokuCell(CellValue(1, CellState.DEFINITIVE), j, i))
            }
            groups.add(SudokuGroup(cells))
        }
        return SudokuModel(groups, setOf('1', '2', '3', '4', '5', '6', '7', '8', '9'), 9, 9)
    }
    private fun getEmptyModel(): SudokuModel {
        val groups = mutableListOf<SudokuGroup>()
        for (i in 0..8) {
            val cells = mutableListOf<SudokuCell>()
            for (j in 0..8) {
                cells.add(SudokuCell(CellValue(0, CellState.EMPTY), j, i))
            }
            groups.add(SudokuGroup(cells))
        }
        return SudokuModel(groups, setOf('1', '2', '3', '4', '5', '6', '7', '8', '9'), 9, 9)
    }
}

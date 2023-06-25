package controller

import model.*
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class SudokuControllerTest {

    @Test
    fun getBlocks() {
        // Arrange
        val model = getValidModel()
        val expectedBlocks = model.children.filterIsInstance<SudokuBlock>()
        val controller = SudokuController(model)
        //Act
        val blocks = controller.getBlocks()
        //Assert
        assertEquals(expectedBlocks, blocks)
    }

    @Test
    fun setCellValue_valid_input() {
        // Arrange
        val model = getValidModel()
        val controller = SudokuController(model)
        //Act
        val newValue = 9
        controller.setCellValue(0,0, newValue, CellState.DEFINITIVE)
        //Assert
        assertEquals(newValue, controller.model.children.flatMap { it.getCells() }.first { it.x == 0 && it.y == 0 }.value.value)
    }

    @Test
    fun setCellValue_input_invalid() {
        // Arrange
        val model = getValidModel()
        val controller = SudokuController(model)
        //Act
        val newValue = 11
        controller.setCellValue(0,0, newValue, CellState.DEFINITIVE)
        //Assert
        assertNotEquals(newValue, controller.model.children.flatMap { it.getCells() }.first { it.x == 0 && it.y == 0 }.value.value)
    }

    @Test
    fun setCellValue_input_dont_set_provided() {
        // Arrange
        val model = getValidModel()
        val controller = SudokuController(model)
        //Act
        val newValue = 9
        controller.setCellValue(0,0, newValue, CellState.PROVIDED)
        //Assert
        assertNotEquals(newValue, controller.model.children.flatMap { it.getCells() }.first { it.x == 0 && it.y == 0 }.value.value)
    }
    @Test
    fun getInvalidCells() {
        // Arrange
        val model = getValidModel()
        val controller = SudokuController(model)
        val expectedInvalidCells = model.getInvalidCells()
        //Act
        val invalidCells = controller.getInvalidCells()
        //Assert
        assertEquals(expectedInvalidCells, invalidCells)
    }

    @Test
    fun getModel() {
        // Arrange
        val model = getValidModel()
        val controller = SudokuController(model)
        //Act
        val m = controller.model
        //Assert
        assertEquals(model, m)
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
}
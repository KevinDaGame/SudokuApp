package model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class SudokuBlockTest {

    @Test
    fun getBorders() {
        val block = SudokuBlock(
            listOf(
                SudokuCell(CellValue(1, CellState.DEFINITIVE), 0, 0),
                SudokuCell(CellValue(2, CellState.DEFINITIVE), 0, 1),
                SudokuCell(CellValue(3, CellState.DEFINITIVE), 0, 2),
                SudokuCell(CellValue(4, CellState.DEFINITIVE), 1, 0),
                SudokuCell(CellValue(5, CellState.DEFINITIVE), 1, 1),
                SudokuCell(CellValue(6, CellState.DEFINITIVE), 1, 2),
                SudokuCell(CellValue(7, CellState.DEFINITIVE), 2, 0),
                SudokuCell(CellValue(8, CellState.DEFINITIVE), 2, 1),
                SudokuCell(CellValue(9, CellState.DEFINITIVE), 2, 2)
            )
        )


        val result1 = block.getBorders(0, 0)
        val result2 = block.getBorders(0, 1)
        val result3 = block.getBorders(0, 2)
        val result4 = block.getBorders(1, 0)
        val result5 = block.getBorders(1, 1)
        val result6 = block.getBorders(1, 2)
        val result7 = block.getBorders(2, 0)
        val result8 = block.getBorders(2, 1)
        val result9 = block.getBorders(2, 2)

        //assert
        assertEquals(2, result1.size)
        assertEquals(1, result2.size)
        assertEquals(2, result3.size)
        assertEquals(1, result4.size)
        assertEquals(0, result5.size)
        assertEquals(1, result6.size)
        assertEquals(2, result7.size)
        assertEquals(1, result8.size)
        assertEquals(2, result9.size)

    }
}
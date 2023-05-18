package model

class SudokuCell(var value: CellValue, val x: Int, val y: Int): ICheckable {
    override fun isSolved(): Boolean {
        return isDefinitiveAndFilled()

    }

    override fun getInvalidCells(): List<SudokuCell> {
        if (isDefinitiveAndFilled()) {
            return listOf(this)
        }
        return listOf()
    }

    private fun isDefinitiveAndFilled(): Boolean {
        return value.value != 0 && value.isDefinitive
    }

    override fun toString(): String {
        return "SudokuCell(value=$value, x=$x, y=$y)"
    }
}
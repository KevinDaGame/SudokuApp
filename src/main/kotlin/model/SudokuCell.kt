package model

class SudokuCell(var value: CellValue, val x: Int, val y: Int): ICheckable {
    override fun isSolved(): Boolean {
        return !(value.value == 0 || !value.isDefinitive)
    }

    override fun getInvalidCells(): List<SudokuCell> {
        if(value.isDefinitive && value.value != 0) {
            return listOf(this)
        }
        return listOf()
    }

    override fun toString(): String {
        return "SudokuCell(value=$value, x=$x, y=$y)"
    }
}
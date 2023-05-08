package model

class SudokuCell(var value: CellValue, val x: Int, val y: Int) {
    override fun toString(): String {
        return "SudokuCell(value=$value, x=$x, y=$y)"
    }
}
package model

import model.visitor.IVisitor

class SudokuModel(val sudokuGroups: List<SudokuGroup>, val validCharacters: Set<Char>, val height: Int, val width: Int) : ICheckable {
    override fun isSolved(): Boolean {
        return sudokuGroups.all { it.isSolved() }
    }

    override fun getInvalidCells(): List<SudokuCell> {
        val invalidCells = mutableListOf<SudokuCell>()
        for (group in sudokuGroups) {
            invalidCells.addAll(group.getInvalidCells())
        }
        return invalidCells.distinct()
    }

    fun accept(visitor: IVisitor) {
        visitor.visit(this)
    }
}
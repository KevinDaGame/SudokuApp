package model

import model.visitor.IVisitor

class SudokuModel(val children: List<ICheckable>, val validCharacters: Set<Char>, val height: Int, val width: Int) : ICheckable {
    override fun isSolved(): Boolean {
        return children.all { it.isSolved() }
    }

    override fun getInvalidCells(): List<SudokuCell> {
        val invalidCells = mutableListOf<SudokuCell>()
        for (group in children) {
            invalidCells.addAll(group.getInvalidCells())
        }
        return invalidCells.distinct()
    }
    override fun getCells(): List<SudokuCell> {
            return children.flatMap { it.getCells() }
    }

    fun accept(visitor: IVisitor) {
        visitor.visit(this)
    }

}
package model

class SudokuModelBuilder {
    private var width: Int = 0
    private var height: Int = 0
    private var groups: MutableList<SudokuGroup> = mutableListOf()

    fun width(width: Int): SudokuModelBuilder {
        this.width = width
        return this
    }

    fun height(height: Int): SudokuModelBuilder {
        this.height = height
        return this
    }

    fun addGroups(groups: List<SudokuGroup>): SudokuModelBuilder {
        this.groups.addAll(groups)
        return this
    }

    fun build(): SudokuModel {
        return SudokuModel(groups, width, height)
    }
}

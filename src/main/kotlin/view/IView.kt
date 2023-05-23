package view

interface IView {
    fun render()

    fun handleInput(input: Char)
}
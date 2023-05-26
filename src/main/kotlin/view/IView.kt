package view

interface IView {
    fun render()

    /**
     * @return true if the next input in line may be handled, false if the next inputs should be ignored
     * This allows you to queue up movement before pressing enter.
     */
    fun handleInput(input: Char): Boolean
}
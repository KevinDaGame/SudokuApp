package view

import io.github.shuoros.jterminal.JTerminal
import java.util.*

class ViewManager private constructor(var activeView: IView = MainView()) {
    fun start() {
        val scanner = Scanner(System.`in`)
        while (true) {
            flushView()
            activeView.render()
            val input = scanner.nextLine()

            for (char in input) {
                if (!activeView.handleInput(char)) break
            }
        }
    }

    private fun flushView() {
        JTerminal.println("\n".repeat(100)) // clear screen
    }

    companion object {
        val instance: ViewManager = ViewManager()

    }

}


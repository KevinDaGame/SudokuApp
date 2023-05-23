package view

import java.util.*

class ViewManager private constructor(var activeView: IView = MainView()) {
    fun start() {
        val scanner = Scanner(System.`in`)
        while(true) {
            activeView.render()
            activeView.handleInput(scanner.next().first())


        }
    }

companion object {
        val instance: ViewManager = ViewManager()

}

}


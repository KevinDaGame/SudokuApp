package view

import io.github.shuoros.jterminal.JTerminal
import io.github.shuoros.jterminal.ansi.Color

class MainView : IView {

    override fun render() {
        JTerminal.println(
            " __        __   _                            _          ____            _       _             _                \n" +
                    " \\ \\      / /__| | ___ ___  _ __ ___   ___  | |_ ___   / ___| _   _  __| | ___ | | ___   _   / \\   _ __  _ __  \n" +
                    "  \\ \\ /\\ / / _ \\ |/ __/ _ \\| '_ ` _ \\ / _ \\ | __/ _ \\  \\___ \\| | | |/ _` |/ _ \\| |/ / | | | / _ \\ | '_ \\| '_ \\ \n" +
                    "   \\ V  V /  __/ | (_| (_) | | | | | |  __/ | || (_) |  ___) | |_| | (_| | (_) |   <| |_| |/ ___ \\| |_) | |_) |\n" +
                    "    \\_/\\_/ \\___|_|\\___\\___/|_| |_| |_|\\___|  \\__\\___/  |____/ \\__,_|\\__,_|\\___/|_|\\_\\\\__,_/_/   \\_\\ .__/| .__/ \n" +
                    "                                                                                                  |_|   |_|    \n",
            Color.AQUA
        )

        JTerminal.println(
            "Please make a choice:\n" +
                    "0: Choose a soduku\n" +
                    "1: Exit"
        )
    }

    override fun handleInput(input: Char): Boolean {
        when (input.toString().toIntOrNull()) {
            0 -> openFilePicker()
            1 -> System.exit(0)
        }
        return false
    }

    private fun openFilePicker() {
        ViewManager.instance.activeView = FileView()
    }

}
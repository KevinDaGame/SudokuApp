package view

import io.github.shuoros.jterminal.ansi.Color

data class ConsoleChar(val char: Char = ' ', val color: Color = Color.WHITE, val background: Color = Color.DEFAULT)
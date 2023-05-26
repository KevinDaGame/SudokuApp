package view

enum class EditorMode {
    DEFINITIVE,
    PENCIL;

    fun getNext(): EditorMode {
        return when (this) {
            DEFINITIVE -> PENCIL
            PENCIL -> DEFINITIVE
        }
    }
}
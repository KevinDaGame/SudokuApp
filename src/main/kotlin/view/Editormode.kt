package view

enum class Editormode {
    DEFINITIVE,
    PENCIL;

    fun getNext(): Editormode {
        return when (this) {
            DEFINITIVE -> PENCIL
            PENCIL -> DEFINITIVE
        }
    }
}
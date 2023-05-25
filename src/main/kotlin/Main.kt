import fileReader.JigsawFileReader
import fileReader.NxNFileReader
import fileReader.SamuraiFileReader
import view.ViewManager

fun main() {
    NxNFileReader.register()
    SamuraiFileReader.register()
    JigsawFileReader.register()

    val viewManager = ViewManager.instance
    viewManager.start()

}
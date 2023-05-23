package fileReader

object FileReaderFactory {
    private val registeredFileReaders: MutableMap<String,IFileReader> = mutableMapOf()

    fun getFileReader(extension: String): IFileReader? {
        return registeredFileReaders[extension]
    }

    fun registerFileReader(reader: IFileReader, extension: String) {
        registeredFileReaders[extension] = reader
    }

}
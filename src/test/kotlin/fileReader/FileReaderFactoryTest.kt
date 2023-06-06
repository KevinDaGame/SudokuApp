package fileReader

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class FileReaderFactoryTest {

    @Test
    fun getFileReader() {
        // Arrange
        FileReaderFactory.registerFileReader(JigsawFileReader(), "jigsaw")
        // Act
        val found = FileReaderFactory.getFileReader("jigsaw")
        val notFound = FileReaderFactory.getFileReader("notFound")
        // Assert
        assertNotNull(found)
        assertNull(notFound)
    }
}
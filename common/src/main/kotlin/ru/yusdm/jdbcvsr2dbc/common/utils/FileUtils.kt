package ru.yusdm.jdbcvsr2dbc.common.utils

import java.io.File
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.StandardCopyOption.REPLACE_EXISTING

object FileUtils {
    fun createFileFromResource(fileNamePrefix: String, fileNameSuffix: String, resourcePath: String): File {
        FileUtils::class.java.getResourceAsStream(resourcePath).use { inputStream ->
            val tempFile: Path = Files.createTempFile(fileNamePrefix, fileNameSuffix)
            Files.copy(inputStream, tempFile, REPLACE_EXISTING)
            return tempFile.toFile()
        }
    }
}
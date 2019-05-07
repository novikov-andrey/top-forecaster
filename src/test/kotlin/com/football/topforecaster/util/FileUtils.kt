package com.football.topforecaster.util

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.io.File

class FileUtils {

    companion object {
        fun loadDocument(fileName: String): Document {
            val resource = FileUtils::class.java.classLoader.getResource(fileName)
            return Jsoup.parse(File(resource!!.file), Charsets.UTF_8.name())
        }
    }
}

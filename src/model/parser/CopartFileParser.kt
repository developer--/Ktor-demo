package com.manqana.model.parser

import org.jsoup.Jsoup
import java.io.File

object CopartFileParser {

    fun parse(filePath: String) : String {
        val file = File(filePath)
        if (file.exists()) {
            val txtContent = file.readText()
            parseContent(txtContent)
            return txtContent
        }
        return ""
    }

    fun parseContent(content: String) {
        val td =Jsoup.parse(content).select("td")
        td.forEach {
            println(it.`val`())
        }
    }
}
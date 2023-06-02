package org.operatorfoundation.ghostwriterandroid

import kotlinx.serialization.Serializable

@Serializable
class Template(val string: String) {
    fun apply(index: Int, detail: Detail): Template? {
        val oldText = "$$index"
        val newText = detail.detailString()

        if (!this.string.contains(oldText)) {
            return null
        }

        if (newText != null) {
            val result = this.string.replace(oldText, newText)
            return Template(result)
        } else {
            return null
        }
    }

    fun extract(index: Int, pattern: ExtractionPattern, source: String): Triple<Template?, String?, Detail?> {
        val oldText = "$$index"
        val extractIndex = this.string.indexOf(oldText, 0)
        if (extractIndex == -1) {
            print("template.extract(): failed to find index")
            return Triple(null, null, null)
        }

        val templatePreludeIndex = extractIndex + oldText.length
        val sourcePreludeIndex = extractIndex
        val prelude = this.string.substring(0 until extractIndex) // exclusive
        if (!source.startsWith(prelude)) {
            print("template.extract(): source and prelude from template do not match")
            return Triple(null, null, null)
        }

        val templateRest = this.string.substring(templatePreludeIndex..this.string.length)
        val sourceRest = source.substring(sourcePreludeIndex..source.length)

        val result = pattern.extract(sourceRest)
        if (result == null) {
            return Triple(null, null, null)
        }

        val convertedDetail = pattern.convert(result)
        val matchIndex = sourceRest.indexOf(result)
        val endMatchIndex = matchIndex + result.length
        val resultEnd = sourceRest.substring(endMatchIndex..sourceRest.length)

        return Triple(Template(templateRest), resultEnd, convertedDetail)
    }
}
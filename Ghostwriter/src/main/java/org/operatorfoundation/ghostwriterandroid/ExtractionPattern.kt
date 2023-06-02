package org.operatorfoundation.ghostwriterandroid

import kotlinx.serialization.Serializable

@Serializable
class ExtractionPattern(val expression: String, val type: Types) {
    fun extract(extractString: String): String? {
        return extractor(extractString)
    }

    fun convert(convertString: String): Detail? {
        return when(this.type) {
            Types.string -> Detail(convertString)
            Types.int -> Detail(convertString.toInt())
            Types.uint -> Detail(convertString.toUInt())
            Types.float -> Detail(convertString.toFloat())
            Types.data -> Detail(convertString.toByteArray())
        }
    }

    fun extractor(x: String): String? {
        val regex = Regex(pattern = expression)
        val matches = regex.matchEntire(x)
        if (matches == null) {
            return null
        }

        val values = matches.groupValues
        return values[1]
    }
}
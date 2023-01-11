package org.operatorfoundation.ghostwriterandroid

class ExtractionPattern(val expression: String, val type: Types) {
    fun extract(extractString: String): String? {
        return extractor(extractString)
    }

    fun convert(convertString: String): Detail? {
        return when(this.type) {
            Types.string -> return Detail(convertString)
            Types.int -> return Detail(convertString.toInt())
            Types.uint -> return Detail(convertString.toUInt())
            Types.float -> return Detail(convertString.toFloat())
            Types.data -> return Detail(convertString.toByteArray())
            else -> {return null}
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
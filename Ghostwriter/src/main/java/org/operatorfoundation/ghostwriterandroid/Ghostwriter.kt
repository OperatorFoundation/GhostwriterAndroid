package org.operatorfoundation.ghostwriterandroid

fun Generate(template: Template?, details: Array<Detail>): String? {
    var result = template

    if (details.size >= 9) {
        println("Generate(): we do not support more than 9 details")
        return null
    }

    for ((index, detail) in details.withIndex()) {
        if (result != null) {
            result = result.apply(index + 1, detail)
        }
    }

    if (result != null) {
        return result.string
    } else {
        return null
    }
}

fun Parse(template: Template?, patterns: Array<ExtractionPattern>, parseString: String): Array<Detail>? {
    var working = template
    var source = parseString
    var details = arrayOf<Detail>()

    if (patterns.size >= 9) {
        println("Generate(): we do not support more than 9 details")
        return null
    }

    for ((index, pattern) in patterns.withIndex()) {
        if (working != null) {
            val (newTemplate, newSource, detail) = working.extract(index + 1, pattern, source)

            working = newTemplate
            if (newSource != null) {
                source = newSource
            }
            if (detail != null) {
                details.plus(detail)
            } else {
                return null
            }
        }
    }

    if (working != null) {
        if (working.string != source) {
            // println("ghostwriter.Parse() final working string and source do not match")
            return null
        }
    }

    return details
}
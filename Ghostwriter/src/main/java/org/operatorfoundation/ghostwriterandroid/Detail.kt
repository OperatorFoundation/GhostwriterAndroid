package org.operatorfoundation.ghostwriterandroid

import kotlin.reflect.typeOf

enum class Types {
    string,
    int,
    uint,
    float,
    data
}

class Detail(var detail: Any) {
    fun detailString(): String? {
        return when(detail) {
            is String -> detail as String
            is Int -> (detail as Int).toString()
            is UInt -> (detail as UInt).toString()
            is Float -> (detail as Float).toString()
            is ByteArray -> String(detail as ByteArray)
            else -> null
        }
    }
}
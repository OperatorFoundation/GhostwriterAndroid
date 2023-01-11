package org.operatorfoundation.ghostwriterandroid

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun testGenerateString() {
        val correct = "abz"
        val template = Template("a$1z")
        val details = arrayOf(Detail("b"))

        val result = Generate(template, details)
        assertEquals(correct, result)
    }
}
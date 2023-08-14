package org.operatorfoundation.ghostwriterandroid

import org.junit.Test

import org.junit.Assert.*
import java.util.regex.Pattern

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

    @Test
    fun testSlice() {
        val testString = "$1\r\n"
        val substring = testString.substring(2, 4)
        println("substring: " + substring)
        assertEquals(substring, "\r\n")
    }

    @Test
    fun testExtract() {
        val template = Template("$1\r\n")
        val extractionPattern = ExtractionPattern("250 (STARTTLS)", Types.string)
        template.extract(1, extractionPattern, "2")
    }

    @Test
    fun testParse() {
        val string = "220 mail.imc.org SMTP service ready\r\n"
        val template = Template("220 $1 SMTP service ready\r\n")
        val expressionString = "^([a-zA-Z0-9.-]+)"
        val pattern = ExtractionPattern(expressionString, Types.string)
        val resultDetails = Parse(template, arrayOf(pattern), string)
        assertNotEquals(resultDetails, null)
    }

    @Test
    fun regexTest() {
        val x = "mail.imc.org SMTP service ready\r\n"
        val expression = "^([a-zA-Z0-9.-]+)"
        val regex = Regex(pattern = expression)
        val matches = regex.matches(x)
        assertNotNull(matches)
    }
}
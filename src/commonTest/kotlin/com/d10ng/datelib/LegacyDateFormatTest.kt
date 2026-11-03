package com.d10ng.datelib

import kotlinx.datetime.*
import kotlin.test.*

class LegacyDateFormatTest {
    @Test
    fun twelveHourFormatAndParse() {
        for ((hour, text) in listOf(0 to "12:00 AM", 12 to "12:00 PM", 13 to "01:00 PM", 23 to "11:00 PM")) {
            val timestamp = timestampSystem(2024, 1, 1, hour, 0, 0)
            assertEquals(text, timestamp.toDateStr("hh:mm a"))
            assertEquals(timestamp, "2024-01-01 $text".toDateLong("yyyy-MM-dd hh:mm a"))
        }
        assertEquals("PM PM", timestampSystem(2024, 1, 1, 13, 0, 0).toDateStr("a a"))
        assertFalse("2024-01-01 00:00 AM".isDatetimeString("yyyy-MM-dd hh:mm a"))
        assertFalse("2024-01-01 13:00 AM".isDatetimeString("yyyy-MM-dd hh:mm a"))
    }

    @Test
    fun numericWidthsAndRepeatedFields() {
        val timestamp = timestampSystem(2024, 11, 25, 13, 45, 56)
        val pattern = "yyyy-M-d H:m:s"
        assertEquals("2024-11-25 13:45:56", timestamp.toDateStr(pattern))
        assertEquals(timestamp, timestamp.toDateStr(pattern).toDateLong(pattern))
        assertEquals("11/11 25/25", timestamp.toDateStr("M/M d/d"))
        assertEquals("24", timestamp.toDateStr("yy"))
        for (year in listOf(-4, 0, 10000)) {
            val extended = timestampSystem(year, 1, 1)
            assertEquals(extended, extended.toDateStr().toDateLong())
        }
        assertEquals(timestamp, timestamp.toDateStr("yyyyMMddHHmmss").toDateLong("yyyyMMddHHmmss"))
        assertFalse("2024-11-25 26".isDatetimeString("yyyy-MM-dd dd"))
        assertTrue("2024-11-25 25".isDatetimeString("yyyy-MM-dd dd"))
        assertEquals("48 330", timestamp.toDateStr("w D"))
    }

    @Test
    fun strictInputAndLiteralSeparators() {
        for (text in listOf("abcd-01-01 00:00:00", "2023-02-29 00:00:00", "2023-02-31 12:00:00", "2024-04-31 00:00:00", "2024-01-01 24:00:00", "2024-01-01", "2024/01/01 00:00:00")) {
            assertFalse(text.isDatetimeString(), text)
            assertFailsWith<IllegalArgumentException>(text) { text.toDateLong() }
        }
        assertTrue("2024-02-29 12:00:00".isDatetimeString())
        assertFalse("2024x01x01".isDatetimeString("yyyy.MM.dd"))
        assertTrue("2024.01.01".isDatetimeString("yyyy.MM.dd"))
        assertTrue("2024[01]01".isDatetimeString("yyyy[MM]dd"))
        assertFalse("anything".isDatetimeString("literal"))
        assertTrue("2024 - 01 - 01".isDatetimeString("yyyy-MM-dd"))
    }

    @Test
    fun millisecondsAndIgnoredDerivedFields() {
        val timestamp = timestampSystem(2024, 2, 29, 1, 2, 3, 123)
        for ((pattern, expected) in listOf("S" to "1", "SS" to "12", "SSS" to "123")) {
            assertEquals(expected, timestamp.toDateStr(pattern))
            val parsed = "2024-02-29 01:02:03.$expected".toDateLong("yyyy-MM-dd HH:mm:ss.$pattern")
            assertEquals(expected.padEnd(3, '0').toInt(), parsed.timestampToSystemDateTime().millisecond())
        }
        assertFailsWith<IllegalArgumentException> { timestamp.toDateStr("SSSS") }
        assertFalse("1234".isDatetimeString("SSSS"))
        val pattern = "yyyy-MM-dd HH:mm:ss.SSS w W D E"
        assertEquals(timestamp, timestamp.toDateStr(pattern).toDateLong(pattern))
        val date = "2024-02-29".toDateLong("yyyy-MM-dd").timestampToSystemDateTime()
        assertEquals(LocalDateTime(2024, 2, 29, 0, 0), date)
    }
}

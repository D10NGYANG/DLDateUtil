package com.d10ng.datelib

import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.Month
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class LocalDateTimeFormatPatternTest {

    @Test
    fun `should format with default pattern`() {
        val dateTime = LocalDateTime(2023, Month.JANUARY, 15, 14, 30, 45)
        val result = dateTime.formatPattern()
        assertEquals("2023-01-15 14:30:45", result)
    }

    @Test
    fun `should format with custom pattern`() {
        val dateTime = LocalDateTime(2023, Month.DECEMBER, 31, 23, 59, 59)
        val result = dateTime.formatPattern("yyyy/MM/dd HH:mm")
        assertEquals("2023/12/31 23:59", result)
    }

    @Test
    fun `should format with date only pattern`() {
        val dateTime = LocalDateTime(2023, Month.FEBRUARY, 28, 0, 0)
        val result = dateTime.formatPattern("yyyy-MM-dd")
        assertEquals("2023-02-28", result)
    }

    @Test
    fun `should format with time only pattern`() {
        val dateTime = LocalDateTime(2023, Month.MARCH, 1, 9, 15, 30)
        val result = dateTime.formatPattern("HH:mm:ss")
        assertEquals("09:15:30", result)
    }

    @Test
    fun `should parse string with default pattern`() {
        val dateString = "2023-05-15 14:30:00"
        val expected = LocalDateTime(2023, 5, 15, 14, 30, 0)

        val result = dateString.toLocalDateTime()

        assertEquals(expected, result)
    }

    @Test
    fun `should parse string with custom pattern`() {
        val dateString = "15/05/2023 14.30"
        val pattern = "dd/MM/yyyy HH.mm"
        val expected = LocalDateTime(2023, 5, 15, 14, 30, 0)

        val result = dateString.toLocalDateTime(pattern)

        assertEquals(expected, result)
    }

    @Test
    fun `should throw exception for invalid date string`() {
        val invalidDateString = "2023-13-15 14:30:00"

        assertFailsWith<Exception> {
            invalidDateString.toLocalDateTime()
        }
    }

    @Test
    fun `should throw exception for invalid pattern`() {
        val dateString = "2023-05-15 14:30:00"
        val invalidPattern = "invalid-pattern"

        assertFailsWith<Exception> {
            dateString.toLocalDateTime(invalidPattern)
        }
    }
}
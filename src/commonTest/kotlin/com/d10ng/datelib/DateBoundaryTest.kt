@file:OptIn(kotlin.time.ExperimentalTime::class)
@file:Suppress("DEPRECATION")

package com.d10ng.datelib

import kotlinx.datetime.*
import kotlin.time.Instant
import kotlin.test.*

class DateBoundaryTest {
    @Test
    fun weekBoundaries() {
        for (date in listOf(LocalDate(2018, 12, 31), LocalDate(2019, 12, 30), LocalDate(2024, 12, 30))) {
            assertEquals(1, date.isoWeekOfYear())
            assertEquals(1, date.atZeroTime().isoWeekOfYear())
        }
        assertEquals(3, LocalDate(2023, 1, 15).weekOfMonth(false))
        assertEquals(4, LocalDate(2023, 1, 16).weekOfMonth(false))
        assertEquals(5, LocalDate(2023, 12, 31).weekOfMonth(false))
    }

    @Test
    fun copyPreservesHistoricalAndExtendedYears() {
        for (year in listOf(-4, 0, 1900, 1960, 10000)) {
            val date = LocalDate(year, 2, 28)
            assertEquals(date, date.copy())
            val dateTime = date.atTime(12, 30)
            assertEquals(dateTime, dateTime.copy())
            assertEquals(year, dateTime.copy(hour = 1).year)
        }
        assertEquals(LocalDate(999999, 12, 31), LocalDate(999999, 12, 31).copy())
        assertEquals(LocalDate(1901, 2, 28), LocalDate(1904, 2, 29).copy(year = 1901))
        assertEquals(1960, timestampSystem(2024, 1, 1).setDateYear(1960).timestampToSystemDateTime().year)
    }

    @Test
    fun secondsFloorAndCheckedRange() {
        for ((millis, seconds) in listOf(-1001L to -2, -1000L to -1, -1L to -1, 0L to 0, 999L to 0)) {
            val instant = Instant.fromEpochMilliseconds(millis)
            assertEquals(seconds, millis.timestampToTimeSeconds())
            assertEquals(seconds, instant.toTimeSeconds())
            assertEquals(seconds, instant.epochSecondsInt())
        }
        for (seconds in listOf(Int.MIN_VALUE, Int.MAX_VALUE)) {
            assertEquals(seconds, seconds.timeSecondsToTimestamp().timestampToTimeSeconds())
        }
        for (seconds in listOf(Int.MIN_VALUE.toLong() - 1, Int.MAX_VALUE.toLong() + 1)) {
            assertFailsWith<IllegalArgumentException> { (seconds * 1000).timestampToTimeSeconds() }
            assertFailsWith<IllegalArgumentException> { Instant.fromEpochSeconds(seconds).toTimeSeconds() }
        }
        assertFailsWith<IllegalArgumentException> { LocalDateTime(2038, 1, 19, 3, 14, 8).toUTCTimeSeconds() }
    }

    @Test
    fun millisecondValidationPrecedesMultiplication() {
        for (millisecond in listOf(-1, 1000, 4295, Int.MIN_VALUE, Int.MAX_VALUE)) {
            assertFailsWith<IllegalArgumentException> { timestampUTC(2024, 1, 1, 0, 0, 0, millisecond) }
        }
        assertEquals(999, timestampUTC(2024, 1, 1, 0, 0, 0, 999).timestampToUTCDateTime().millisecond())
        val timestamp = timestampSystem(2024, 1, 1)
        assertEquals(999, timestamp.setDateMillisecond(Int.MAX_VALUE).timestampToSystemDateTime().millisecond())
        assertEquals(0, timestamp.setDateMillisecond(Int.MIN_VALUE).timestampToSystemDateTime().millisecond())
    }
}

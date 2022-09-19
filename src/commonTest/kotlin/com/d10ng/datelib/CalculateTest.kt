package com.d10ng.datelib

import kotlin.test.Test
import kotlin.test.assertEquals

class CalculateTest {

    @Test
    fun isDatetimeStringTest() {
        assertEquals("2022-09-14 18:00:21".isDatetimeString("yyyy-MM-dd HH:mm:ss"), true)
        assertEquals("2022-09-14".isDatetimeString("yyyy-MM-dd HH:mm:ss"), false)
    }

    @Test
    fun getDateByTest() {
        assertEquals(getDateBy(2022, 9, 14, 18, 0, 21, 238), timestamp)
    }

    @Test
    fun getNextDayTest() {
        assertEquals(timestamp.getNextDay(-1), 1663063221238)
        assertEquals(timestamp.getNextDay(10), 1664013621238)
    }

    @Test
    fun getDaysOfMonthTest() {
        assertEquals(getDaysOfMonth(2020, 2), 29)
        assertEquals(getDaysOfMonth(2022, 2), 28)
    }

    @Test
    fun isNextDayTest() {
        val cur = curTime
        assertEquals(cur.getNextDay(-1).isNextDay(-1), true)
        assertEquals(cur.getNextDay(-1).isNextDay(-2), false)
        assertEquals(cur.getNextDay(11).isNextDay(11), true)
        assertEquals(cur.getNextDay(11).isNextDay(12), false)
    }

    @Test
    fun toUTCDateTest() {
        assertEquals(timestamp.toUTCDate(), 1663120821238)
        assertEquals(timestamp.toLocalDate(), 1663178421238)
    }

    @Test
    fun toCustomDateTest() {
        assertEquals(timestamp.toCustomDate(-2), 1663142421238)
    }

    @Test
    fun dateDiffToStringTest() {
        val start = 1663142665000
        val end = 1663250850000
        assertEquals(dateDiffToString(start, end), "30:03:05")
        assertEquals(dateDiffToString(start, end, true), "1天 06:03:05")
    }
}
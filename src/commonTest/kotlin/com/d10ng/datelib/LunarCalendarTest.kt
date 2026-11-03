package com.d10ng.datelib

import kotlinx.datetime.*
import kotlin.test.*

class LunarCalendarTest {
    @Test
    fun knownDatesAndGanzhi() {
        val newYear = assertNotNull(LunarDateUtil.lunar2solar(2024, 1, 1))
        assertEquals(Triple(2024, 2, 10), Triple(newYear.cYear, newYear.cMonth, newYear.cDay))
        assertEquals("甲辰", newYear.gzDay)
        assertEquals("甲子", LunarDateUtil.solar2lunar(2000, 1, 7)?.gzDay)
        assertEquals("癸卯", LunarDateUtil.solar2lunar(2024, 1, 2)?.gzYear)
        assertEquals("癸卯", LunarDateUtil.solar2lunar(2024, 2, 3)?.gzYear)
        assertEquals("甲辰", LunarDateUtil.solar2lunar(2024, 2, 4)?.gzYear)
        assertEquals(Triple(1900, 1, 31), LunarDateUtil.lunar2solar(1900, 1, 1)?.let {
            Triple(it.cYear, it.cMonth, it.cDay)
        })
    }

    @Test
    fun allSolarTermsIn2024() {
        val days = listOf(6, 20, 4, 19, 5, 20, 4, 19, 5, 20, 5, 21, 6, 22, 7, 22, 7, 22, 8, 23, 7, 22, 6, 21)
        val names = listOf("小寒", "大寒", "立春", "雨水", "惊蛰", "春分", "清明", "谷雨", "立夏", "小满", "芒种", "夏至", "小暑", "大暑", "立秋", "处暑", "白露", "秋分", "寒露", "霜降", "立冬", "小雪", "大雪", "冬至")
        for ((index, day) in days.withIndex()) {
            val lunar = assertNotNull(LunarDateUtil.solar2lunar(2024, index / 2 + 1, day))
            assertTrue(lunar.isTerm, "${lunar.cMonth}-$day")
            assertEquals(names[index], lunar.term)
        }
        assertFalse(assertNotNull(LunarDateUtil.solar2lunar(2024, 1, 2)).isTerm)
    }

    @Test
    fun rejectInvalidDatesAndUnsupportedRange() {
        for (year in listOf(1899, 2101, Int.MIN_VALUE, Int.MAX_VALUE)) {
            assertNull(LunarDateUtil.lunar2solar(year, 1, 1))
            assertNull(LunarDateUtil.solar2lunar(year, 1, 1))
        }
        for (month in listOf(0, 13)) {
            assertNull(LunarDateUtil.lunar2solar(2024, month, 1))
            assertNull(LunarDateUtil.solar2lunar(2024, month, 1))
        }
        assertNull(LunarDateUtil.lunar2solar(2024, 1, 0))
        assertNull(LunarDateUtil.lunar2solar(2023, 2, 30, true))
        assertNotNull(LunarDateUtil.lunar2solar(2023, 2, 29, true))
        assertNull(LunarDateUtil.lunar2solar(2024, 2, 1, true))
        assertNull(LunarDateUtil.lunar2solar(2100, 12, 30))
        assertNull(LunarDateUtil.solar2lunar(2023, 2, 29))
        assertNull(LunarDateUtil.solar2lunar(1900, 1, 30))
        assertFailsWith<IllegalArgumentException> { LocalDate(1800, 1, 1).lunarCalendar() }
        @Suppress("DEPRECATION")
        val legacy = timestampSystem(1800, 1, 1).getDateDayLunar()
        assertNull(legacy)
    }

    @Test
    fun roundTripEverySupportedSolarDate() {
        var date = LocalDate(1900, 1, 31)
        val end = LocalDate(2100, 12, 31)
        while (date <= end) {
            val lunar = assertNotNull(LunarDateUtil.solar2lunar(date.year, date.month.number, date.day))
            val solar = assertNotNull(LunarDateUtil.lunar2solar(lunar.lYear, lunar.lMonth, lunar.lDay, lunar.isLeap), "$date")
            assertEquals(date, LocalDate(solar.cYear, solar.cMonth, solar.cDay))
            date = date.plus(1, DateTimeUnit.DAY)
        }
    }
}

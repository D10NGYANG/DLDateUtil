package com.d10ng.datelib

import kotlinx.datetime.LocalDate
import java.time.temporal.WeekFields
import java.util.TimeZone
import kotlin.test.*

class CalendarReferenceTest {
    @Test
    fun copyMaximumJvmDate() {
        val date = LocalDate(999999999, 12, 31)
        assertEquals(date, date.copy())
        assertEquals(31, daysOfMonth(date.year, 12))
    }

    @Test
    fun weeksMatchJavaTimeForFortyYears() {
        var date = java.time.LocalDate.of(2000, 1, 1)
        val end = java.time.LocalDate.of(2040, 1, 1)
        while (date < end) {
            val local = LocalDate(date.year, date.monthValue, date.dayOfMonth)
            assertEquals(date.get(WeekFields.ISO.weekOfWeekBasedYear()), local.isoWeekOfYear(), "$date")
            assertEquals(date.get(WeekFields.of(java.time.DayOfWeek.MONDAY, 1).weekOfMonth()), local.weekOfMonth(false), "$date")
            date = date.plusDays(1)
        }
    }

    @Test
    fun lunarDatesAreIndependentOfSystemTimeZone() {
        val original = TimeZone.getDefault()
        try {
            val dates = listOf(LocalDate(1900, 1, 31), LocalDate(1960, 6, 1), LocalDate(2024, 3, 10), LocalDate(2024, 7, 1), LocalDate(2024, 11, 3))
            TimeZone.setDefault(TimeZone.getTimeZone("UTC"))
            val expected = dates.map { it.lunarCalendar() }
            for (zone in listOf("Asia/Shanghai", "America/New_York", "Pacific/Apia")) {
                TimeZone.setDefault(TimeZone.getTimeZone(zone))
                for ((index, date) in dates.withIndex()) {
                    val lunar = date.lunarCalendar()
                    assertEquals(expected[index].copy(isToday = false), lunar.copy(isToday = false), "$zone $date")
                    assertEquals(lunar, LunarDateUtil.lunar2solar(lunar.lYear, lunar.lMonth, lunar.lDay, lunar.isLeap))
                }
            }
        } finally {
            TimeZone.setDefault(original)
        }
    }
}

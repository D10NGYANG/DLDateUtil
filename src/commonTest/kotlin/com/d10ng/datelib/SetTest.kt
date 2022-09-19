package com.d10ng.datelib

import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * 从时间戳直接设置数据的接口测试
 */
class SetTest {

    @Test
    fun setDateYearTest() {
        val year = 2021
        var ts = timestamp.setDateYear(year)
        assertEquals(ts.getDateYear(), year)
        // 闰年测试
        ts = timestampLeap.setDateYear(year)
        assertEquals(ts.getDateYear(), year)
        assertEquals(ts.getDateMonth(), 2)
        assertEquals(ts.getDateDay(), 28)
    }

    @Test
    fun setDateMonthTest() {
        val month = 4
        var ts = timestamp.setDateMonth(month)
        assertEquals(ts.getDateMonth(), month)
        // 31号测试 2020-03-31 00:00:00
        ts = timestamp31.setDateMonth(month)
        assertEquals(ts.getDateMonth(), month)
        assertEquals(ts.getDateDay(), 30)
    }

    @Test
    fun setDateDayTest() {
        val day = 22
        var ts = timestamp.setDateDay(day)
        assertEquals(ts.getDateDay(), day)
        ts = timestamp.setDateDay(31)
        assertEquals(ts.getDateDay(), 30)
    }

    @Test
    fun setDateHourTest() {
        val hour = 22
        val ts = timestamp.setDateHour(hour)
        assertEquals(ts.getDateHour(), hour)
    }

    @Test
    fun setDateMinuteTest() {
        val minute = 35
        val ts = timestamp.setDateMinute(minute)
        assertEquals(ts.getDateMinute(), minute)
    }

    @Test
    fun setDateSecondTest() {
        val second = 35
        val ts = timestamp.setDateSecond(second)
        assertEquals(ts.getDateSecond(), second)
    }

    @Test
    fun setDateMillisecondTest() {
        val millisecond = 266
        val ts = timestamp.setDateMillisecond(millisecond)
        assertEquals(ts.getDateMillisecond(), millisecond)
    }
}
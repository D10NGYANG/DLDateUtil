package com.d10ng.datelib

import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * 从时间戳获取数据的接口测试
 */
class GetTest {

    @Test
    fun getDateYearTest() {
        assertEquals(timestamp.getDateYear(), 2022)
    }

    @Test
    fun getDateMonthTest() {
        assertEquals(timestamp.getDateMonth(), 9)
    }

    @Test
    fun getDateDayTest() {
        assertEquals(timestamp.getDateDay(), 14)
    }

    @Test
    fun getDateHourTest() {
        assertEquals(timestamp.getDateHour(), 18)
    }

    @Test
    fun getDateMinuteTest() {
        assertEquals(timestamp.getDateMinute(), 0)
    }

    @Test
    fun getDateSecondTest() {
        assertEquals(timestamp.getDateSecond(), 21)
    }

    @Test
    fun getDateMillisecondTest() {
        assertEquals(timestamp.getDateMillisecond(), 238)
    }

    @Test
    fun getDateWeekOfYearTest() {
        assertEquals(timestamp.getDateWeekOfYear(), 38)
    }

    @Test
    fun getDateWeekOfMonthTest() {
        assertEquals(timestamp.getDateWeekOfMonth(), 2)
        assertEquals(timestamp.getDateWeekOfMonth(false), 3)
    }

    @Test
    fun getDateDayOfYearTest() {
        assertEquals(timestamp.getDateDayOfYear(), 257)
    }

    @Test
    fun getDateDayOfWeekTest() {
        assertEquals(timestamp.getDateDayOfWeek(), 3)
    }

    @Test
    fun getDateDayOfWeekTextTest() {
        assertEquals(timestamp.getDateDayOfWeekText(WeekTextType.CN), "星期三")
        assertEquals(timestamp.getDateDayOfWeekText(WeekTextType.CN_SHORT), "周三")
        assertEquals(timestamp.getDateDayOfWeekText(WeekTextType.CN_MINI), "三")
        assertEquals(timestamp.getDateDayOfWeekText(WeekTextType.EN), "WEDNESDAY")
        assertEquals(timestamp.getDateDayOfWeekText(WeekTextType.EN_SHORT), "WED")
    }

    @Test
    fun getDateMonthTextTest() {
        assertEquals(timestamp.getDateMonthText(MonthTextType.CN), "九月")
        assertEquals(timestamp.getDateMonthText(MonthTextType.EN), "September")
        assertEquals(timestamp.getDateMonthText(MonthTextType.EN_SHORT), "Sep")
    }

    @Test
    fun getDateDayLunarTest() {
        assertEquals(timestamp.getDateDayLunar(), CalendarInfo(2022, 8, 19, "虎", "八月", "十九", 2022, 9, 14, "壬寅", "己酉", "庚午", false, false, 3, "星期三", false, null))
    }
}
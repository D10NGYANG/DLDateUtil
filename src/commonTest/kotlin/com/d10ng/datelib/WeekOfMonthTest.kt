package com.d10ng.datelib

import kotlinx.datetime.LocalDate
import kotlinx.datetime.Month
import kotlin.test.Test
import kotlin.test.assertEquals

class WeekOfMonthTest {

    @Test
    fun `test weekOfMonth with first Monday as first week`() {
        // 测试月中第一个周一开始算周数的情况
        val date1 = LocalDate(2023, Month.JANUARY, 2)  // 周一（第一周）
        assertEquals(1, date1.weekOfMonth(true))

        val date2 = LocalDate(2023, Month.JANUARY, 9)  // 第二周周一
        assertEquals(2, date2.weekOfMonth(true))

        val date3 = LocalDate(2023, Month.FEBRUARY, 6)  // 二月第一周周一
        assertEquals(1, date3.weekOfMonth(true))

        val date4 = LocalDate(2023, Month.JANUARY, 1)  // 周日（不属于第一周）
        assertEquals(0, date4.weekOfMonth(true))
    }

    @Test
    fun `test weekOfMonth with 1st as first week`() {
        // 测试月的1号作为第一周的情况
        val date1 = LocalDate(2023, Month.JANUARY, 1)  // 周日（第一周）
        assertEquals(1, date1.weekOfMonth(false))

        val date2 = LocalDate(2023, Month.JANUARY, 2)  // 周一（第二周）
        assertEquals(2, date2.weekOfMonth(false))

        val date3 = LocalDate(2023, Month.JANUARY, 8)  // 周日（第二周）
        assertEquals(2, date3.weekOfMonth(false))

        val date4 = LocalDate(2023, Month.FEBRUARY, 28) // 二月最后一天
        assertEquals(5, date4.weekOfMonth(false))
    }

    @Test
    fun `test edge cases`() {
        // 测试边界情况
        val date1 = LocalDate(2023, Month.DECEMBER, 31)  // 年末
        assertEquals(6, date1.weekOfMonth(false))

        val date2 = LocalDate(2023, Month.MARCH, 1)  // 周三（测试非周一开始的月份）
        assertEquals(0, date2.weekOfMonth(true))

        val date3 = LocalDate(2024, Month.FEBRUARY, 29)  // 闰年
        assertEquals(5, date3.weekOfMonth(false))
    }
}
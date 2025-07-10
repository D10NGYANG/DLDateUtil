package com.d10ng.datelib

import kotlinx.datetime.LocalDate
import kotlin.test.Test
import kotlin.test.assertEquals

class WeekOfYearTest {

    @Test
    fun `test week of year for first day of year`() {
        val date = LocalDate(2023, 1, 1)
        assertEquals(1, date.weekOfYear())
    }

    @Test
    fun `test week of year for middle of year`() {
        val date = LocalDate(2023, 6, 15)
        assertEquals(25, date.weekOfYear())
    }

    @Test
    fun `test week of year for last day of year`() {
        val date = LocalDate(2023, 12, 31)
        assertEquals(53, date.weekOfYear())
    }

    @Test
    fun `test week of year crossing year boundary`() {
        val date = LocalDate(2022, 1, 1) // Saturday
        assertEquals(1, date.weekOfYear())
    }

    @Test
    fun `test week of year for leap year`() {
        val date = LocalDate(2024, 2, 29) // Leap day
        assertEquals(9, date.weekOfYear())
    }

    @Test
    fun `test isoWeekOfYear when first day is Monday`() {
        // 2023-01-01 是周日，所以2023年第一周从1月2日开始
        val date1 = LocalDate(2023, 1, 1) // 22年的第52周
        val date2 = LocalDate(2023, 1, 2) // 第1周
        val date3 = LocalDate(2023, 1, 8) // 第1周
        val date4 = LocalDate(2023, 1, 9) // 第2周

        assertEquals(52, date1.isoWeekOfYear())
        assertEquals(1, date2.isoWeekOfYear())
        assertEquals(1, date3.isoWeekOfYear())
        assertEquals(2, date4.isoWeekOfYear())
    }

    @Test
    fun `test isoWeekOfYear when first day is Sunday`() {
        // 2021-01-01 是周五，所以2021年第一周从1月4日开始
        val date1 = LocalDate(2021, 1, 1) // 20年的第53周
        val date2 = LocalDate(2021, 1, 3) // 20年的第53周
        val date3 = LocalDate(2021, 1, 4) // 第1周
        val date4 = LocalDate(2021, 1, 10) // 第1周
        val date5 = LocalDate(2021, 1, 11) // 第2周

        assertEquals(53, date1.isoWeekOfYear())
        assertEquals(53, date2.isoWeekOfYear())
        assertEquals(1, date3.isoWeekOfYear())
        assertEquals(1, date4.isoWeekOfYear())
        assertEquals(2, date5.isoWeekOfYear())
    }

    @Test
    fun `test isoWeekOfYear for leap year`() {
        // 2020年是闰年，2月有29天
        val date1 = LocalDate(2020, 2, 28) // 第9周
        val date2 = LocalDate(2020, 2, 29) // 第9周
        val date3 = LocalDate(2020, 3, 1) // 第9周
        val date4 = LocalDate(2020, 3, 2) // 第10周

        assertEquals(9, date1.isoWeekOfYear())
        assertEquals(9, date2.isoWeekOfYear())
        assertEquals(9, date3.isoWeekOfYear())
        assertEquals(10, date4.isoWeekOfYear())
    }

    @Test
    fun `test isoWeekOfYear for end of year`() {
        // 2022年最后一周
        val date1 = LocalDate(2022, 12, 25) // 第51周
        val date2 = LocalDate(2022, 12, 31) // 第52周
        val date3 = LocalDate(2023, 1, 1) // 第52周

        assertEquals(51, date1.isoWeekOfYear())
        assertEquals(52, date2.isoWeekOfYear())
        assertEquals(52, date3.isoWeekOfYear())
    }

    @Test
    fun `test isoWeekOfYear when first day is Tuesday`() {
        // 2019-01-01 是周二
        val date1 = LocalDate(2019, 1, 1) // 第1周
        val date2 = LocalDate(2019, 1, 6) // 第1周
        val date3 = LocalDate(2019, 1, 7) // 第2周

        assertEquals(1, date1.isoWeekOfYear())
        assertEquals(1, date2.isoWeekOfYear())
        assertEquals(2, date3.isoWeekOfYear())
    }
}
package com.d10ng.datelib

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFails

class DaysOfMonthTest {

    @Test
    fun `test daysOfMonth for common months`() {
        assertEquals(31, daysOfMonth(2023, 1))  // 一月
        assertEquals(28, daysOfMonth(2023, 2))  // 二月非闰年
        assertEquals(30, daysOfMonth(2023, 4))  // 四月
        assertEquals(31, daysOfMonth(2023, 7))  // 七月
        assertEquals(30, daysOfMonth(2023, 9))  // 九月
        assertEquals(31, daysOfMonth(2023, 12)) // 十二月
    }

    @Test
    fun `test daysOfMonth for February in leap year`() {
        assertEquals(29, daysOfMonth(2020, 2))  // 闰年二月
        assertEquals(29, daysOfMonth(2000, 2))  // 世纪闰年二月
        assertEquals(28, daysOfMonth(2100, 2))  // 非闰年二月
    }

    @Test
    fun `test daysOfMonth for edge cases`() {
        assertEquals(31, daysOfMonth(1, 1))     // 公元1年
        assertEquals(31, daysOfMonth(9999, 12)) // 最大年份
        assertEquals(28, daysOfMonth(2023, 2))  // 最小天数
        assertEquals(31, daysOfMonth(2023, 8))  // 最大天数
    }

    @Test
    fun `test daysOfMonth for invalid month inputs`() {
        // 测试非法月份输入（虽然Kotlin会抛出异常，这里仅作示例）
        // 实际项目中应该添加异常处理测试
        assertFails { daysOfMonth(2023, 13) } // 月份大于12
        assertFails { daysOfMonth(2023, 0) }  // 月份小于1
    }
}
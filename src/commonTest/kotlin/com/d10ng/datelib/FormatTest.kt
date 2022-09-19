package com.d10ng.datelib

import kotlin.test.Test
import kotlin.test.assertEquals

class FormatTest {

    @Test
    fun toDateStrTest() {
        val pattern1 = "yyyy年MM月dd日 HH时mm分ss秒SSS毫秒 今年的第ww周 当前月的第W周 今年的第DDD天 星期E a hh"
        assertEquals(timestamp.toDateStr(pattern1), "2022年09月14日 18时00分21秒238毫秒 今年的第38周 当前月的第2周 今年的第257天 星期3 PM 06")
        val pattern2 = "yyyy-MM-dd HH:mm:ss.SSS"
        assertEquals(timestamp.toDateStr(pattern2), "2022-09-14 18:00:21.238")
        val pattern3 = "yyyy-MM-ddTHH:mm:ss.SSSZ"
        assertEquals(timestamp.toDateStr(pattern3), "2022-09-14T18:00:21.238Z")
    }

    @Test
    fun toDateLongTest() {
        val pattern1 = "yyyy年MM月dd日 HH时mm分ss秒SSS毫秒 今年的第ww周 当前月的第W周 今年的第DDD天 星期E a hh"
        assertEquals("2022年09月14日 18时00分21秒238毫秒 今年的第38周 当前月的第2周 今年的第257天 星期3 PM 06".toDateLong(pattern1), timestamp)
        val pattern2 = "yyyy-MM-dd HH:mm:ss.SSS"
        assertEquals("2022-09-14 18:00:21.238".toDateLong(pattern2), timestamp)
        val pattern3 = "yyyy-MM-ddTHH:mm:ss.SSSZ"
        assertEquals("2022-09-14T18:00:21.238Z".toDateLong(pattern3), timestamp)
    }
}
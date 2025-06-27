package com.d10ng.datelib

import kotlin.test.Test
import kotlin.test.assertEquals

class LocalDateTimeTest {

    @Test
    fun test() {
        val timestamp = 1748832079780L
        assertEquals(createUTCDateTime(timestamp).toString(), "2025-06-02T02:41:19.780")
        assertEquals(createSystemLocalDateTime(timestamp).toString(), "2025-06-02T10:41:19.780")
        val seconds = (timestamp / 1000).toInt()
        assertEquals(createUTCDateTime(seconds).toString(), "2025-06-02T02:41:19")
        assertEquals(createSystemLocalDateTime(seconds).toString(), "2025-06-02T10:41:19")
        assertEquals(createUTCDateTime(timestamp).toUTCEpochMilliseconds(), timestamp)
        assertEquals(createSystemLocalDateTime(timestamp).toUTCEpochMilliseconds(), timestamp + 8 * 60 * 60 * 1000)
        assertEquals(createUTCDateTime(timestamp).toEpochMilliseconds(), timestamp - 8 * 60 * 60 * 1000)
        assertEquals(createSystemLocalDateTime(timestamp).toEpochMilliseconds(), timestamp)
    }
}
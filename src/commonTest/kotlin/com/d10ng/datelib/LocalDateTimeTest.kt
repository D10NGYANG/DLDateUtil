package com.d10ng.datelib

import kotlin.test.Test
import kotlin.test.assertEquals

class LocalDateTimeTest {

    @Test
    fun test() {
        val timestamp = 1748832079780L
        assertEquals(timestamp.timestampToUTCDateTime().toString(), "2025-06-02T02:41:19.780")
        assertEquals(timestamp.timestampToSystemDateTime().toString(), "2025-06-02T10:41:19.780")
        val seconds = (timestamp / 1000).toInt()
        assertEquals(seconds.timeSecondsToUTCDateTime().toString(), "2025-06-02T02:41:19")
        assertEquals(seconds.timeSecondsToSystemDateTime().toString(), "2025-06-02T10:41:19")
        assertEquals(timestamp.timestampToUTCDateTime().toUTCTimestamp(), timestamp)
        assertEquals(timestamp.timestampToSystemDateTime().toUTCTimestamp(), timestamp + 8 * 60 * 60 * 1000)
        assertEquals(timestamp.timestampToUTCDateTime().toSystemTimestamp(), timestamp - 8 * 60 * 60 * 1000)
        assertEquals(timestamp.timestampToSystemDateTime().toSystemTimestamp(), timestamp)
    }
}
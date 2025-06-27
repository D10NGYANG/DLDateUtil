package com.d10ng.datelib

import kotlinx.datetime.Instant
import kotlin.test.Test
import kotlin.test.assertEquals

class InstantTest {

    @Test
    fun test() {
        val timestamp = 1748832079780
        val instant = Instant.fromEpochMilliseconds(timestamp)
        assertEquals(instant.toEpochMilliseconds(), timestamp)
        assertEquals(instant.epochSecondsInt(), (timestamp / 1000).toInt())
        assertEquals(instant.toUTCDateTime().toString(), "2025-06-02T02:41:19.780")
        assertEquals(instant.toSystemLocalDateTime().toString(), "2025-06-02T10:41:19.780")
    }
}
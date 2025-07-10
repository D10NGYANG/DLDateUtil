@file:OptIn(ExperimentalTime::class)

package com.d10ng.datelib

import com.d10ng.datelib.toSystemDateTime
import com.d10ng.datelib.toTimeSeconds
import kotlinx.datetime.Instant
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.time.ExperimentalTime

class InstantTest {

    @Test
    fun test() {
        val timestamp = 1748832079780
        val instant = Instant.fromEpochMilliseconds(timestamp)
        assertEquals(instant.toEpochMilliseconds(), timestamp)
        assertEquals(instant.toTimeSeconds(), (timestamp / 1000).toInt())
        assertEquals(instant.toUTCDateTime().toString(), "2025-06-02T02:41:19.780")
        assertEquals(instant.toSystemDateTime().toString(), "2025-06-02T10:41:19.780")
    }
}
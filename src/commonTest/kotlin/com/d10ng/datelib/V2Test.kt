package com.d10ng.datelib

import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalTime
import kotlinx.datetime.atTime
import kotlinx.datetime.minus
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.time.ExperimentalTime

class V2Test {

    @Test
    fun test() {
        val timestamp = nowTimestamp()
        val timeSeconds = nowTimeSeconds()
        println(timestamp)
        println(timeSeconds)
        println(timestamp.timestampToTimeSeconds())
        println(timeSeconds.timeSecondsToTimestamp())
        println(timestamp.timestampToLocalDateTime())
        println(timestamp.timestampToUTCDateTime())
        println(timestamp.timestampToSystemDateTime())
        println(timeSeconds.timeSecondsToLocalDateTime())
        println(timeSeconds.timeSecondsToUTCDateTime())
        println(timeSeconds.timeSecondsToSystemDateTime())
    }

    @Test
    fun test2() {
        for (i in 0..99) {
            val timestamp = 1663149621238L // 2022-09-14 18:00:21.238
            val newTimestamp = timestamp.timestampToSystemDateTime().copy(
                year = 2025
            ).toSystemTimestamp()
            assertEquals(newTimestamp, 1757844021238L)
        }
    }

    @OptIn(ExperimentalTime::class)
    @Test
    fun test3() {
        for (i in 0..99) {
        }
    }
}
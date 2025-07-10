package com.d10ng.datelib

import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.format
import kotlin.test.Test
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
            val text = "2025-06-17 15:20:32"
            println(text.toLocalDateTime().toSystemTimestamp())
        }
    }

    @OptIn(ExperimentalTime::class)
    @Test
    fun test3() {
        for (i in 0..99) {
            println(LocalDateTime(2025,1,1,0,0, 1, 990000000).format(LocalDateTime.Formats.ISO))
        }
    }
}
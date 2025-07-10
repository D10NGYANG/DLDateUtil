package com.d10ng.datelib

import kotlinx.datetime.LocalTime
import kotlinx.datetime.atTime
import kotlin.test.Test

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
        val date = nowSystemDate()
        val time = LocalTime(12, 30,0, 0)
        println(time)
        println(time.toISOString())
    }
}
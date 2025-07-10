package com.d10ng.datelib

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
}
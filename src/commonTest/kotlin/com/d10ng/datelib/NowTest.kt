package com.d10ng.datelib

import kotlin.test.Test
import kotlin.time.ExperimentalTime

class NowTest {

    @OptIn(ExperimentalTime::class)
    @Test
    fun test() {
        println(now())
        println(nowTimestamp())
        println(nowTimeSeconds())
        println(nowUTCDateTime())
        println(nowSystemDateTime())
        println(nowUTCDate())
        println(nowSystemDate())
        println(nowUTCTime())
        println(nowSystemTime())
    }
}
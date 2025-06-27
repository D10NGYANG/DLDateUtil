package com.d10ng.datelib

import kotlin.test.Test

class NowTest {

    @Test
    fun test() {
        println(now())
        println(nowTimestamp())
        println(nowTimeSeconds())
        println(nowUTCDateTime())
        println(nowSystemLocalDateTime())
        println(nowUTCDate())
        println(nowSystemLocalDate())
        println(nowUTCTime())
        println(nowSystemLocalTime())
    }
}
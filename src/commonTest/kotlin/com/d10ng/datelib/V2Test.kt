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
        for (i in 0..99) {
            val datetime = nowSystemDateTime()
            val year = datetime.year
            val month = datetime.month
            val day = datetime.day
            val hour = datetime.hour
            val minute = datetime.minute
            val second = datetime.second
            val nanosecond = datetime.nanosecond
            println("year: $year, month: $month, day: $day, hour: $hour, minute: $minute, second: $second, nanosecond: $nanosecond")
        }
    }

    @Test
    fun test3() {
        for (i in 0..99) {
            val year = nowSystemDateTime().year
            val month = nowSystemDateTime().month
            val day = nowSystemDateTime().day
            val hour = nowSystemDateTime().hour
            val minute = nowSystemDateTime().minute
            val second = nowSystemDateTime().second
            val nanosecond = nowSystemDateTime().nanosecond
            println("year: $year, month: $month, day: $day, hour: $hour, minute: $minute, second: $second, nanosecond: $nanosecond")
        }
    }
}
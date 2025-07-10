@file:JsExport
package com.d10ng.datelib

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.js.JsExport
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

/**
 * 将毫秒级时间戳转换为秒级
 * @receiver [Long]
 * @return [Int]
 */
fun Long.timestampToTimeSeconds(): Int =
    (this / 1000).toInt()

/**
 * 将秒级时间戳转换为毫秒级
 * @receiver [Int]
 * @return [Long]
 */
fun Int.timeSecondsToTimestamp(): Long =
    (this * 1000L)

/**
 * 将时间戳转换为Instant
 * @receiver [Long] 时间戳，单位毫秒
 * @return [Instant]
 */
@OptIn(ExperimentalTime::class)
fun Long.timestampToInstant(): Instant =
    Instant.fromEpochMilliseconds(this)

/**
 * 将时间戳转换为Instant
 * @receiver [Int] 时间戳，单位秒
 * @return [Instant]
 */
@OptIn(ExperimentalTime::class)
fun Int.timeSecondsToInstant(): Instant =
    timeSecondsToTimestamp().timestampToInstant()

/**
 * 将时间戳转换为LocalDateTime
 * @receiver [Long] 时间戳，单位毫秒
 * @param timeZone [TimeZone] 时区
 * @return [LocalDateTime]
 */
@OptIn(ExperimentalTime::class)
fun Long.timestampToLocalDateTime(timeZone: TimeZone = TimeZone.currentSystemDefault()): LocalDateTime =
    timestampToInstant().toLocalDateTime(timeZone)

/**
 * 将时间戳转换为UTC时间
 * @receiver [Long] 时间戳，单位毫秒
 * @return [LocalDateTime]
 */
fun Long.timestampToUTCDateTime(): LocalDateTime =
    timestampToLocalDateTime(TimeZone.UTC)

/**
 * 将时间戳转换为系统时间
 * @receiver [Long] 时间戳，单位毫秒
 * @return [LocalDateTime]
 */
fun Long.timestampToSystemDateTime(): LocalDateTime =
    timestampToLocalDateTime(TimeZone.currentSystemDefault())

/**
 * 将时间戳转换为LocalDateTime
 * @receiver [Int] 时间戳，单位秒
 * @param timeZone [TimeZone] 时区
 * @return [LocalDateTime]
 */
fun Int.timeSecondsToLocalDateTime(timeZone: TimeZone = TimeZone.currentSystemDefault()): LocalDateTime =
    timeSecondsToTimestamp().timestampToLocalDateTime(timeZone)

/**
 * 将时间戳转换为UTC时间
 * @receiver [Int] 时间戳，单位秒
 * @return [LocalDateTime]
 */
fun Int.timeSecondsToUTCDateTime(): LocalDateTime =
    timeSecondsToTimestamp().timestampToUTCDateTime()

/**
 * 将时间戳转换为系统时间
 * @receiver [Int] 时间戳，单位秒
 * @return [LocalDateTime]
 */
fun Int.timeSecondsToSystemDateTime(): LocalDateTime =
    timeSecondsToTimestamp().timestampToSystemDateTime()

fun timestamp(
    year: Int = curYear,
    month: Int = curMonth,
    day: Int = curDay,
    hour: Int = curHour,
    minute: Int = curMinute,
    second: Int = curSecond,
    millisecond: Int = curMillisecond
): Long = LocalDateTime(year, month, day, hour, minute, second, millisecond * 1000000).toTimestamp()
@file:JsExport
package com.d10ng.datelib

import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.js.JsExport
import kotlin.js.JsName
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

/**
 * 构建毫秒级时间戳
 * @param year [Int] 年
 * @param month [Int] 月
 * @param day [Int] 日
 * @param hour [Int] 时
 * @param minute [Int] 分
 * @param second [Int] 秒
 * @param millisecond [Int] 毫秒
 * @param timeZone [TimeZone] 时区
 */
@JsName("timestamp")
fun timestamp(
    year: Int,
    month: Int,
    day: Int,
    hour: Int,
    minute: Int,
    second: Int,
    millisecond: Int,
    timeZone: TimeZone
): Long =
    LocalDateTime(year, month, day, hour, minute, second, millisecond * 1000000)
        .toTimestamp(timeZone)

/**
 * 构建毫秒级时间戳
 * @param year [Int] 年
 * @param month [Int] 月
 * @param day [Int] 日
 * @param hour [Int] 时
 * @param minute [Int] 分
 * @param second [Int] 秒
 * @param timeZone [TimeZone] 时区
 */
@JsName("timestampByYMDHMS")
fun timestamp(
    year: Int,
    month: Int,
    day: Int,
    hour: Int,
    minute: Int,
    second: Int,
    timeZone: TimeZone
): Long = timestamp(year, month, day, hour, minute, second, 0, timeZone)

/**
 * 构建毫秒级时间戳
 * @param year [Int] 年
 * @param month [Int] 月
 * @param day [Int] 日
 * @param timeZone [TimeZone] 时区
 */
@JsName("timestampByYMD")
fun timestamp(
    year: Int,
    month: Int,
    day: Int,
    timeZone: TimeZone
): Long = timestamp(year, month, day, 0, 0, 0, timeZone)

/**
 * 构建毫秒级时间戳，UTC时区
 * @param year [Int] 年
 * @param month [Int] 月
 * @param day [Int] 日
 * @param hour [Int] 时
 * @param minute [Int] 分
 * @param second [Int] 秒
 * @param millisecond [Int] 毫秒
 */
@JsName("timestampUTC")
fun timestampUTC(
    year: Int,
    month: Int,
    day: Int,
    hour: Int,
    minute: Int,
    second: Int,
    millisecond: Int
): Long = timestamp(year, month, day, hour, minute, second, millisecond, TimeZone.UTC)

/**
 * 构建毫秒级时间戳，UTC时区
 * @param year [Int] 年
 * @param month [Int] 月
 * @param day [Int] 日
 * @param hour [Int] 时
 * @param minute [Int] 分
 * @param second [Int] 秒
 */
@JsName("timestampUTCByYMDHMS")
fun timestampUTC(
    year: Int,
    month: Int,
    day: Int,
    hour: Int,
    minute: Int,
    second: Int
): Long = timestamp(year, month, day, hour, minute, second, TimeZone.UTC)

/**
 * 构建毫秒级时间戳，UTC时区
 * @param year [Int] 年
 * @param month [Int] 月
 * @param day [Int] 日
 */
@JsName("timestampUTCByYMD")
fun timestampUTC(
    year: Int,
    month: Int,
    day: Int
): Long = timestamp(year, month, day, TimeZone.UTC)

/**
 * 构建毫秒级时间戳，系统时区
 * @param year [Int] 年
 * @param month [Int] 月
 * @param day [Int] 日
 * @param hour [Int] 时
 * @param minute [Int] 分
 * @param second [Int] 秒
 * @param millisecond [Int] 毫秒
 */
@JsName("timestampSystem")
fun timestampSystem(
    year: Int,
    month: Int,
    day: Int,
    hour: Int,
    minute: Int,
    second: Int,
    millisecond: Int
): Long = timestamp(year, month, day, hour, minute, second, millisecond, TimeZone.currentSystemDefault())

/**
 * 构建毫秒级时间戳，系统时区
 * @param year [Int] 年
 * @param month [Int] 月
 * @param day [Int] 日
 * @param hour [Int] 时
 * @param minute [Int] 分
 * @param second [Int] 秒
 */
@JsName("timestampSystemByYMDHMS")
fun timestampSystem(
    year: Int,
    month: Int,
    day: Int,
    hour: Int,
    minute: Int,
    second: Int
): Long = timestamp(year, month, day, hour, minute, second, TimeZone.currentSystemDefault())

/**
 * 构建毫秒级时间戳，系统时区
 * @param year [Int] 年
 * @param month [Int] 月
 * @param day [Int] 日
 */
@JsName("timestampSystemByYMD")
fun timestampSystem(
    year: Int,
    month: Int,
    day: Int
): Long = timestamp(year, month, day, TimeZone.currentSystemDefault())
@file:JsExport

package com.d10ng.datelib

import kotlinx.datetime.*
import kotlin.js.JsExport
import kotlin.time.Clock
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

/**
 * 获取当前时间
 * @return [Instant]
 */
@OptIn(ExperimentalTime::class)
fun now(): Instant =
    Clock.System.now()

/**
 * 获取当前时间戳，单位毫秒
 * @return [Long]
 */
@OptIn(ExperimentalTime::class)
fun nowTimestamp(): Long =
    now().toTimestamp()

/**
 * 获取当前时间戳，单位秒
 * @return [Int]
 */
fun nowTimeSeconds(): Int =
    (nowTimestamp() / 1000).toInt()

/**
 * 获取当前时间
 * @param timeZone [TimeZone] 时区
 * @return [LocalDateTime]
 */
@OptIn(ExperimentalTime::class)
fun nowLocalDateTime(timeZone: TimeZone = TimeZone.currentSystemDefault()): LocalDateTime =
    now().toLocalDateTime(timeZone)

/**
 * 获取当前UTC时间
 * @return [LocalDateTime]
 */
fun nowUTCDateTime(): LocalDateTime =
    nowLocalDateTime(TimeZone.UTC)

/**
 * 获取当前系统时间
 * @return [LocalDateTime]
 */
fun nowSystemDateTime(): LocalDateTime =
    nowLocalDateTime(TimeZone.currentSystemDefault())

/**
 * 获取当前系统时间
 * @return [LocalDateTime]
 */
@Deprecated("Replace with nowSystemDateTime()", ReplaceWith("nowSystemDateTime()"))
fun nowSystemLocalDateTime(): LocalDateTime = nowSystemDateTime()

/**
 * 获取当前时间
 * @param timeZone [TimeZone] 时区
 * @return [LocalDate]
 */
fun nowLocalDate(timeZone: TimeZone = TimeZone.currentSystemDefault()): LocalDate =
    nowLocalDateTime(timeZone).date

/**
 * 获取当前UTC时间
 * @return [LocalDate]
 */
fun nowUTCDate(): LocalDate =
    nowLocalDate(TimeZone.UTC)

/**
 * 获取当前系统时间
 * @return [LocalDate]
 */
fun nowSystemDate(): LocalDate =
    nowLocalDate(TimeZone.currentSystemDefault())

/**
 * 获取当前系统时间
 * @return [LocalDate]
 */
@Deprecated("Replace with nowSystemDate()", ReplaceWith("nowSystemDate()"))
fun nowSystemLocalDate(): LocalDate = nowSystemDate()

/**
 * 获取当前时间
 * @param timeZone [TimeZone] 时区
 * @return [LocalTime]
 */
fun nowLocalTime(timeZone: TimeZone = TimeZone.currentSystemDefault()): LocalTime =
    nowLocalDateTime(timeZone).time

/**
 * 获取当前UTC时间
 * @return [LocalTime]
 */
fun nowUTCTime(): LocalTime =
    nowLocalTime(TimeZone.UTC)

/**
 * 获取当前系统时间
 * @return [LocalTime]
 */
fun nowSystemTime(): LocalTime =
    nowLocalTime(TimeZone.currentSystemDefault())

/**
 * 获取当前系统时间
 * @return [LocalTime]
 */
@Deprecated("Replace with nowSystemTime()", ReplaceWith("nowSystemTime()"))
fun nowSystemLocalTime(): LocalTime = nowSystemTime()
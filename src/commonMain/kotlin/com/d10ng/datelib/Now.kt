@file:JsExport
package com.d10ng.datelib

import kotlinx.datetime.*
import kotlin.js.JsExport

/**
 * 获取当前时间
 * @return [Instant]
 */
fun now(): Instant =
    Clock.System.now()

/**
 * 获取当前时间戳，单位毫秒
 * @return [Long]
 */
fun nowTimestamp(): Long =
    now().toEpochMilliseconds()

/**
 * 获取当前时间戳，单位秒
 * @return [Int]
 */
fun nowTimeSeconds(): Int =
    now().epochSeconds.toInt()

/**
 * 获取当前时间
 * @param timeZone [TimeZone] 时区
 * @return [LocalDateTime]
 */
fun nowLocalDateTime(timeZone: TimeZone = TimeZone.currentSystemDefault()): LocalDateTime =
    now().toLocalDateTime(timeZone)

/**
 * 获取当前系统时间
 * @return [LocalDateTime]
 */
fun nowSystemLocalDateTime(): LocalDateTime =
    nowLocalDateTime(TimeZone.currentSystemDefault())

/**
 * 获取当前时间
 * @param timeZone [TimeZone] 时区
 * @return [LocalDate]
 */
fun nowLocalDate(timeZone: TimeZone = TimeZone.currentSystemDefault()): LocalDate =
    nowLocalDateTime(timeZone).date

/**
 * 获取当前系统时间
 * @return [LocalDate]
 */
fun nowSystemLocalDate(): LocalDate =
    nowLocalDate(TimeZone.currentSystemDefault())

/**
 * 获取当前时间
 * @param timeZone [TimeZone] 时区
 * @return [LocalTime]
 */
fun nowLocalTime(timeZone: TimeZone = TimeZone.currentSystemDefault()): LocalTime =
    nowLocalDateTime(timeZone).time

/**
 * 获取当前系统时间
 * @return [LocalTime]
 */
fun nowSystemLocalTime(): LocalTime =
    nowLocalTime(TimeZone.currentSystemDefault())
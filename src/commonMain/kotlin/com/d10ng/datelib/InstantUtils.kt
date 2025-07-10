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
 * 将Instant转换成毫秒时间戳
 * @receiver [Instant]
 * @return [Long]
 */
@OptIn(ExperimentalTime::class)
@JsName("instantToTimestamp")
fun Instant.toTimestamp(): Long =
    toEpochMilliseconds()

/**
 * 将Instant转换成秒时间戳
 * @receiver [Instant]
 * @return [Int]
 */
@OptIn(ExperimentalTime::class)
@JsName("instantToTimeSeconds")
fun Instant.toTimeSeconds(): Int =
    (toTimestamp() / 1000).toInt()

/**
 * 将Instant转换为UTC时区的LocalDateTime
 * @receiver [Instant]
 * @return [LocalDateTime]
 */
@OptIn(ExperimentalTime::class)
@JsName("instantToUTCDateTime")
fun Instant.toUTCDateTime(): LocalDateTime =
    toLocalDateTime(TimeZone.UTC)

/**
 * 将Instant转换为系统默认时区的LocalDateTime
 * @receiver [Instant]
 * @return [LocalDateTime]
 */
@OptIn(ExperimentalTime::class)
@JsName("instantToSystemLocalDateTime")
fun Instant.toSystemDateTime(): LocalDateTime =
    toLocalDateTime(TimeZone.currentSystemDefault())

/**
 * 将Instant转换为系统默认时区的LocalDateTime
 * @receiver [Instant]
 * @return [LocalDateTime]
 */
@OptIn(ExperimentalTime::class)
@JsName("instantToSystemLocalDateTime")
@Deprecated(
    "Use toSystemDateTime() instead",
    ReplaceWith("toSystemDateTime()"),
    DeprecationLevel.WARNING
)
fun Instant.toSystemLocalDateTime(): LocalDateTime = toSystemDateTime()

/**
 * 从Instant获取Int类型的时间戳，单位秒
 * @receiver [Instant]
 * @return [Int]
 */
@OptIn(ExperimentalTime::class)
@JsName("instantEpochSecondsInt")
@Deprecated(
    "Use toTimeSeconds() instead",
    ReplaceWith("toTimeSeconds()"),
    DeprecationLevel.WARNING
)
fun Instant.epochSecondsInt(): Int = epochSeconds.toInt()
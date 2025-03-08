@file:JsExport
package com.d10ng.datelib

import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.js.JsExport

/**
 * 将Instant转换为系统默认时区的LocalDateTime
 * @receiver [Instant]
 * @return [LocalDateTime]
 */
fun Instant.toSystemLocalDateTime(): LocalDateTime =
    toLocalDateTime(TimeZone.currentSystemDefault())

/**
 * 从Instant获取Int类型的时间戳，单位秒
 * @receiver [Instant]
 * @return [Int]
 */
fun Instant.epochSecondsInt(): Int = epochSeconds.toInt()
@file:JsExport
package com.d10ng.datelib

import kotlinx.datetime.LocalTime
import kotlinx.datetime.format
import kotlin.js.JsExport
import kotlin.js.JsName

/**
 * 复制一个新的LocalTime对象
 * @receiver [LocalTime]
 * @param hour [Int]
 * @param minute [Int]
 * @param second [Int]
 * @param nanosecond [Int]
 * @return [LocalTime]
 */
@JsName("copyLocalTime")
fun LocalTime.copy(
    hour: Int = this.hour,
    minute: Int = this.minute,
    second: Int = this.second,
    nanosecond: Int = this.nanosecond
): LocalTime = LocalTime(
    hour = hour.coerceIn(0, 23),
    minute = minute.coerceIn(0, 59),
    second = second.coerceIn(0, 59),
    nanosecond = nanosecond.coerceIn(0, 999999999)
)

/**
 * 获取ISO格式的时间字符串
 * @receiver [LocalTime]
 * @return [String]
 */
@JsName("localTimeFormatISO")
fun LocalTime.formatISO(): String =
    format(LocalTime.Formats.ISO)

/**
 * 获取指定格式的时间字符串
 * @receiver [LocalTime]
 * @param pattern [String] 格式
 * @return [String]
 */
@JsName("localTimeFormatPattern")
fun LocalTime.formatPattern(pattern: String): String =
    format(buildTimeFormat(pattern))

/**
 * 将字符串转换为LocalTime
 * @receiver [String]
 * @param pattern [String] 格式
 * @return [LocalTime]
 */
@JsName("stringToLocalTime")
fun String.toLocalTime(pattern: String): LocalTime =
    buildTimeFormat(pattern).parse(this)
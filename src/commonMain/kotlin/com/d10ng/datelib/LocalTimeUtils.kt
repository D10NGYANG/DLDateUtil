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
 * 将LocalTime转换为ISO字符串
 * @receiver [LocalTime]
 * @return [String]
 */
@JsName("localTimeToISOString")
fun LocalTime.toISOString(): String =
    format(LocalTime.Formats.ISO)
@file:JsExport
package com.d10ng.datelib

import kotlinx.datetime.LocalTime
import kotlin.js.JsExport

/**
 * 将毫秒秒转换成倒计时字符串，如00:00:01
 * @receiver Long
 * @return String
 */
@Deprecated("请使用LocalTime.fromMillisecondOfDay(this.toInt()).toISOString()", ReplaceWith("LocalTime.fromMillisecondOfDay(this.toInt()).toISOString()"), DeprecationLevel.WARNING)
fun Long.time2String(): String {
    return LocalTime.fromMillisecondOfDay(this.toInt()).toISOString()
}

/**
 * 将秒转换成倒计时字符串，如00:00:01
 * @receiver Int
 * @return String
 */
@Deprecated("请使用LocalTime.fromSecondOfDay(this).toISOString()", ReplaceWith("LocalTime.fromSecondOfDay(this).toISOString()"), DeprecationLevel.WARNING)
fun Int.timeSecond2String(): String {
    return LocalTime.fromSecondOfDay(this).toISOString()
}
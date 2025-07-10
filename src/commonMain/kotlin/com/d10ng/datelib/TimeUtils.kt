@file:JsExport
package com.d10ng.datelib

import kotlinx.datetime.LocalTime
import kotlin.js.JsExport

/**
 * 将毫秒秒转换成倒计时字符串，如00:00:01
 * @receiver Long
 * @return String
 */
@Deprecated("请使用LocalTime.fromMillisecondOfDay(this.toInt()).formatISO()", ReplaceWith("LocalTime.fromMillisecondOfDay(this.toInt()).formatISO()"))
fun Long.time2String(): String {
    return LocalTime.fromMillisecondOfDay(this.toInt()).formatISO()
}

/**
 * 将秒转换成倒计时字符串，如00:00:01
 * @receiver Int
 * @return String
 */
@Deprecated("请使用LocalTime.fromSecondOfDay(this).formatISO()", ReplaceWith("LocalTime.fromSecondOfDay(this).formatISO()"))
fun Int.timeSecond2String(): String {
    return LocalTime.fromSecondOfDay(this).formatISO()
}
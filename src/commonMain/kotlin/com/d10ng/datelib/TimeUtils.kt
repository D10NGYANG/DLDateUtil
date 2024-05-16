@file:JsExport
package com.d10ng.datelib

import kotlin.js.JsExport

/**
 * 将毫秒秒转换成倒计时字符串，如00:00:01
 * @receiver Long
 * @return String
 */
fun Long.time2String(): String {
    return (this / 1000).toInt().timeSecond2String()
}

/**
 * 将秒转换成倒计时字符串，如00:00:01
 * @receiver Int
 * @return String
 */
fun Int.timeSecond2String(): String {
    if (this <= 0) {
        return "00:00:00"
    }
    val hour = this / (60 * 60)
    val minute = (this - hour * 60 * 60) / 60
    val second = this - hour * 60 * 60 - minute * 60
    val builder = StringBuilder()
        .append(hour.toString().padStart(2, '0')).append(":")
        .append(minute.toString().padStart(2, '0')).append(":")
        .append(second.toString().padStart(2, '0'))
    return builder.toString()
}
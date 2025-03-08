@file:JsExport
package com.d10ng.datelib

import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.isoDayNumber
import kotlin.js.JsExport

/**
 * 获取星期的中文名，如：星期一
 * @receiver [DayOfWeek]
 * @return [String]
 */
fun DayOfWeek.chineseName(): String {
    return WeekTextType.CN.list[isoDayNumber - 1]
}

/**
 * 获取星期的中文名，如：周一
 * @receiver [DayOfWeek]
 * @return [String]
 */
fun DayOfWeek.chineseShortName(): String {
    return WeekTextType.CN_SHORT.list[isoDayNumber - 1]
}

/**
 * 获取星期的中文名，如：一
 * @receiver [DayOfWeek]
 * @return [String]
 */
fun DayOfWeek.chineseMiniName(): String {
    return WeekTextType.CN_MINI.list[isoDayNumber - 1]
}

/**
 * 获取星期的英文名，如：MONDAY
 * @receiver [DayOfWeek]
 * @return [String]
 */
fun DayOfWeek.englishName(): String {
    return WeekTextType.EN.list[isoDayNumber - 1]
}

/**
 * 获取星期的英文名，如：MON
 * @receiver [DayOfWeek]
 * @return [String]
 */
fun DayOfWeek.englishShortName(): String {
    return WeekTextType.EN_SHORT.list[isoDayNumber - 1]
}
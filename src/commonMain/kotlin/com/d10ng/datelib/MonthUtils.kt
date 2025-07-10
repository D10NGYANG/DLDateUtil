@file:JsExport
package com.d10ng.datelib

import com.d10ng.datelib.nowSystemDate
import kotlinx.datetime.*
import kotlin.js.JsExport
import kotlin.js.JsName

/**
 * 获取月份的中文名，如：一月
 * @receiver [Month]
 * @return [String]
 */
@JsName("monthChineseName")
fun Month.chineseName(): String {
    return MonthTextType.CN.list[ordinal]
}

/**
 * 获取月份的英文名，如：JANUARY
 * @receiver [Month]
 * @return [String]
 */
@JsName("monthEnglishName")
fun Month.englishName(): String {
    return MonthTextType.EN.list[ordinal]
}

/**
 * 获取月份的英文名，如：JAN
 * @receiver [Month]
 * @return [String]
 */
@JsName("monthEnglishShortName")
fun Month.englishShortName(): String {
    return MonthTextType.EN_SHORT.list[ordinal]
}

/**
 * 获取指定月份的天数
 * @param year [Int] 年
 * @param month [Int] 月
 * @return [Int] 天数
 */
fun daysOfMonth(year: Int, month: Int): Int {
    val start = LocalDate(year, month, 1)
    val end = start + DatePeriod(months = 1)
    return start.daysUntil(end)
}

/**
 * 获取当前月份的天数
 * @return [Int] 天数
 */
fun currentMonthDays(): Int {
    val now = nowSystemDate()
    return daysOfMonth(now.year, now.monthNumber)
}
@file:JsExport
package com.d10ng.datelib

import kotlinx.datetime.*
import kotlinx.datetime.LocalDate
import kotlin.js.JsExport
import kotlin.js.JsName

/**
 * 获取月份的文本
 * @receiver [Month]
 * @param type [MonthTextType] 文本类型
 * @return [String]
 */
@JsName("monthName")
fun Month.name(type: MonthTextType): String =
    type.list[ordinal]

/**
 * 获取月份的中文名，如：一月
 * @receiver [Month]
 * @return [String]
 */
@JsName("monthChineseName")
fun Month.chineseName(): String =
    name(MonthTextType.CN)

/**
 * 获取月份的英文名，如：JANUARY
 * @receiver [Month]
 * @return [String]
 */
@JsName("monthEnglishName")
fun Month.englishName(): String =
    name(MonthTextType.EN)

/**
 * 获取月份的英文名，如：JAN
 * @receiver [Month]
 * @return [String]
 */
@JsName("monthEnglishShortName")
fun Month.englishShortName(): String =
    name(MonthTextType.EN_SHORT)

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
    return daysOfMonth(now.year, now.month.number)
}
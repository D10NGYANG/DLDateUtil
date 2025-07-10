@file:JsExport
package com.d10ng.datelib

import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.isoDayNumber
import kotlin.js.JsExport
import kotlin.js.JsName

/**
 * 获取星期的文本，如：星期一
 * @receiver [DayOfWeek]
 * @param type [WeekTextType] 文本类型
 * @return [String]
 */
@JsName("dayOfWeekName")
fun DayOfWeek.name(type: WeekTextType): String =
    type.list[isoDayNumber - 1]

/**
 * 获取星期的中文名，如：星期一
 * @receiver [DayOfWeek]
 * @return [String]
 */
@JsName("dayOfWeekChineseName")
fun DayOfWeek.chineseName(): String =
    name(WeekTextType.CN)

/**
 * 获取星期的中文名，如：周一
 * @receiver [DayOfWeek]
 * @return [String]
 */
@JsName("dayOfWeekChineseShortName")
fun DayOfWeek.chineseShortName(): String =
    name(WeekTextType.CN_SHORT)

/**
 * 获取星期的中文名，如：一
 * @receiver [DayOfWeek]
 * @return [String]
 */
@JsName("dayOfWeekChineseMiniName")
fun DayOfWeek.chineseMiniName(): String =
    name(WeekTextType.CN_MINI)

/**
 * 获取星期的英文名，如：MONDAY
 * @receiver [DayOfWeek]
 * @return [String]
 */
@JsName("dayOfWeekEnglishName")
fun DayOfWeek.englishName(): String =
    name(WeekTextType.EN)

/**
 * 获取星期的英文名，如：MON
 * @receiver [DayOfWeek]
 * @return [String]
 */
@JsName("dayOfWeekEnglishShortName")
fun DayOfWeek.englishShortName(): String =
    name(WeekTextType.EN_SHORT)
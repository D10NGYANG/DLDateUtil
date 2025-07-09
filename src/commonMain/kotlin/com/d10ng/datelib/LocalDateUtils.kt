@file:JsExport
@file:OptIn(ExperimentalTime::class)

package com.d10ng.datelib

import kotlinx.datetime.*
import kotlin.js.JsExport
import kotlin.js.JsName
import kotlin.time.ExperimentalTime

/**
 * 将LocalDate转换为LocalDateTime
 * @receiver [LocalDate]
 * @return [LocalDateTime]
 */
@JsName("localDateToLocalDateTime")
fun LocalDate.toLocalDateTime(): LocalDateTime =
    LocalDateTime(this, LocalTime(0, 0))

/**
 * 将LocalDate转换为系统默认时区的时间戳
 * @receiver [LocalDate]
 * @return [Instant]
 */
@JsName("localDateToSystemInstant")
fun LocalDate.toSystemInstant(): Instant =
    toLocalDateTime().toSystemInstant()

/**
 * 将LocalDate转换为系统默认时区的时间戳，单位毫秒
 * @receiver [LocalDate]
 * @return [Long]
 */
@JsName("localDateToEpochMilliseconds")
fun LocalDate.toEpochMilliseconds(): Long =
    toSystemInstant().toEpochMilliseconds()

/**
 * 将LocalDate转换为系统默认时区的时间戳，单位秒
 * @receiver [LocalDate]
 * @return [Int]
 */
@JsName("localDateToEpochSeconds")
fun LocalDate.toEpochSeconds(): Int =
    toSystemInstant().epochSeconds.toInt()

/**
 * 获取日期是当年的第几周
 * @receiver [LocalDate]
 * @return [Int]
 */
@JsName("localDateWeekOfYear")
fun LocalDate.weekOfYear(): Int {
    // 获取对应年份的第一天是星期几
    val startDayOfWeek = LocalDate(year, 1, 1).dayOfWeek.isoDayNumber
    // 获取当前时间是当年的第几天
    val endDayOfYear = dayOfYear
    // 计算偏移天数和偏移周数
    val (offsetDay, offsetWeek) = if (startDayOfWeek == 1) {
        0 to 0
    } else {
        (8 - startDayOfWeek) to 1
    }
    return if (endDayOfYear <= offsetDay) 1
    else (endDayOfYear - offsetDay) / 7 + 1 + offsetWeek
}

/**
 * 获取日期是当月的第几周
 * @receiver [LocalDate]
 * @param isFirstMondayAsFirstWeek [Boolean] true: 取月中的第一个周一开始算周数；false: 取月的1号作为第一周
 * @return [Int]
 */
@JsName("localDateWeekOfMonth")
fun LocalDate.weekOfMonth(isFirstMondayAsFirstWeek: Boolean = true): Int {
    val start = LocalDate(year, monthNumber, 1)
    val startWeekOfDay = start.dayOfWeek.isoDayNumber
    if (isFirstMondayAsFirstWeek) {
        var firstMonday = start
        if (startWeekOfDay != 1) {
            firstMonday = start + DatePeriod(days = 8 - startWeekOfDay)
        }
        return (dayOfMonth - firstMonday.dayOfMonth) / 7 + 1
    } else {
        val firstWeekOffset = 8 - startWeekOfDay
        if (dayOfMonth <= firstWeekOffset) return 1
        return (dayOfMonth - firstWeekOffset) / 7 + 2
    }
}

/**
 * 获取当前日期的农历信息
 * @receiver [LocalDate]
 * @return [CalendarInfo]
 */
@JsName("localDateLunarCalendar")
fun LocalDate.lunarCalendar(): CalendarInfo =
    LunarDateUtil.solar2lunar(year, monthNumber, dayOfMonth)!!

/**
 * 复制日期
 * @receiver [LocalDate]
 * @param year [Int] 年
 * @param month [Int] 月
 * @param day [Int] 日
 * @return [LocalDate]
 */
@JsName("copyLocalDate")
fun LocalDate.copy(
    year: Int = this.year,
    month: Int = this.monthNumber,
    day: Int = this.dayOfMonth
): LocalDate {
    val y = year.coerceIn(1970, 9999)
    val m = month.coerceIn(1, 12)
    val d = day.coerceIn(1, daysOfMonth(y, m))
    return LocalDate(y, m, d)
}

/**
 * 判断日期是否为今天
 * @receiver [LocalDate]
 * @return [Boolean]
 */
@JsName("localDateIsToday")
fun LocalDate.isToday(): Boolean =
    minus(nowSystemLocalDate()).days == 0

/**
 * 判断日期是否为昨天
 * @receiver [LocalDate]
 * @return [Boolean]
 */
@JsName("localDateIsYesterday")
fun LocalDate.isYesterday(): Boolean =
    minus(nowSystemLocalDate()).days == 1

/**
 * 判断日期是否为明天
 * @receiver [LocalDate]
 * @return [Boolean]
 */
@JsName("localDateIsTomorrow")
fun LocalDate.isTomorrow(): Boolean =
    minus(nowSystemLocalDate()).days == -1

/**
 * 判断日期是否为前天
 * @receiver [LocalDate]
 * @return [Boolean]
 */
@JsName("localDateIsBeforeYesterday")
fun LocalDate.isBeforeYesterday(): Boolean =
    minus(nowSystemLocalDate()).days == 2
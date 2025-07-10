@file:JsExport

package com.d10ng.datelib

import kotlinx.datetime.*
import kotlin.js.JsExport
import kotlin.js.JsName

/**
 * 将LocalDate转换为LocalDateTime，时间为00:00:00
 * @receiver [LocalDate]
 * @return [LocalDateTime]
 */
@JsName("localDateToLocalDateTime")
fun LocalDate.atZeroTime(): LocalDateTime =
    atTime(LocalTime(0, 0))

/**
 * 获取日期是当年的第几周
 * > 自该年第一周（以1月1日为基准）起，每周从星期一开始算周数。
 * @receiver [LocalDate]
 * @return [Int]
 */
@JsName("localDateWeekOfYear")
fun LocalDate.weekOfYear(): Int {
    val jan1st = LocalDate(this.year, 1, 1)
    val jan1WeekStart = jan1st.minus((jan1st.dayOfWeek.ordinal), DateTimeUnit.DAY)
    val daysBetween = jan1WeekStart.daysUntil(this)
    return (daysBetween / 7) + 1
}

/**
 * 获取日期是当年的第几周，使用ISO标准
 * > 自该年第一周（以1月4日为基准）起，每周从星期一开始算周数。
 * @receiver [LocalDate]
 * @return [Int]
 */
@JsName("localDateIsoWeekOfYear")
fun LocalDate.isoWeekOfYear(): Int {
    val firstWeekInYearStart: (Int) -> LocalDate = { year ->
        val jan1st = LocalDate(year, 1, 1)
        val previousMonday = jan1st.minus(jan1st.dayOfWeek.ordinal, DateTimeUnit.DAY)
        if (jan1st.dayOfWeek <= DayOfWeek.THURSDAY) previousMonday else previousMonday.plus(1, DateTimeUnit.WEEK)
    }
    if (firstWeekInYearStart(year + 1) < this) return 1
    val currentYearStart = firstWeekInYearStart(year)
    val start = if (this < currentYearStart) firstWeekInYearStart(year - 1) else currentYearStart
    return (start.until(this, DateTimeUnit.WEEK) + 1).toInt()
}

/**
 * 获取日期是当月的第几周
 * @receiver [LocalDate]
 * @param isFirstMondayAsFirstWeek [Boolean] true: 取月中的第一个周一开始算周数；false: 取月的1号作为第一周
 * @return [Int]
 */
@JsName("localDateWeekOfMonth")
fun LocalDate.weekOfMonth(isFirstMondayAsFirstWeek: Boolean = true): Int {
    val start = LocalDate(year, month.number, 1)
    val startWeekOfDay = start.dayOfWeek.isoDayNumber
    if (isFirstMondayAsFirstWeek) {
        var firstMonday = start
        if (startWeekOfDay != 1) {
            firstMonday = start + DatePeriod(days = 8 - startWeekOfDay)
        }
        val less = day - firstMonday.day
        return if (less < 0) 0
        else less / 7 + 1
    } else {
        val firstWeekOffset = 8 - startWeekOfDay
        return if (day <= firstWeekOffset) 1
        else if (day <= firstWeekOffset + 7) 2
        else (day - firstWeekOffset) / 7 + 2
    }
}

/**
 * 获取当前日期的农历信息
 * @receiver [LocalDate]
 * @return [CalendarInfo]
 */
@JsName("localDateLunarCalendar")
fun LocalDate.lunarCalendar(): CalendarInfo =
    LunarDateUtil.solar2lunar(year, month.number, day)!!

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
    month: Int = this.month.number,
    day: Int = this.day
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
    minus(nowSystemDate()).days == 0

/**
 * 判断日期是否为昨天
 * @receiver [LocalDate]
 * @return [Boolean]
 */
@JsName("localDateIsYesterday")
fun LocalDate.isYesterday(): Boolean =
    minus(nowSystemDate()).days == 1

/**
 * 判断日期是否为明天
 * @receiver [LocalDate]
 * @return [Boolean]
 */
@JsName("localDateIsTomorrow")
fun LocalDate.isTomorrow(): Boolean =
    minus(nowSystemDate()).days == -1

/**
 * 判断日期是否为前天
 * @receiver [LocalDate]
 * @return [Boolean]
 */
@JsName("localDateIsBeforeYesterday")
fun LocalDate.isBeforeYesterday(): Boolean =
    minus(nowSystemDate()).days == 2

/**
 * 获取ISO格式的日期字符串
 * @receiver [LocalDate]
 * @return [String]
 */
@JsName("localDateFormatISO")
fun LocalDate.formatISO(): String =
    format(LocalDate.Formats.ISO)
@file:JsExport
package com.d10ng.datelib

import kotlinx.datetime.*
import kotlin.js.JsExport

/**
 * 创建本地时间
 * @param milliseconds [Long] 毫秒
 * @param timeZone [TimeZone] 时区
 * @return [LocalDateTime]
 */
fun createLocalDateTime(milliseconds: Long, timeZone: TimeZone): LocalDateTime {
    val instant = Instant.fromEpochMilliseconds(milliseconds)
    return instant.toLocalDateTime(timeZone)
}

/**
 * 创建系统时区的本地时间
 * @param milliseconds [Long] 毫秒
 * @return [LocalDateTime]
 */
fun createSystemLocalDateTime(milliseconds: Long): LocalDateTime =
    createLocalDateTime(milliseconds, TimeZone.currentSystemDefault())

/**
 * 创建本地时间
 * @param seconds [Int] 秒
 * @param timeZone [TimeZone] 时区
 * @return [LocalDateTime]
 */
fun createLocalDateTime(seconds: Int, timeZone: TimeZone): LocalDateTime {
    val instant = Instant.fromEpochSeconds(seconds.toLong())
    return instant.toLocalDateTime(timeZone)
}

/**
 * 创建系统时区的本地时间
 * @param seconds [Int] 秒
 * @return [LocalDateTime]
 */
fun createSystemLocalDateTime(seconds: Int): LocalDateTime =
    createLocalDateTime(seconds, TimeZone.currentSystemDefault())

/**
 * 将LocalDateTime转换为系统默认时区的Instant
 * @receiver [LocalDateTime]
 * @return [Instant]
 */
fun LocalDateTime.toSystemInstant(): Instant =
    toInstant(TimeZone.currentSystemDefault())

/**
 * 将LocalDateTime转换为系统默认时区的时间戳，单位毫秒
 * @receiver [LocalDateTime]
 * @return [Int]
 */
fun LocalDateTime.toEpochMilliseconds(): Long =
    toSystemInstant().toEpochMilliseconds()

/**
 * 将LocalDateTime转换为系统默认时区的时间戳，单位秒
 * @receiver [LocalDateTime]
 * @return [Int]
 */
fun LocalDateTime.toEpochSeconds(): Int =
    toSystemInstant().epochSeconds.toInt()

/**
 * 获取毫秒
 * @receiver [LocalDateTime]
 * @return [Int]
 */
fun LocalDateTime.millisecond(): Int =
    nanosecond / 1000000

/**
 * 获取年中的周数
 * @receiver [LocalDateTime]
 * @return [Int]
 */
fun LocalDateTime.weekOfYear(): Int =
    date.weekOfYear()

/**
 * 获取月中的周数
 * @receiver [LocalDateTime]
 * @param isFirstMondayAsFirstWeek [Boolean] true: 取月中的第一个周一开始算周数；false: 取月的1号作为第一周
 * @return [Int]
 */
fun LocalDateTime.weekOfMonth(isFirstMondayAsFirstWeek: Boolean = true): Int =
    date.weekOfMonth(isFirstMondayAsFirstWeek)

/**
 * 获取当前日期的农历信息
 * @receiver [LocalDateTime]
 * @return [CalendarInfo]
 */
fun LocalDateTime.lunarCalendar(): CalendarInfo = date.lunarCalendar()

/**
 * 复制日期时间
 * @receiver [LocalDateTime]
 * @param year [Int] 年
 * @param month [Int] 月
 * @param day [Int] 日
 * @param hour [Int] 时
 * @param minute [Int] 分
 * @param second [Int] 秒
 * @param nanosecond [Int] 纳秒
 * @return [LocalDateTime]
 */
fun LocalDateTime.copy(
    year: Int = this.year,
    month: Int = this.monthNumber,
    day: Int = this.dayOfMonth,
    hour: Int = this.hour,
    minute: Int = this.minute,
    second: Int = this.second,
    nanosecond: Int = this.nanosecond
): LocalDateTime {
    return LocalDateTime(year, month, day, hour, minute, second, nanosecond)
}

/**
 * 判断日期时间是否是今天
 * @receiver [LocalDateTime]
 * @return [Boolean]
 */
fun LocalDateTime.isToday(): Boolean =
    date.isToday()

/**
 * 判断日期时间是否是昨天
 * @receiver [LocalDateTime]
 * @return [Boolean]
 */
fun LocalDateTime.isYesterday(): Boolean =
    date.isYesterday()

/**
 * 判断日期时间是否是明天
 * @receiver [LocalDateTime]
 * @return [Boolean]
 */
fun LocalDateTime.isTomorrow(): Boolean =
    date.isTomorrow()

/**
 * 判断日期是否为前天
 * @receiver [LocalDateTime]
 * @return [Boolean]
 */
fun LocalDateTime.isBeforeYesterday(): Boolean =
    date.isBeforeYesterday()
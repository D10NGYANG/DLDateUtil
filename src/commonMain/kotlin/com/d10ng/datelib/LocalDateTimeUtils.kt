@file:JsExport
@file:OptIn(ExperimentalTime::class)

package com.d10ng.datelib

import kotlinx.datetime.*
import kotlin.js.JsExport
import kotlin.js.JsName
import kotlin.time.ExperimentalTime

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
 * 创建UTC时区的本地时间
 * @param milliseconds [Long] 毫秒
 * @return [LocalDateTime]
 */
fun createUTCDateTime(milliseconds: Long): LocalDateTime =
    createLocalDateTime(milliseconds, TimeZone.UTC)

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
@JsName("createLocalDateTimeBySeconds")
fun createLocalDateTime(seconds: Int, timeZone: TimeZone): LocalDateTime {
    val instant = Instant.fromEpochSeconds(seconds.toLong())
    return instant.toLocalDateTime(timeZone)
}

/**
 * 创建UTC时区的本地时间
 * @param seconds [Int] 秒
 * @return [LocalDateTime]
 */
@JsName("createUTCDateTimeBySeconds")
fun createUTCDateTime(seconds: Int): LocalDateTime =
    createLocalDateTime(seconds, TimeZone.UTC)

/**
 * 创建系统时区的本地时间
 * @param seconds [Int] 秒
 * @return [LocalDateTime]
 */
@JsName("createSystemLocalDateTimeBySeconds")
fun createSystemLocalDateTime(seconds: Int): LocalDateTime =
    createLocalDateTime(seconds, TimeZone.currentSystemDefault())

/**
 * 将LocalDateTime转换为UTC时区的Instant
 * @receiver [LocalDateTime]
 * @return [Instant]
 */
@JsName("localDateTimeToUTCInstant")
fun LocalDateTime.toUTCInstant(): Instant =
    toInstant(TimeZone.UTC)

/**
 * 将LocalDateTime转换为系统默认时区的Instant
 * @receiver [LocalDateTime]
 * @return [Instant]
 */
@JsName("localDateTimeToSystemInstant")
fun LocalDateTime.toSystemInstant(): Instant =
    toInstant(TimeZone.currentSystemDefault())

/**
 * 将LocalDateTime转换为UTC时区的时间戳，单位毫秒
 * @receiver [LocalDateTime]
 * @return [Int]
 */
@JsName("localDateTimeToUTCEpochMilliseconds")
fun LocalDateTime.toUTCEpochMilliseconds(): Long =
    toUTCInstant().toEpochMilliseconds()

/**
 * 将LocalDateTime转换为系统默认时区的时间戳，单位毫秒
 * @receiver [LocalDateTime]
 * @return [Int]
 */
@JsName("localDateTimeToEpochMilliseconds")
fun LocalDateTime.toEpochMilliseconds(): Long =
    toSystemInstant().toEpochMilliseconds()

/**
 * 将LocalDateTime转换为UTC时区的时间戳，单位秒
 * @receiver [LocalDateTime]
 * @return [Int]
 */
@JsName("localDateTimeToUTCEpochSeconds")
fun LocalDateTime.toUTCEpochSeconds(): Int =
    toUTCInstant().epochSecondsInt()

/**
 * 将LocalDateTime转换为系统默认时区的时间戳，单位秒
 * @receiver [LocalDateTime]
 * @return [Int]
 */
@JsName("localDateTimeToEpochSeconds")
fun LocalDateTime.toEpochSeconds(): Int =
    toSystemInstant().epochSecondsInt()

/**
 * 获取毫秒
 * @receiver [LocalDateTime]
 * @return [Int]
 */
@JsName("localDateTimeMillisecond")
fun LocalDateTime.millisecond(): Int =
    nanosecond / 1000000

/**
 * 获取年中的周数
 * @receiver [LocalDateTime]
 * @return [Int]
 */
@JsName("localDateTimeWeekOfYear")
fun LocalDateTime.weekOfYear(): Int =
    date.weekOfYear()

/**
 * 获取月中的周数
 * @receiver [LocalDateTime]
 * @param isFirstMondayAsFirstWeek [Boolean] true: 取月中的第一个周一开始算周数；false: 取月的1号作为第一周
 * @return [Int]
 */
@JsName("localDateTimeWeekOfMonth")
fun LocalDateTime.weekOfMonth(isFirstMondayAsFirstWeek: Boolean = true): Int =
    date.weekOfMonth(isFirstMondayAsFirstWeek)

/**
 * 获取当前日期的农历信息
 * @receiver [LocalDateTime]
 * @return [CalendarInfo]
 */
@JsName("localDateTimeLunarCalendar")
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
@JsName("copyLocalDateTime")
fun LocalDateTime.copy(
    year: Int = this.year,
    month: Int = this.monthNumber,
    day: Int = this.dayOfMonth,
    hour: Int = this.hour,
    minute: Int = this.minute,
    second: Int = this.second,
    nanosecond: Int = this.nanosecond
): LocalDateTime {
    val y = year.coerceIn(1970, 9999)
    val m = month.coerceIn(1, 12)
    return LocalDateTime(
        year = y,
        monthNumber = m,
        dayOfMonth = day.coerceIn(1, daysOfMonth(y, m)),
        hour = hour.coerceIn(0, 23),
        minute = minute.coerceIn(0, 59),
        second = second.coerceIn(0, 59),
        nanosecond = nanosecond.coerceIn(0, 999999999)
    )
}

/**
 * 判断日期时间是否是今天
 * @receiver [LocalDateTime]
 * @return [Boolean]
 */
@JsName("localDateTimeIsToday")
fun LocalDateTime.isToday(): Boolean =
    date.isToday()

/**
 * 判断日期时间是否是昨天
 * @receiver [LocalDateTime]
 * @return [Boolean]
 */
@JsName("localDateTimeIsYesterday")
fun LocalDateTime.isYesterday(): Boolean =
    date.isYesterday()

/**
 * 判断日期时间是否是明天
 * @receiver [LocalDateTime]
 * @return [Boolean]
 */
@JsName("localDateTimeIsTomorrow")
fun LocalDateTime.isTomorrow(): Boolean =
    date.isTomorrow()

/**
 * 判断日期是否为前天
 * @receiver [LocalDateTime]
 * @return [Boolean]
 */
@JsName("localDateTimeIsBeforeYesterday")
fun LocalDateTime.isBeforeYesterday(): Boolean =
    date.isBeforeYesterday()
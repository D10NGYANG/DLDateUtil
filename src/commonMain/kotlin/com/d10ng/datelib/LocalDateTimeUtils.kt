@file:JsExport

package com.d10ng.datelib

import kotlinx.datetime.*
import kotlin.js.JsExport
import kotlin.js.JsName
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

/**
 * 将LocalDateTime转换为UTC时区的Instant
 * @receiver [LocalDateTime]
 * @return [Instant]
 */
@OptIn(ExperimentalTime::class)
@JsName("localDateTimeToUTCInstant")
fun LocalDateTime.toUTCInstant(): Instant =
    toInstant(TimeZone.UTC)

/**
 * 将LocalDateTime转换为系统默认时区的Instant
 * @receiver [LocalDateTime]
 * @return [Instant]
 */
@OptIn(ExperimentalTime::class)
@JsName("localDateTimeToSystemInstant")
fun LocalDateTime.toSystemInstant(): Instant =
    toInstant(TimeZone.currentSystemDefault())

/**
 * 将LocalDateTime转换为指定时区的毫秒时间戳
 * @receiver [LocalDateTime]
 * @param timeZone [TimeZone] 时区
 * @return [Long]
 */
@OptIn(ExperimentalTime::class)
@JsName("localDateTimeToTimestamp")
fun LocalDateTime.toTimestamp(timeZone: TimeZone = TimeZone.currentSystemDefault()): Long =
    toInstant(timeZone).toTimestamp()

/**
 * 将LocalDateTime转换为UTC时区的时间戳，单位毫秒
 * @receiver [LocalDateTime]
 * @return [Long]
 */
@JsName("localDateTimeToUTCTimestamp")
fun LocalDateTime.toUTCTimestamp(): Long =
    toTimestamp(TimeZone.UTC)

/**
 * 将LocalDateTime转换为系统默认时区的时间戳，单位毫秒
 * @receiver [LocalDateTime]
 * @return [Long]
 */
@JsName("localDateTimeToSystemTimestamp")
fun LocalDateTime.toSystemTimestamp(): Long =
    toTimestamp(TimeZone.currentSystemDefault())

/**
 * 将LocalDateTime转换为指定时区的时间戳，单位秒
 * @receiver [LocalDateTime]
 * @param timeZone [TimeZone] 时区
 * @return [Int]
 */
@OptIn(ExperimentalTime::class)
@JsName("localDateTimeToTimeSeconds")
fun LocalDateTime.toTimeSeconds(timeZone: TimeZone = TimeZone.currentSystemDefault()): Int =
    toInstant(timeZone).toTimeSeconds()

/**
 * 将LocalDateTime转换为UTC时区的时间戳，单位秒
 * @receiver [LocalDateTime]
 * @return [Int]
 */
@JsName("localDateTimeToUTCTimeSeconds")
fun LocalDateTime.toUTCTimeSeconds(): Int =
    toTimeSeconds(TimeZone.UTC)

/**
 * 将LocalDateTime转换为系统默认时区的时间戳，单位秒
 * @receiver [LocalDateTime]
 * @return [Int]
 */
@JsName("localDateTimeToSystemTimeSeconds")
fun LocalDateTime.toSystemTimeSeconds(): Int =
    toTimeSeconds(TimeZone.currentSystemDefault())

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
 * 获取年中的周数，ISO标准
 * @receiver [LocalDateTime]
 * @return [Int]
 */
@JsName("localDateTimeIsoWeekOfYear")
fun LocalDateTime.isoWeekOfYear(): Int =
    date.isoWeekOfYear()

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
fun LocalDateTime.lunarCalendar(): CalendarInfo =
    date.lunarCalendar()

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
    month: Int = this.month.number,
    day: Int = this.day,
    hour: Int = this.hour,
    minute: Int = this.minute,
    second: Int = this.second,
    nanosecond: Int = this.nanosecond
): LocalDateTime {
    val y = year.coerceIn(1970, 9999)
    val m = month.coerceIn(1, 12)
    return LocalDateTime(
        year = y,
        month = m,
        day = day.coerceIn(1, daysOfMonth(y, m)),
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

/**
 * 获取ISO格式的日期时间字符串
 * @receiver [LocalDateTime]
 * @return [String]
 */
@JsName("localDateTimeFormatISO")
fun LocalDateTime.formatISO(): String =
    format(LocalDateTime.Formats.ISO)

/**
 * 获取指定格式的日期时间字符串
 * @receiver [LocalDateTime]
 * @param pattern [String] 格式
 * @return [String]
 */
@JsName("localDateTimeFormatPattern")
fun LocalDateTime.formatPattern(pattern: String = "yyyy-MM-dd HH:mm:ss"): String =
    format(buildDateTimeFormat(pattern))

/**
 * 将字符串转换为LocalDateTime
 * @receiver [String]
 * @param pattern [String] 格式
 * @return [LocalDateTime]
 */
@JsName("localDateTimeParsePattern")
fun String.toLocalDateTime(pattern: String = "yyyy-MM-dd HH:mm:ss"): LocalDateTime =
    buildDateTimeFormat(pattern).parse(this)

/**
 * 创建本地时间
 * @param milliseconds [Long] 毫秒
 * @param timeZone [TimeZone] 时区
 * @return [LocalDateTime]
 */
@Deprecated(
    message = "Use timestampToLocalDateTime instead",
    replaceWith = ReplaceWith("milliseconds.timestampToLocalDateTime(timeZone)")
)
fun createLocalDateTime(milliseconds: Long, timeZone: TimeZone): LocalDateTime =
    milliseconds.timestampToLocalDateTime(timeZone)

/**
 * 创建UTC时区的本地时间
 * @param milliseconds [Long] 毫秒
 * @return [LocalDateTime]
 */
@Deprecated(
    message = "Use timestampToUTCDateTime instead",
    replaceWith = ReplaceWith("milliseconds.timestampToUTCDateTime()")
)
fun createUTCDateTime(milliseconds: Long): LocalDateTime =
    milliseconds.timestampToUTCDateTime()

/**
 * 创建系统时区的本地时间
 * @param milliseconds [Long] 毫秒
 * @return [LocalDateTime]
 */
@Deprecated(
    message = "Use timestampToSystemDateTime instead",
    replaceWith = ReplaceWith("milliseconds.timestampToSystemDateTime()")
)
fun createSystemLocalDateTime(milliseconds: Long): LocalDateTime =
    milliseconds.timestampToSystemDateTime()

/**
 * 创建本地时间
 * @param seconds [Int] 秒
 * @param timeZone [TimeZone] 时区
 * @return [LocalDateTime]
 */
@JsName("createLocalDateTimeBySeconds")
@Deprecated(
    message = "Use timeSecondsToLocalDateTime instead",
    replaceWith = ReplaceWith("seconds.timeSecondsToLocalDateTime(timeZone)")
)
fun createLocalDateTime(seconds: Int, timeZone: TimeZone): LocalDateTime =
    seconds.timeSecondsToLocalDateTime(timeZone)

/**
 * 创建UTC时区的本地时间
 * @param seconds [Int] 秒
 * @return [LocalDateTime]
 */
@JsName("createUTCDateTimeBySeconds")
@Deprecated(
    message = "Use timeSecondsToUTCDateTime instead",
    replaceWith = ReplaceWith("seconds.timeSecondsToUTCDateTime()")
)
fun createUTCDateTime(seconds: Int): LocalDateTime =
    seconds.timeSecondsToUTCDateTime()

/**
 * 创建系统时区的本地时间
 * @param seconds [Int] 秒
 * @return [LocalDateTime]
 */
@JsName("createSystemLocalDateTimeBySeconds")
@Deprecated(
    message = "Use timeSecondsToSystemDateTime instead",
    replaceWith = ReplaceWith("seconds.timeSecondsToSystemDateTime()")
)
fun createSystemLocalDateTime(seconds: Int): LocalDateTime =
    seconds.timeSecondsToLocalDateTime(TimeZone.currentSystemDefault())

/**
 * 将LocalDateTime转换为UTC时区的时间戳，单位毫秒
 * @receiver [LocalDateTime]
 * @return [Long]
 */
@JsName("localDateTimeToUTCEpochMilliseconds")
@Deprecated(
    message = "Use toUTCTimestamp instead",
    replaceWith = ReplaceWith("toUTCTimestamp()")
)
fun LocalDateTime.toUTCEpochMilliseconds(): Long =
    toUTCTimestamp()

/**
 * 将LocalDateTime转换为系统默认时区的时间戳，单位毫秒
 * @receiver [LocalDateTime]
 * @return [Int]
 */
@JsName("localDateTimeToEpochMilliseconds")
@Deprecated(
    message = "Use toSystemTimestamp instead",
    replaceWith = ReplaceWith("toSystemTimestamp()")
)
fun LocalDateTime.toEpochMilliseconds(): Long =
    toSystemTimestamp()

/**
 * 将LocalDateTime转换为UTC时区的时间戳，单位秒
 * @receiver [LocalDateTime]
 * @return [Int]
 */
@JsName("localDateTimeToUTCEpochSeconds")
@Deprecated(
    message = "Use toUTCTimeSeconds instead",
    replaceWith = ReplaceWith("toUTCTimeSeconds()")
)
fun LocalDateTime.toUTCEpochSeconds(): Int =
    toUTCTimeSeconds()

/**
 * 将LocalDateTime转换为系统默认时区的时间戳，单位秒
 * @receiver [LocalDateTime]
 * @return [Int]
 */
@JsName("localDateTimeToEpochSeconds")
@Deprecated(
    message = "Use toSystemTimeSeconds instead",
    replaceWith = ReplaceWith("toSystemTimeSeconds()")
)
fun LocalDateTime.toEpochSeconds(): Int =
    toSystemTimeSeconds()
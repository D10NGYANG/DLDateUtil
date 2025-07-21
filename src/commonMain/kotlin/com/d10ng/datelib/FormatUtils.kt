package com.d10ng.datelib

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.datetime.format.DateTimeFormat
import kotlinx.datetime.format.FormatStringsInDatetimeFormats
import kotlinx.datetime.format.byUnicodePattern

/**
 * 构建日期时间格式
 * @param pattern 日期时间格式
 * @return [DateTimeFormat]<[LocalDateTime]>
 */
@OptIn(FormatStringsInDatetimeFormats::class)
fun buildDateTimeFormat(pattern: String): DateTimeFormat<LocalDateTime> =
    LocalDateTime.Format { byUnicodePattern(pattern) }

// 默认日期时间格式 yyyy-MM-dd HH:mm:ss
val LocalDateTime.Companion.DEFAULT_FORMAT: DateTimeFormat<LocalDateTime>
    get() = buildDateTimeFormat("yyyy-MM-dd HH:mm:ss")

// 紧凑日期时间格式 yyyyMMddHHmmss
val LocalDateTime.Companion.COMPACT_FORMAT: DateTimeFormat<LocalDateTime>
    get() = buildDateTimeFormat("yyyyMMddHHmmss")

/**
 * 将日期格式化成字符串
 * @param pattern 日期格式
 * @return [DateTimeFormat]<[LocalDate]>
 */
@OptIn(FormatStringsInDatetimeFormats::class)
fun buildDateFormat(pattern: String): DateTimeFormat<LocalDate> =
    LocalDate.Format { byUnicodePattern(pattern) }

// 默认日期格式 yyyy-MM-dd
val LocalDate.Companion.DEFAULT_FORMAT: DateTimeFormat<LocalDate>
    get() = buildDateFormat("yyyy-MM-dd")

// 紧凑日期格式 yyyyMMdd
val LocalDate.Companion.COMPACT_FORMAT: DateTimeFormat<LocalDate>
    get() = buildDateFormat("yyyyMMdd")

/**
 * 将时间格式化成字符串
 * @param pattern 时间格式
 * @return [DateTimeFormat]<[LocalTime]>
 */
@OptIn(FormatStringsInDatetimeFormats::class)
fun buildTimeFormat(pattern: String): DateTimeFormat<LocalTime> =
    LocalTime.Format { byUnicodePattern(pattern) }

// 默认时间格式 HH:mm:ss
val LocalTime.Companion.DEFAULT_FORMAT: DateTimeFormat<LocalTime>
    get() = buildTimeFormat("HH:mm:ss")

// 紧凑时间格式 HHmmss
val LocalTime.Companion.COMPACT_FORMAT: DateTimeFormat<LocalTime>
    get() = buildTimeFormat("HHmmss")
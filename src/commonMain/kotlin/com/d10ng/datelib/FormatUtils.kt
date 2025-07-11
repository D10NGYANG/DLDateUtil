package com.d10ng.datelib

import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.format.DateTimeFormat
import kotlinx.datetime.format.FormatStringsInDatetimeFormats
import kotlinx.datetime.format.byUnicodePattern

/**
 * 构建日期时间格式
 * @param pattern 日期时间格式
 * @return [DateTimeFormat]
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
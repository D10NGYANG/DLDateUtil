@file:JsExport
@file:OptIn(ExperimentalTime::class)

package com.d10ng.datelib

import kotlinx.datetime.*
import kotlin.js.JsExport
import kotlin.js.JsName
import kotlin.math.abs
import kotlin.math.floor
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

/**
 * 时间工具
 *
 * @author D10NG
 * @date on 2019-10-08 11:28
 */

// 默认格式
private const val DEFAULT_PATTERN = "yyyy-MM-dd HH:mm:ss"

/** 获取当前系统时间戳，单位毫秒 */
@Suppress("NON_EXPORTABLE_TYPE")
@Deprecated("请使用nowTimestamp()", ReplaceWith("nowTimestamp()"))
val curTime: Long
    get() = nowTimestamp()
/** 获取当前年份 */
@Deprecated("请使用nowSystemDateTime().year", ReplaceWith("nowSystemDateTime().year"))
val curYear: Int
    get() = nowSystemDateTime().year
/** 获取当前月份 */
@Deprecated("请使用nowSystemDateTime().month.number", ReplaceWith("nowSystemDateTime().month.number"))
val curMonth: Int
    get() = nowSystemDateTime().month.number
/** 获取当前日 */
@Deprecated("请使用nowSystemDateTime().day", ReplaceWith("nowSystemDateTime().day"))
val curDay: Int
    get() = nowSystemDateTime().day
/** 获取当前小时 */
@Deprecated("请使用nowSystemDateTime().hour", ReplaceWith("nowSystemDateTime().hour"))
val curHour: Int
    get() = nowSystemDateTime().hour
/** 获取当前分钟 */
@Deprecated("请使用nowSystemDateTime().minute", ReplaceWith("nowSystemDateTime().minute"))
val curMinute: Int
    get() = nowSystemDateTime().minute
/** 获取当前秒钟 */
@Deprecated("请使用nowSystemDateTime().second", ReplaceWith("nowSystemDateTime().second"))
val curSecond: Int
    get() = nowSystemDateTime().second
/** 获取当前毫秒 */
@Deprecated("请使用nowSystemDateTime().millisecond()", ReplaceWith("nowSystemDateTime().millisecond()"))
val curMillisecond: Int
    get() = nowSystemDateTime().millisecond()
/** 获取当前年中的周数 */
@Deprecated("请使用nowSystemDateTime().weekOfYear()", ReplaceWith("nowSystemDateTime().weekOfYear()"))
val curWeekOfYear: Int
    get() = nowSystemDateTime().weekOfYear()
/** 获取当前月中的周数 */
@Deprecated("请使用nowSystemDateTime().weekOfMonth()", ReplaceWith("nowSystemDateTime().weekOfMonth()"))
val curWeekOfMonth: Int
    get() = nowSystemDateTime().weekOfMonth()
/** 获取当前年中的天数 */
@Deprecated("请使用nowSystemDateTime().dayOfYear", ReplaceWith("nowSystemDateTime().dayOfYear"))
val curDayOfYear: Int
    get() = nowSystemDateTime().dayOfYear
/** 获取当前星期几 */
@Deprecated("请使用nowSystemDateTime().dayOfWeek.isoDayNumber", ReplaceWith("nowSystemDateTime().dayOfWeek.isoDayNumber"))
val curDayOfWeek: Int
    get() = nowSystemDateTime().dayOfWeek.isoDayNumber
/** 今天的农历信息 */
@Deprecated("请使用nowSystemDateTime().lunarCalendar()", ReplaceWith("nowSystemDateTime().lunarCalendar()"))
val curDayLunar: CalendarInfo
    get() = nowSystemDateTime().lunarCalendar()

/**
 * 获取时间戳中的年份
 * @receiver [Long] 毫秒时间戳
 * @return [Int] 年份
 */
@Deprecated("请使用timestampToSystemDateTime().year", ReplaceWith("timestampToSystemDateTime().year"))
fun Long.getDateYear(): Int =
    timestampToSystemDateTime().year

/**
 * 修改时间戳中的年份
 * > 如果输入时间戳的年份为闰年，月日刚好是2月29，修改年份不是闰年则回退一天，举例：输入时间=2020-02-29，修改年份为2021，输出时间=2021-02-28
 * @receiver [Long] 毫秒时间戳
 * @param value [Int] 年份，0～9999
 * @return [Long] 毫秒时间戳
 */
@Suppress("NON_EXPORTABLE_TYPE")
@Deprecated("请使用timestampToSystemDateTime().copy(year = value).toSystemTimestamp()", ReplaceWith("timestampToSystemDateTime().copy(year = value).toSystemTimestamp()"))
fun Long.setDateYear(value: Int): Long =
    timestampToSystemDateTime().copy(year = value).toSystemTimestamp()

/**
 * 获取时间戳中的月份
 * @receiver [Long] 毫秒时间戳
 * @return [Int] 月份
 */
@Deprecated("请使用timestampToSystemDateTime().month.number", ReplaceWith("timestampToSystemDateTime().month.number"))
fun Long.getDateMonth(): Int =
    timestampToSystemDateTime().month.number

/**
 * 修改时间戳中的月份
 * > 如果输入时间戳的月份的总天数与修改月份的总天数不一致，且刚好时间戳的日期在不一致的位置，则退到修改月份的最后一天，举例：输入时间=2020-03-31，修改月份为4月，输出时间=2022-04-30
 * @receiver [Long] 毫秒时间戳
 * @param value [Int] 月份 1~12
 * @return [Long] 毫秒时间戳
 */
@Suppress("NON_EXPORTABLE_TYPE")
@Deprecated("请使用timestampToSystemDateTime().copy(month = value).toSystemTimestamp()", ReplaceWith("timestampToSystemDateTime().copy(month = value).toSystemTimestamp()"))
fun Long.setDateMonth(value: Int): Long =
    timestampToSystemDateTime().copy(month = value).toSystemTimestamp()

/**
 * 获取时间戳中的日
 * @receiver [Long] 毫秒时间戳
 * @return [Int] 日
 */
@Deprecated("请使用timestampToSystemDateTime().day", ReplaceWith("timestampToSystemDateTime().day"))
fun Long.getDateDay(): Int =
    timestampToSystemDateTime().day

/**
 * 修改时间戳中的日
 * @receiver [Long] 毫秒时间戳
 * @param value [Int] 日 1～月最大天数，输入值不在允许范围时，将矫正到允许范围区间内。如：0 -> 1，32 -> 31
 * @return [Long] 毫秒时间戳
 */
@Suppress("NON_EXPORTABLE_TYPE")
@Deprecated("请使用timestampToSystemDateTime().copy(day = value).toSystemTimestamp()", ReplaceWith("timestampToSystemDateTime().copy(day = value).toSystemTimestamp()"))
fun Long.setDateDay(value: Int): Long =
    timestampToSystemDateTime().copy(day = value).toSystemTimestamp()

/**
 * 获取时间戳中的小时 24小时
 * @receiver [Long] 毫秒时间戳
 * @return [Int] 小时
 */
@Deprecated("请使用timestampToSystemDateTime().hour", ReplaceWith("timestampToSystemDateTime().hour"))
fun Long.getDateHour(): Int =
    timestampToSystemDateTime().hour

/**
 * 修改时间戳中的小时 24小时
 * @receiver [Long] 毫秒时间戳
 * @param value [Int] 小时 24小时，0～23，输入值不在允许范围时，将矫正到允许范围区间内
 * @return [Long] 毫秒时间戳
 */
@Suppress("NON_EXPORTABLE_TYPE")
@Deprecated("请使用timestampToSystemDateTime().copy(hour = value).toSystemTimestamp()", ReplaceWith("timestampToSystemDateTime().copy(hour = value).toSystemTimestamp()"))
fun Long.setDateHour(value: Int): Long =
    timestampToSystemDateTime().copy(hour = value).toSystemTimestamp()

/**
 * 获取时间戳中的分钟
 * @receiver [Long] 毫秒时间戳
 * @return [Int] 分钟
 */
@Deprecated("请使用timestampToSystemDateTime().minute", ReplaceWith("timestampToSystemDateTime().minute"))
fun Long.getDateMinute(): Int =
    timestampToSystemDateTime().minute

/**
 * 修改时间戳中的分钟
 * @receiver [Long] 毫秒时间戳
 * @param value [Int] 分钟，0～59，输入值不在允许范围时，将矫正到允许范围区间内
 * @return [Long] 毫秒时间戳
 */
@Suppress("NON_EXPORTABLE_TYPE")
@Deprecated("请使用timestampToSystemDateTime().copy(minute = value).toSystemTimestamp()", ReplaceWith("timestampToSystemDateTime().copy(minute = value).toSystemTimestamp()"))
fun Long.setDateMinute(value: Int): Long =
    timestampToSystemDateTime().copy(minute = value).toSystemTimestamp()

/**
 * 获取时间戳中的秒钟
 * @receiver [Long] 毫秒时间戳
 * @return [Int] 秒钟
 */
@Deprecated("请使用timestampToSystemDateTime().second", ReplaceWith("timestampToSystemDateTime().second"))
fun Long.getDateSecond(): Int =
    timestampToSystemDateTime().second

/**
 * 修改时间戳的中秒钟
 * @receiver [Long] 毫秒时间戳
 * @param value [Int] 秒钟，0～59，输入值不在允许范围时，将矫正到允许范围区间内
 * @return [Long] 毫秒时间戳
 */
@Suppress("NON_EXPORTABLE_TYPE")
@Deprecated("请使用timestampToSystemDateTime().copy(second = value).toSystemTimestamp()", ReplaceWith("timestampToSystemDateTime().copy(second = value).toSystemTimestamp()"))
fun Long.setDateSecond(value: Int): Long =
    timestampToSystemDateTime().copy(second = value).toSystemTimestamp()

/**
 * 获取时间戳中的毫秒
 * @receiver [Long] 毫秒时间戳
 * @return [Int] 毫秒
 */
@Deprecated("请使用timestampToSystemDateTime().millisecond()", ReplaceWith("timestampToSystemDateTime().millisecond()"))
fun Long.getDateMillisecond(): Int =
    timestampToSystemDateTime().millisecond()

/**
 * 修改时间戳中的毫秒
 * @receiver [Long] 毫秒时间戳
 * @param value [Int] 毫秒，0～999，输入值不在允许范围时，将矫正到允许范围区间内
 * @return [Long] 毫秒时间戳
 */
@Suppress("NON_EXPORTABLE_TYPE")
@Deprecated("请使用timestampToSystemDateTime().copy(nanosecond = value * 1000000).toSystemTimestamp()", ReplaceWith("timestampToSystemDateTime().copy(nanosecond = value * 1000000).toSystemTimestamp()"))
fun Long.setDateMillisecond(value: Int): Long =
    timestampToSystemDateTime().copy(nanosecond = value * 1000000).toSystemTimestamp()

/**
 * 获取时间戳中的年中的周数
 * @receiver [Long] 毫秒时间戳
 * @return [Int] 年中的周数
 */
@Deprecated("请使用timestampToSystemDateTime().weekOfYear()", ReplaceWith("timestampToSystemDateTime().weekOfYear()"))
fun Long.getDateWeekOfYear(): Int =
    timestampToSystemDateTime().weekOfYear()

/**
 * 获取时间戳中的月中的周数
 * @receiver [Long] 毫秒时间戳
 * @param isFirstMondayAsFirstWeek [Boolean] true: 取月中的第一个周一开始算周数，false: 取月的1号作为第一周
 * @return [Int] 月中的周数
 */
@Deprecated("请使用timestampToSystemDateTime().weekOfMonth(isFirstMondayAsFirstWeek)", ReplaceWith("timestampToSystemDateTime().weekOfMonth(isFirstMondayAsFirstWeek)"))
fun Long.getDateWeekOfMonth(isFirstMondayAsFirstWeek: Boolean = true): Int =
    timestampToSystemDateTime().weekOfMonth(isFirstMondayAsFirstWeek)

/**
 * 获取时间戳中的年中的天数
 * @receiver [Long] 毫秒时间戳
 * @return [Int] 日
 */
@Deprecated("请使用timestampToSystemDateTime().dayOfYear", ReplaceWith("timestampToSystemDateTime().dayOfYear"))
fun Long.getDateDayOfYear(): Int =
    timestampToSystemDateTime().dayOfYear

/**
 * 获取时间戳中的星期几
 * @receiver [Long] 毫秒时间戳
 * @return [Int] 星期几，1～7
 */
@Deprecated("请使用timestampToSystemDateTime().dayOfWeek.isoDayNumber", ReplaceWith("timestampToSystemDateTime().dayOfWeek.isoDayNumber"))
fun Long.getDateDayOfWeek(): Int =
    timestampToSystemDateTime().dayOfWeek.isoDayNumber

/**
 * 获取时间戳中的星期几的文本
 * @receiver [Long] 毫秒时间戳
 * @param type [WeekTextType] 星期几文本类型
 * @return [String] 文本，如"星期一"，"周一"，"一"，"MONDAY"，"MON"
 */
@Deprecated("请使用timestampToSystemDateTime().dayOfWeek.name(type)", ReplaceWith("timestampToSystemDateTime().dayOfWeek.name(type)"))
fun Long.getDateDayOfWeekText(type: WeekTextType): String =
    timestampToSystemDateTime().dayOfWeek.name(type)

/**
 * 获取时间戳中的月份的文本
 * @receiver [Long] 毫秒时间戳
 * @param type [MonthTextType] 月份文本类型
 * @return [String] 文本，如"一月"，"JANUARY"，"JAN"
 */
@Deprecated("请使用timestampToSystemDateTime().month.name(type)", ReplaceWith("timestampToSystemDateTime().month.name(type)"))
fun Long.getDateMonthText(type: MonthTextType): String =
    timestampToSystemDateTime().month.name(type)

/**
 * 获取时间戳的农历信息
 * @receiver [Long] 毫秒时间戳
 * @return [CalendarInfo] 农历信息
 */
@Deprecated("请使用timestampToSystemDateTime().lunarCalendar()", ReplaceWith("timestampToSystemDateTime().lunarCalendar()"))
fun Long.getDateDayLunar(): CalendarInfo? =
    timestampToSystemDateTime().lunarCalendar()

/**
 * 时间戳转换成字符窜
 * @receiver [Long] 毫秒时间戳
 * @param pattern [String] 时间样式 yyyy-MM-dd HH:mm:ss
 * * y\Y：表示年份
 * * M：表示月份
 * * d：表示日期
 * * H：表示小时，24小时制
 * * h：表示小时，12小时制
 * * m：表示分钟
 * * s：表示秒
 * * S：表示毫秒
 * * w：年中的周数
 * * W：月中的周数
 * * D：年中的天数
 * * E：星期几，如1
 * * a: AM/PM（上午、下午）
 * @return [String] 时间字符串，如2022-09-14 16:16:55
 */
fun Long.toDateStr(pattern: String = DEFAULT_PATTERN): String {
    val datetime = timestampToSystemDateTime()
    var string = pattern
    // 将 y、Y 转换成年份
    var reg = "[yY]+".toRegex().findAll(string).toList()
    for (item in reg) {
        string = string.replaceRange(item.range, datetime.year.toString().padStartForce(item.value.length, '0'))
    }
    // 将 M 转换成月份
    reg = "M+".toRegex().findAll(string).toList()
    for (item in reg) {
        string = string.replaceRange(item.range, datetime.month.number.toString().padStartForce(item.value.length, '0'))
    }
    // 将 d 转换成日期
    reg = "d+".toRegex().findAll(string).toList()
    for (item in reg) {
        string = string.replaceRange(item.range, datetime.day.toString().padStartForce(item.value.length, '0'))
    }
    // 将 H 转换成小时 24小时制
    reg = "H+".toRegex().findAll(string).toList()
    for (item in reg) {
        string = string.replaceRange(item.range, datetime.hour.toString().padStartForce(item.value.length, '0'))
    }
    // 将 h 转换成小时 12小时制
    reg = "h+".toRegex().findAll(string).toList()
    for (item in reg) {
        string = string.replaceRange(item.range, (if (datetime.hour > 12) datetime.hour - 12 else datetime.hour).toString().padStartForce(item.value.length, '0'))
    }
    // 将 m 转换成分钟
    reg = "m+".toRegex().findAll(string).toList()
    for (item in reg) {
        string = string.replaceRange(item.range, datetime.minute.toString().padStartForce(item.value.length, '0'))
    }
    // 将 s 转换成秒钟
    reg = "s+".toRegex().findAll(string).toList()
    for (item in reg) {
        string = string.replaceRange(item.range, datetime.second.toString().padStartForce(item.value.length, '0'))
    }
    // 将 S 转换成毫秒
    reg = "S+".toRegex().findAll(string).toList()
    for (item in reg) {
        string = string.replaceRange(item.range,
            datetime.millisecond().toString().padStart(3, '0').substring(0, item.value.length)
        )
    }
    // 将 w 转换成年中的周数
    reg = "w+".toRegex().findAll(string).toList()
    for (item in reg) {
        string = string.replaceRange(item.range, datetime.weekOfYear().toString().padStartForce(item.value.length, '0'))
    }
    // 将 W 转换成月中的周数
    reg = "W+".toRegex().findAll(string).toList()
    for (item in reg) {
        string = string.replaceRange(item.range, datetime.weekOfMonth().toString().padStartForce(item.value.length, '0'))
    }
    // 将 D 转换成年中的天数
    reg = "D+".toRegex().findAll(string).toList()
    for (item in reg) {
        string = string.replaceRange(item.range, datetime.dayOfYear.toString().padStartForce(item.value.length, '0'))
    }
    // 将 E 转换成星期几
    reg = "E+".toRegex().findAll(string).toList()
    for (item in reg) {
        string = string.replaceRange(item.range, datetime.dayOfWeek.isoDayNumber.toString().padStartForce(item.value.length, '0'))
    }
    // 将 a 转换成 AM/PM
    reg = "a+".toRegex().findAll(string).toList()
    for (item in reg) {
        string = string.replaceRange(item.range, if (datetime.hour > 12) "PM" else "AM")
    }
    return string
}

/**
 * 将字符串转为时间戳
 * @receiver [String] 时间字符串
 * @param pattern [String] 时间样式 yyyy-MM-dd HH:mm:ss
 * * y\Y：表示年份
 * * M：表示月份
 * * d：表示日期
 * * H：表示小时，24小时制
 * * h：表示小时，12小时制
 * * m：表示分钟
 * * s：表示秒
 * * S：表示毫秒
 * * w：年中的周数，不解析，传递进来不处理
 * * W：月中的周数，不解析，传递进来不处理
 * * D：年中的天数，不解析，传递进来不处理
 * * E：星期几，不解析，传递进来不处理
 * * a: AM/PM（上午、下午）
 * @return [Long] 毫秒时间戳
 */
@Suppress("NON_EXPORTABLE_TYPE")
fun String.toDateLong(pattern: String = DEFAULT_PATTERN): Long {
    val cur = nowSystemDateTime()
    var string = pattern
    var reg = "a+".toRegex().findAll(string).toList()
    for (item in reg) {
        if (item.value.length == 2) continue
        string = string.replace(item.value, "aa")
    }
    // 将 y、Y 转换成年份
    reg = "[yY]+".toRegex().findAll(string).toList()
    val year = if (reg.isNotEmpty()) this.substring(reg[0].range).toIntOrNull()?: cur.year else cur.year
    // 将 M 转换成月份
    reg = "M+".toRegex().findAll(string).toList()
    val month = if (reg.isNotEmpty()) this.substring(reg[0].range).toIntOrNull()?: cur.month.number else cur.month.number
    // 将 d 转换成日期
    reg = "d+".toRegex().findAll(string).toList()
    val day = if (reg.isNotEmpty()) this.substring(reg[0].range).toIntOrNull()?: cur.day else cur.day
    // 将 H 转换成小时 24小时制
    reg = "H+".toRegex().findAll(string).toList()
    var isH = false
    var hour = if (reg.isNotEmpty()) {
        isH = true
        this.substring(reg[0].range).toIntOrNull()?: cur.hour
    } else {
        // 将 h 转换成小时 12小时制
        val regh = "h+".toRegex().findAll(string).toList()
        if (regh.isNotEmpty()) this.substring(reg[0].range).toIntOrNull()?: cur.hour else 0
    }
    reg = "a+".toRegex().findAll(string).toList()
    val isAM = reg.isEmpty() || this.substring(reg[0].range).contains("AM")
    if (!isAM && !isH) hour = (hour + 12).coerceAtMost(23)
    // 将 m 转换成分钟
    reg = "m+".toRegex().findAll(string).toList()
    val minute = if (reg.isNotEmpty()) this.substring(reg[0].range).toIntOrNull()?: cur.minute else 0
    // 将 s 转换成秒钟
    reg = "s+".toRegex().findAll(string).toList()
    val second  = if (reg.isNotEmpty()) this.substring(reg[0].range).toIntOrNull()?: cur.second else 0
    // 将 S 转换成毫秒
    reg = "S+".toRegex().findAll(string).toList()
    val millisecond  = if (reg.isNotEmpty()) this.substring(reg[0].range).padEnd(3, '0').toIntOrNull()?: cur.millisecond() else 0
    return timestampSystem(year, month, day, hour, minute, second, millisecond)
}

/**
 * 判断字符串是否为时间字符串
 * @receiver [String] 字符串
 * @param pattern [String] 时间格式 yyyy-MM-dd HH:mm:ss
 * * y\Y：表示年份
 * * M：表示月份
 * * d：表示日期
 * * H：表示小时，24小时制
 * * h：表示小时，12小时制
 * * m：表示分钟
 * * s：表示秒
 * * S：表示毫秒
 * * w：年中的周数，不解析，传递进来不处理
 * * W：月中的周数，不解析，传递进来不处理
 * * D：年中的天数，不解析，传递进来不处理
 * * E：星期几，不解析，传递进来不处理
 * * a: AM/PM（上午、下午）
 * @return [Boolean] 是否为时间格式字符串
 */
fun String.isDatetimeString(pattern: String = DEFAULT_PATTERN): Boolean {
    var string = pattern.replace("\\s".toRegex(), "")
    // 将 d 转换成(?:0[1-9]|[1-2]\d|30|31)
    var reg = "d+".toRegex().findAll(string).toList()
    for (item in reg) {
        string = string.replaceRange(item.range, "(?:0[1-9]|[1-2]\\d|30|31)")
    }
    // 将 y、Y 转换成\d{length}
    reg = "[yY]+".toRegex().findAll(string).toList()
    for (item in reg) {
        string = string.replaceRange(item.range, "\\d{${item.value.length}}")
    }
    // 将 M 转换成(?:1[0-2]|0[1-9])
    reg = "M+".toRegex().findAll(string).toList()
    for (item in reg) {
        string = string.replaceRange(item.range, "(?:1[0-2]|0[1-9])")
    }
    // 将 H 转换成(?:[01]\d|2[0-3])
    reg = "H+".toRegex().findAll(string).toList()
    for (item in reg) {
        string = string.replaceRange(item.range, "(?:[01]\\d|2[0-3])")
    }
    // 将 h 转换成(?:1[0-2]|0[1-9])
    reg = "h+".toRegex().findAll(string).toList()
    for (item in reg) {
        string = string.replaceRange(item.range, "(?:1[0-2]|0[1-9])")
    }
    // 将 m 转换成[0-5]\d
    reg = "m+".toRegex().findAll(string).toList()
    for (item in reg) {
        string = string.replaceRange(item.range, "[0-5]\\d")
    }
    // 将 s 转换成[0-5]\d
    reg = "s+".toRegex().findAll(string).toList()
    for (item in reg) {
        string = string.replaceRange(item.range, "[0-5]\\d")
    }
    // 将 S 转换成\d{3}
    reg = "S+".toRegex().findAll(string).toList()
    for (item in reg) {
        string = string.replaceRange(item.range, "\\d{3}")
    }
    // 将 a 转换成(?:AM|PM)
    reg = "a+".toRegex().findAll(string).toList()
    for (item in reg) {
        string = string.replaceRange(item.range, "(?:AM|PM)")
    }
    string = "^(${string})$"
    val thisStr = this.replace("\\s".toRegex(), "")
    return string.toRegex().matches(thisStr)
}

/**
 * 根据年月日获取时间戳
 * @param year [Int] 年
 * @param month [Int] 月
 * @param day [Int] 日
 * @return [Long] 毫秒时间戳
 */
@Suppress("NON_EXPORTABLE_TYPE")
@JsName("getDateByYMD")
@Deprecated("请使用timestampSystem(year, month, day)", ReplaceWith("timestampSystem(year, month, day)"))
fun getDateBy(
    year: Int,
    month: Int,
    day: Int
): Long = timestampSystem(year, month, day)

/**
 * 根据年月日时分秒获取时间戳
 * @param year [Int] 年
 * @param month [Int] 月
 * @param day [Int] 日
 * @param hour [Int] 时
 * @param minute [Int] 分
 * @param second [Int] 秒
 * @return [Long] 毫秒时间戳
 */
@Suppress("NON_EXPORTABLE_TYPE")
@JsName("getDateByYMDHms")
@Deprecated("请使用timestampSystem(year, month, day, hour, minute, second)", ReplaceWith("timestampSystem(year, month, day, hour, minute, second)"))
fun getDateBy(
    year: Int,
    month: Int,
    day: Int,
    hour: Int,
    minute: Int,
    second: Int
): Long = timestampSystem(year, month, day, hour, minute, second)

/**
 * 根据参数获取时间戳
 * @param year [Int] 年
 * @param month [Int] 月
 * @param day [Int] 日
 * @param hour [Int] 时
 * @param minute [Int] 分
 * @param second [Int] 秒
 * @param millisecond [Int] 毫秒
 * @return [Long] 毫秒时间戳
 */
@Suppress("NON_EXPORTABLE_TYPE")
@Deprecated("请使用timestampSystem(year, month, day, hour, minute, second, millisecond)", ReplaceWith("timestampSystem(year, month, day, hour, minute, second, millisecond)"))
fun getDateBy(
    year: Int = nowSystemDateTime().year,
    month: Int = nowSystemDateTime().month.number,
    day: Int = nowSystemDateTime().day,
    hour: Int = nowSystemDateTime().hour,
    minute: Int = nowSystemDateTime().minute,
    second: Int = nowSystemDateTime().second,
    millisecond: Int = nowSystemDateTime().millisecond()
): Long = timestampSystem(year, month, day, hour, minute, second, millisecond)

/**
 * 获取距离当前第n天的时间戳
 * @param offset [Int] n，正数时表示向后增加天数，负数时表示往前减天数，例如n=1，表示获取明天的时间戳，n=-1表示获取昨天的时间戳
 * @return [Long] 毫秒时间戳
 */
@Suppress("NON_EXPORTABLE_TYPE")
fun getNextDate(offset: Int = 1): Long {
    return nowTimestamp().getNextDay(offset)
}

/**
 * 获取某个日子为标点的附近的日子时间戳
 * @receiver [Long] 标点毫秒时间戳
 * @param offset [Int] n，正数时表示向后增加天数，负数时表示往前减天数，例如n=1，表示获取明天的时间戳，n=-1表示获取昨天的时间戳
 * @return [Long] 毫秒时间戳
 */
@Suppress("NON_EXPORTABLE_TYPE")
fun Long.getNextDay(offset: Int = 1): Long {
    val instant = Instant.fromEpochMilliseconds(this)
    val offsetInstant = instant.plus(offset, DateTimeUnit.DAY, TimeZone.currentSystemDefault())
    return offsetInstant.toEpochMilliseconds()
}

/**
 * 获取指定月份的天数
 * @param year [Int] 年
 * @param month [Int] 月
 * @return [Int] 天数
 */
@Deprecated("请使用daysOfMonth(year, month)", ReplaceWith("daysOfMonth(year, month)"))
fun getDaysOfMonth(year: Int = nowSystemDateTime().year, month: Int = nowSystemDateTime().month.number): Int {
    val ldt = LocalDateTime(year, month, 1, 0, 0, 0)
    val start = ldt.toInstant(TimeZone.currentSystemDefault())
    val end = start.plus(1, DateTimeUnit.MONTH, TimeZone.currentSystemDefault())
    return start.until(end, DateTimeUnit.DAY, TimeZone.currentSystemDefault()).toInt()
}

/**
 * 时间戳是否为今天的
 * @receiver [Long] 毫秒时间戳
 * @return [Boolean]
 */
fun Long.isToday(): Boolean = isNextDay(0)

/**
 * 时间戳是否为昨天的
 * @receiver [Long] 毫秒时间戳
 * @return [Boolean]
 */
fun Long.isYesterday(): Boolean = isNextDay(-1)

/**
 * 时间戳是否为今天的某n天偏移
 * @receiver [Long] 毫秒时间戳
 * @param offset [Int] n，正数时表示向后增加天数，负数时表示往前减天数，例如n=1，表示对比明天的时间戳，n=-1表示对比昨天的时间戳
 * @return [Boolean]
 */
fun Long.isNextDay(offset: Int = 1): Boolean {
    val today = nowSystemDate().atZeroTime().toSystemInstant()
    val end = timestampToSystemDateTime().date.atZeroTime().toSystemInstant()
    val temp = today.plus(offset, DateTimeUnit.DAY, TimeZone.currentSystemDefault())
    return temp.daysUntil(end, TimeZone.currentSystemDefault()) == 0
}

/**
 * 本地时间转化为UTC时间
 * @receiver [Long] 毫秒时间戳
 * @return [Long] 毫秒时间戳
 */
@Suppress("NON_EXPORTABLE_TYPE")
fun Long.toUTCDate(): Long {
    return timestampToUTCDateTime().toInstant(TimeZone.currentSystemDefault()).toEpochMilliseconds()
}

/**
 * UTC时间转化为本地时间
 * @receiver [Long] 毫秒时间戳
 * @return [Long] 毫秒时间戳
 */
@Suppress("NON_EXPORTABLE_TYPE")
fun Long.toLocalDate(): Long {
    return timestampToSystemDateTime().toInstant(TimeZone.UTC).toEpochMilliseconds()
}

/**
 * UTC时间转化为指定timeZone时间
 * @receiver [Long] 毫秒时间戳
 * @param timeZoneInt [Int] 时区值，如东8区时赋值为8
 * @return [Long] 毫秒时间戳
 */
@Suppress("NON_EXPORTABLE_TYPE")
fun Long.toCustomDate(timeZoneInt: Int): Long {
    val instant = Instant.fromEpochMilliseconds(this)
    val end = instant.plus(timeZoneInt, DateTimeUnit.HOUR)
    return end.toEpochMilliseconds()
}

/**
 * 将两个毫秒时间戳之间的间隔转换成时间文本
 * @param start [Long] 开始时间戳，毫秒
 * @param end [Long] 结束时间戳，毫秒
 * @param hasDay [Boolean] 是否将天数独立出来，如 25:20:38 -> 1天 01:20:38，如果不够一天，那就只显示时分秒，如03:18:25
 * @return [String] 时间文本，eg: 13:20:38、1天 01:20:38
 */
@Suppress("NON_EXPORTABLE_TYPE")
fun dateDiffToString(start: Long, end: Long, hasDay: Boolean = false): String {
    val diffTimeSecond = floor(abs(end - start) / 1000.0)
    val day = (diffTimeSecond / 86400).toInt()
    var hour = (diffTimeSecond / 3600).toInt()
    if (hasDay && day > 0) {
        hour -= (day * 24)
    }
    val hourStr = "$hour".padStart(2, '0')
    val minute = ((diffTimeSecond % 3600) / 60).toInt()
    val minuteStr = "$minute".padStart(2, '0')
    val second = ((diffTimeSecond % 3600) % 60).toInt()
    val secondStr = "$second".padStart(2, '0')
    return if (hasDay && day > 0) {
        "${day}天 ${hourStr}:${minuteStr}:${secondStr}"
    } else {
        "${hourStr}:${minuteStr}:${secondStr}"
    }
}
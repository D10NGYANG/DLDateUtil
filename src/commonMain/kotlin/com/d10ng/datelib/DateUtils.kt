package com.d10ng.datelib

import kotlinx.datetime.*

/**
 * 时间工具
 *
 * @author D10NG
 * @date on 2019-10-08 11:28
 */

/** 获取当前系统时间戳，单位毫秒 */
val curTime: Long
    get() = Clock.System.now().toEpochMilliseconds()
/** 获取当前年份 */
val curYear: Int
    get() = curTime.getDateYear()
/** 获取当前月份 */
val curMonth: Int
    get() = curTime.getDateMonth()
/** 获取当前日 */
val curDay: Int
    get() = curTime.getDateDay()
/** 获取当前小时 */
val curHour: Int
    get() = curTime.getDateHour()
/** 获取当前分钟 */
val curMinute: Int
    get() = curTime.getDateMinute()
/** 获取当前秒钟 */
val curSecond: Int
    get() = curTime.getDateSecond()
/** 获取当前毫秒 */
val curMillisecond: Int
    get() = curTime.getDateMillisecond()
/** 获取当前年中的周数 */
val curWeekOfYear: Int
    get() = curTime.getDateWeekOfYear()
/** 获取当前月中的周数 */
val curWeekOfMonth: Int
    get() = curTime.getDateWeekOfMonth()
/** 获取当前年中的天数 */
val curDayOfYear: Int
    get() = curTime.getDateDayOfYear()
/** 获取当前星期几 */
val curDayOfWeek: Int
    get() = curTime.getDateDayOfWeek()
/** 今天的农历信息 */
val curDayLunar: CalendarInfo
    get() = curTime.getDateDayLunar()!!

/**
 * 将毫秒时间戳转换成 LocalDateTime
 * @receiver [Long] 毫秒时间戳
 * @param [timeZone] TimeZone 时区
 * @return LocalDateTime
 */
private fun Long.toLocalDateTime(timeZone: TimeZone = TimeZone.currentSystemDefault()): LocalDateTime {
    val instant = Instant.fromEpochMilliseconds(this)
    return instant.toLocalDateTime(timeZone)
}
/**
 * 获取时间戳中的年份
 * @receiver [Long]
 * @return [Int] 年份
 */
fun Long.getDateYear(): Int = this.toLocalDateTime().year

/**
 * 修改时间戳中的年份
 * @receiver [Long]
 * @param value [Int] 年份
 * @return [Long]
 */
fun Long.setDateYear(value: Int): Long =
    getDateBy(value.coerceAtLeast(0).coerceAtMost(9999), getDateMonth(), getDateDay().coerceAtMost(getDaysOfMonth(value, getDateMonth())), getDateHour(), getDateMinute(), getDateSecond(), getDateMillisecond())

/**
 * 获取时间戳中的月份
 * @receiver [Long]
 * @return [Int] 月份
 */
fun Long.getDateMonth(): Int = this.toLocalDateTime().month.number

/**
 * 修改时间戳中的月份
 * @receiver [Long]
 * @param value [Int] 月份
 * @return [Long]
 */
fun Long.setDateMonth(value: Int): Long =
    getDateBy(getDateYear(), value.coerceAtLeast(1).coerceAtMost(12), getDateDay().coerceAtMost(getDaysOfMonth(getDateYear(), value)), getDateHour(), getDateMinute(), getDateSecond(), getDateMillisecond())

/**
 * 获取时间戳中的日
 * @receiver [Long]
 * @return [Int] 日
 */
fun Long.getDateDay(): Int = this.toLocalDateTime().dayOfMonth

/**
 * 修改时间戳中的日
 * @receiver [Long]
 * @param value [Int] 日
 * @return [Long]
 */
fun Long.setDateDay(value: Int): Long =
    getDateBy(getDateYear(), getDateMonth(), value.coerceAtLeast(1).coerceAtMost(getDaysOfMonth(getDateYear(), getDateMonth())), getDateHour(), getDateMinute(), getDateSecond(), getDateMillisecond())

/**
 * 获取时间戳中的小时 24小时
 * @receiver [Long]
 * @return [Int] 小时
 */
fun Long.getDateHour(): Int = this.toLocalDateTime().hour

/**
 * 修改时间戳中的小时 24小时
 * @receiver [Long]
 * @param value [Int] 小时 24小时
 * @return [Long]
 */
fun Long.setDateHour(value: Int): Long =
    getDateBy(getDateYear(), getDateMonth(), getDateDay(), value.coerceAtLeast(0).coerceAtMost(23), getDateMinute(), getDateSecond(), getDateMillisecond())

/**
 * 获取时间戳中的分钟
 * @receiver [Long]
 * @return [Int] 分钟
 */
fun Long.getDateMinute(): Int = this.toLocalDateTime().minute

/**
 * 修改时间戳中的分钟
 * @receiver [Long]
 * @param value [Int] 分钟
 * @return [Long]
 */
fun Long.setDateMinute(value: Int): Long =
    getDateBy(getDateYear(), getDateMonth(), getDateDay(), getDateHour(), value.coerceAtLeast(0).coerceAtMost(59), getDateSecond(), getDateMillisecond())

/**
 * 获取时间戳中的秒钟
 * @receiver [Long]
 * @return [Int] 秒钟
 */
fun Long.getDateSecond(): Int = this.toLocalDateTime().second

/**
 * 修改时间戳的中秒钟
 * @receiver [Long]
 * @param value [Int] 秒钟
 * @return [Long]
 */
fun Long.setDateSecond(value: Int): Long =
    getDateBy(getDateYear(), getDateMonth(), getDateDay(), getDateHour(), getDateMinute(), value.coerceAtLeast(0).coerceAtMost(59), getDateMillisecond())

/**
 * 获取时间戳中的毫秒
 * @receiver [Long]
 * @return [Int] 毫秒
 */
fun Long.getDateMillisecond(): Int = this.toLocalDateTime().nanosecond / 1000000

/**
 * 修改时间戳中的毫秒
 * @receiver [Long]
 * @param value [Int] 毫秒
 * @return [Long]
 */
fun Long.setDateMillisecond(value: Int): Long =
    getDateBy(getDateYear(), getDateMonth(), getDateDay(), getDateHour(), getDateMinute(), getDateSecond(), value.coerceAtLeast(0).coerceAtMost(999))

/**
 * 获取时间戳中的年中的周数
 * @receiver [Long]
 * @return [Int] 年中的周数
 */
fun Long.getDateWeekOfYear(): Int {
    val start = LocalDateTime(getDateYear(), 1, 1, 0, 0, 0)
        .toInstant(TimeZone.currentSystemDefault())
    val end = Instant.fromEpochMilliseconds(this)
    return start.until(end, DateTimeUnit.WEEK, TimeZone.currentSystemDefault()).toInt() + 1
}

/**
 * 获取时间戳中的月中的周数
 * @receiver [Long]
 * @return [Int] 月中的周数
 */
fun Long.getDateWeekOfMonth(): Int {
    val start = LocalDateTime(getDateYear(), getDateMonth(), 1, 0, 0, 0)
        .toInstant(TimeZone.currentSystemDefault())
    val end = Instant.fromEpochMilliseconds(this)
    return start.until(end, DateTimeUnit.WEEK, TimeZone.currentSystemDefault()).toInt() + 1
}

/**
 * 获取时间戳中的年中的天数
 * @receiver [Long]
 * @return [Int] 日
 */
fun Long.getDateDayOfYear(): Int = this.toLocalDateTime().dayOfYear

/**
 * 获取时间戳中的星期几
 * @receiver [Long]
 * @return [Int] 星期几
 */
fun Long.getDateDayOfWeek(): Int = this.toLocalDateTime().dayOfWeek.isoDayNumber

/**
 * 获取时间戳中的星期几的文本
 * @receiver [Long]
 * @param type [WeekTextType]
 * @return [String]
 */
fun Long.getDateDayOfWeekText(type: WeekTextType): String =
    type.list[getDateDayOfWeek() - 1]

/**
 * 获取时间戳中的月份的文本
 * @receiver Long
 * @param type MonthTextType
 * @return String
 */
fun Long.getDateMonthText(type: MonthTextType): String =
    type.list[getDateMonth() -1]

/**
 * 获取时间戳的农历信息
 * @receiver [Long]
 * @return [CalendarInfo]?
 */
fun Long.getDateDayLunar(): CalendarInfo? = LunarDateUtil.solar2lunar(getDateYear(), getDateMonth(), getDateDay())

/**
 * 时间戳转换成字符窜
 * @param pattern 时间样式 yyyy-MM-dd HH:mm:ss
 * @return [String] 时间字符串
 */
fun Long.toDateStr(pattern: String = "yyyy-MM-dd HH:mm:ss"): String {
    var string = pattern
    // 将 y、Y 转换成年份
    var reg = "[yY]+".toRegex().findAll(string).toList()
    for (item in reg) {
        string = string.replaceRange(item.range, getDateYear().toString().up2Length(item.value.length))
    }
    // 将 M 转换成月份
    reg = "M+".toRegex().findAll(string).toList()
    for (item in reg) {
        string = string.replaceRange(item.range, getDateMonth().toString().up2Length(item.value.length))
    }
    // 将 d 转换成日期
    reg = "d+".toRegex().findAll(string).toList()
    for (item in reg) {
        string = string.replaceRange(item.range, getDateDay().toString().up2Length(item.value.length))
    }
    // 将 H 转换成小时 24小时制
    reg = "H+".toRegex().findAll(string).toList()
    for (item in reg) {
        string = string.replaceRange(item.range, getDateHour().toString().up2Length(item.value.length))
    }
    // 将 h 转换成小时 12小时制
    reg = "h+".toRegex().findAll(string).toList()
    for (item in reg) {
        string = string.replaceRange(item.range, (if (getDateHour() > 12) getDateHour() - 12 else getDateHour()).toString().up2Length(item.value.length))
    }
    // 将 m 转换成分钟
    reg = "m+".toRegex().findAll(string).toList()
    for (item in reg) {
        string = string.replaceRange(item.range, getDateMinute().toString().up2Length(item.value.length))
    }
    // 将 s 转换成秒钟
    reg = "s+".toRegex().findAll(string).toList()
    for (item in reg) {
        string = string.replaceRange(item.range, getDateSecond().toString().up2Length(item.value.length))
    }
    // 将 S 转换成毫秒
    reg = "S+".toRegex().findAll(string).toList()
    for (item in reg) {
        string = string.replaceRange(item.range, getDateMillisecond().toString().up2Length(item.value.length))
    }
    // 将 w 转换成年中的周数
    reg = "w+".toRegex().findAll(string).toList()
    for (item in reg) {
        string = string.replaceRange(item.range, getDateWeekOfYear().toString().up2Length(item.value.length))
    }
    // 将 W 转换成月中的周数
    reg = "W+".toRegex().findAll(string).toList()
    for (item in reg) {
        string = string.replaceRange(item.range, getDateWeekOfMonth().toString().up2Length(item.value.length))
    }
    // 将 D 转换成年中的天数
    reg = "D+".toRegex().findAll(string).toList()
    for (item in reg) {
        string = string.replaceRange(item.range, getDateDayOfYear().toString().up2Length(item.value.length))
    }
    // 将 E 转换成星期几
    reg = "E+".toRegex().findAll(string).toList()
    for (item in reg) {
        string = string.replaceRange(item.range, getDateDayOfWeek().toString().up2Length(item.value.length))
    }
    // 将 a 转换成 AM/PM
    reg = "a+".toRegex().findAll(string).toList()
    for (item in reg) {
        string = string.replaceRange(item.range, if (getDateHour() > 12) "PM" else "AM")
    }
    return string
}

/**
 * 将字符串转为时间戳
 * @param pattern 时间样式 yyyy-MM-dd HH:mm:ss
 * @return [String] 时间字符串
 */
fun String.toDateLong(pattern: String = "yyyy-MM-dd HH:mm:ss"): Long {
    var string = pattern
    var reg = "a+".toRegex().findAll(string).toList()
    for (item in reg) {
        if (item.value.length == 2) continue
        string = string.replace(item.value, "aa")
    }
    // 将 y、Y 转换成年份
    reg = "[yY]+".toRegex().findAll(string).toList()
    val year = if (reg.isNotEmpty()) this.substring(reg[0].range).toIntOrNull()?: curYear else curYear
    // 将 M 转换成月份
    reg = "M+".toRegex().findAll(string).toList()
    val month = if (reg.isNotEmpty()) this.substring(reg[0].range).toIntOrNull()?: curMonth else 1
    // 将 d 转换成日期
    reg = "d+".toRegex().findAll(string).toList()
    val day = if (reg.isNotEmpty()) this.substring(reg[0].range).toIntOrNull()?: curDay else 1
    // 将 H 转换成小时 24小时制
    reg = "H+".toRegex().findAll(string).toList()
    var hour = if (reg.isNotEmpty())
        this.substring(reg[0].range).toIntOrNull()?: curHour
    else {
        // 将 h 转换成小时 12小时制
        val regh = "h+".toRegex().findAll(string).toList()
        if (regh.isNotEmpty()) this.substring(reg[0].range).toIntOrNull()?: curHour else 0
    }
    reg = "a+".toRegex().findAll(string).toList()
    val isAM = if (reg.isNotEmpty()) this.substring(reg[0].range).contains("AM") else true
    if (!isAM) hour = (hour + 12).coerceAtMost(23)
    // 将 m 转换成分钟
    reg = "m+".toRegex().findAll(string).toList()
    val minute = if (reg.isNotEmpty()) this.substring(reg[0].range).toIntOrNull()?: curMinute else 0
    // 将 s 转换成秒钟
    reg = "s+".toRegex().findAll(string).toList()
    val second  = if (reg.isNotEmpty()) this.substring(reg[0].range).toIntOrNull()?: curSecond else 0
    // 将 S 转换成毫秒
    reg = "S+".toRegex().findAll(string).toList()
    val millisecond  = if (reg.isNotEmpty()) this.substring(reg[0].range).toIntOrNull()?: curMillisecond else 0
    return getDateBy(year, month, day, hour, minute, second, millisecond)
}

/**
 * 根据年月日获取时间戳
 * @param year 年
 * @param month 月
 * @param day 日
 * @return [Long] 时间戳
 */
@Deprecated(
    message = "请更换更标准的另一个方法",
    replaceWith = ReplaceWith("getDateBy(year, month, day)"),
    level = DeprecationLevel.WARNING
)
fun getDateFromYMD(
    year: Int = curYear,
    month: Int = curMonth,
    day: Int = curDay
): Long {
    return getDateBy(year, month, day, 0, 0, 0, 0)
}

/**
 * 根据年月日获取时间戳
 * @param year [Int]
 * @param month [Int]
 * @param day [Int]
 * @return [Long]
 */
fun getDateBy(
    year: Int,
    month: Int,
    day: Int
): Long {
    return getDateBy(year, month, day, 0, 0, 0, 0)
}

/**
 * 根据年月日时分秒获取时间戳
 * @param year [Int] 年
 * @param month [Int] 月
 * @param day [Int] 日
 * @param hour [Int] 时
 * @param minute [Int] 分
 * @param second [Int] 秒
 * @return [Long] 时间戳
 */
@Deprecated(
    message = "请更换更标准的另一个方法",
    replaceWith = ReplaceWith("getDateBy(year, month, day, hour, minute, second)"),
    level = DeprecationLevel.WARNING
)
fun getDateFromYMDHMS(
    year: Int = curYear,
    month: Int = curMonth,
    day: Int = curDay,
    hour: Int = curHour,
    minute: Int = curMinute,
    second: Int = curSecond
): Long {
    return getDateBy(year, month, day, hour, minute, second, 0)
}

/**
 * 根据年月日时分秒获取时间戳
 * @param year Int 年
 * @param month Int 月
 * @param day Int 日
 * @param hour Int 时
 * @param minute Int 分
 * @param second Int 秒
 * @return [Long] 时间戳
 */
fun getDateBy(
    year: Int,
    month: Int,
    day: Int,
    hour: Int,
    minute: Int,
    second: Int
): Long {
    return getDateBy(year, month, day, hour, minute, second, 0)
}

/**
 * 根据参数获取时间戳
 * @param year Int
 * @param month Int
 * @param day Int
 * @param hour Int
 * @param minute Int
 * @param second Int
 * @param millisecond Int
 * @return Long
 */
fun getDateBy(
    year: Int = curYear,
    month: Int = curMonth,
    day: Int = curDay,
    hour: Int = curHour,
    minute: Int = curMinute,
    second: Int = curSecond,
    millisecond: Int = curMillisecond
): Long {
    return LocalDateTime(year, month, day, hour, minute, second, millisecond * 1000000)
        .toInstant(TimeZone.currentSystemDefault()).toEpochMilliseconds()
}

/**
 * 获取第n天的时间戳
 * @param offset [Int] n
 * @return [Long] 时间戳
 */
fun getNextDate(offset: Int = 1): Long {
    return curTime.getNextDay(offset)
}

/**
 * 获取某个日子为标点的附近的日子时间戳
 * @receiver [Long] 标点
 * @param offset [Int] n
 * @return [Long] 时间戳
 */
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
fun getDaysOfMonth(year: Int = curYear, month: Int = curMonth): Int {
    val ldt = LocalDateTime(year, month, 1, 0, 0, 0)
    val start = ldt.toInstant(TimeZone.currentSystemDefault())
    val end = start.plus(1, DateTimeUnit.MONTH, TimeZone.currentSystemDefault())
    return start.until(end, DateTimeUnit.DAY, TimeZone.currentSystemDefault()).toInt()
}

/**
 * 获取今天星期几
 * @return [Int]
 */
@Deprecated(
    message = "请更换更标准的另一个方法",
    replaceWith = ReplaceWith("curDayOfWeek"),
    level = DeprecationLevel.WARNING
)
fun getCurWeek(): Int {
    return curDayOfWeek
}

/**
 * 获取时间戳是星期几
 * @return [Int]
 */
@Deprecated(
    message = "请更换更标准的另一个方法",
    replaceWith = ReplaceWith("getDateDayOfWeek()"),
    level = DeprecationLevel.WARNING
)
fun Long.getDateWeek(): Int {
    return getDateDayOfWeek()
}

/**
 * 时间戳是否为今天的
 * @receiver [Long]
 * @return [Boolean]
 */
fun Long.isToday(): Boolean = isNextDay(0)

/**
 * 时间戳是否为昨天的
 * @receiver [Long]
 * @return [Boolean]
 */
fun Long.isYesterday(): Boolean = isNextDay(-1)

/**
 * 时间戳是否为今天的某一天偏移
 * @receiver [Long]
 * @param offset [Int]
 * @return [Boolean]
 */
fun Long.isNextDay(offset: Int = 1): Boolean {
    val today = LocalDateTime(curYear, curMonth, curDay, 0, 0, 0, 0)
        .toInstant(TimeZone.currentSystemDefault())
    val endTime = this.setDateHour(0)
        .setDateMinute(0)
        .setDateSecond(0)
        .setDateMillisecond(0)
    val end = Instant.fromEpochMilliseconds(endTime)
    val temp = today.plus(offset, DateTimeUnit.DAY, TimeZone.currentSystemDefault())
    return temp.daysUntil(end, TimeZone.currentSystemDefault()) == 0
}

/**
 * 本地时间转化为UTC时间
 * @receiver [Long]
 * @return [Long]
 */
fun Long.toUTCDate(): Long {
    return toLocalDateTime(TimeZone.UTC).toInstant(TimeZone.currentSystemDefault()).toEpochMilliseconds()
}

/**
 * UTC时间转化为本地时间
 * @receiver [Long]
 * @return [Long]
 */
fun Long.toLocalDate(): Long {
    return toLocalDateTime(TimeZone.currentSystemDefault()).toInstant(TimeZone.UTC).toEpochMilliseconds()
}

/**
 * UTC时间转化为指定timeZone时间
 * @receiver [Long]
 * @param timeZoneInt [Int]
 * @return [Long]
 */
fun Long.toCustomDate(timeZoneInt: Int): Long {
    val instant = Instant.fromEpochMilliseconds(this)
    val end = instant.plus(timeZoneInt, DateTimeUnit.HOUR)
    return end.toEpochMilliseconds()
}

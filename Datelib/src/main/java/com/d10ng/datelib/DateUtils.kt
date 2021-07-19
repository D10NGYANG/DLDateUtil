package com.d10ng.datelib

import java.text.SimpleDateFormat
import java.util.*

/**
 * 时间工具
 *
 * @author D10NG
 * @date on 2019-10-08 11:28
 */

/**
 * 获取当前系统时间戳，单位毫秒
 */
val curTime: Long
    get() = System.currentTimeMillis()

/**
 * 获取当前年份
 */
val curYear: Int
    get() = curTime.getDateYear()

/**
 * 获取当前月份
 */
val curMonth: Int
    get() = curTime.getDateMonth()

/**
 * 获取当前日
 */
val curDay: Int
    get() = curTime.getDateDay()

/**
 * 获取当前小时
 */
val curHour: Int
    get() = curTime.getDateHour()

/**
 * 获取当前分钟
 */
val curMinute: Int
    get() = curTime.getDateMinute()

/**
 * 获取当前秒钟
 */
val curSecond: Int
    get() = curTime.getDateSecond()

/**
 * 获取时间戳中的年份
 * @return [Int] 年份
 */
fun Long.getDateYear(): Int {
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = this
    return calendar.get(Calendar.YEAR)
}

/**
 * 获取时间戳中的月份
 * @return [Int] 月份
 */
fun Long.getDateMonth(): Int {
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = this
    return calendar.get(Calendar.MONTH) + 1
}

/**
 * 获取时间戳中的日
 * @return [Int] 日
 */
fun Long.getDateDay(): Int {
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = this
    return calendar.get(Calendar.DAY_OF_MONTH)
}

/**
 * 获取时间戳中的小时 24小时
 * @return [Int] 小时
 */
fun Long.getDateHour(): Int {
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = this
    return calendar.get(Calendar.HOUR_OF_DAY)
}

/**
 * 获取时间戳中的分钟
 * @return [Int] 分钟
 */
fun Long.getDateMinute(): Int {
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = this
    return calendar.get(Calendar.MINUTE)
}

/**
 * 获取时间戳中的秒钟
 * @return [Int] 秒钟
 */
fun Long.getDateSecond(): Int {
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = this
    return calendar.get(Calendar.SECOND)
}

/**
 * 时间戳转换成字符窜
 * @param pattern 时间样式 yyyy-MM-dd HH:mm:ss
 * @return [String] 时间字符串
 */
fun Long.toDateStr(pattern: String = "yyyy-MM-dd HH:mm:ss"): String {
    val date = Date(this)
    val format = SimpleDateFormat(pattern)
    return format.format(date)
}

/**
 * 将字符串转为时间戳
 * @param pattern 时间样式 yyyy-MM-dd HH:mm:ss
 * @return [String] 时间字符串
 */
fun String.toDateLong(pattern: String = "yyyy-MM-dd HH:mm:ss"): Long {
    val dateFormat = SimpleDateFormat(pattern)
    var date: Date? = Date()
    try {
        date = dateFormat.parse(this)
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return date?.time ?: 0
}

/**
 * 根据年月日获取时间戳
 * @param year 年
 * @param month 月
 * @param day 日
 * @return [Long] 时间戳
 */
fun getDateFromYMD(year: Int = curYear, month: Int = curMonth, day: Int = curDay): Long {
    return getDateFromYMDHMS(year, month, day, 0, 0, 0)
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
fun getDateFromYMDHMS(
    year: Int = curYear,
    month: Int = curMonth,
    day: Int = curDay,
    hour: Int = curHour,
    minute: Int = curMinute,
    second: Int = curSecond
): Long {
    val calendar = Calendar.getInstance()
    calendar.set(year, month -1, day, hour, minute, second)
    calendar.set(Calendar.MILLISECOND, 0)
    return calendar.timeInMillis
}

/**
 * 获取第n天的时间戳
 * @param offset n
 * @return [Long] 时间戳
 */
fun getNextDate(offset: Int): Long {
    val calendar = Calendar.getInstance()
    calendar.time = Date(getDateFromYMD(curYear, curMonth , curDay))
    calendar.add(Calendar.DAY_OF_MONTH, offset)
    return calendar.timeInMillis
}

/**
 * 获取某个日子为标点的附近的日子时间戳
 * @receiver Long
 * @param offset Int
 * @return Long
 */
fun Long.getNextDay(offset: Int): Long {
    val calendar = Calendar.getInstance()
    calendar.time = Date(this)
    calendar.add(Calendar.DAY_OF_MONTH, offset)
    return calendar.timeInMillis
}

/**
 * 获取指定月份的天数
 * @param year 年
 * @param month 月
 * @return [Int] 天数
 */
fun getDaysOfMonth(year: Int, month: Int): Int {
    val calendar = Calendar.getInstance()
    calendar.set(Calendar.YEAR, year)
    calendar.set(Calendar.MONTH, month -1)
    return calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
}

/**
 * 获取今天星期几
 * @return [Int] [Calendar.SUNDAY]
 */
fun getCurWeek(): Int {
    return curTime.getDateWeek()
}

/**
 * 获取时间戳是星期几
 * @return [Int] [Calendar.SUNDAY]
 */
fun Long.getDateWeek(): Int {
    val calendar = Calendar.getInstance()
    calendar.time = Date(this)
    return calendar.get(Calendar.DAY_OF_WEEK)
}

/**
 * 时间戳是否为今天的
 * @receiver Long
 * @return Boolean
 */
fun Long.isToday(): Boolean {
    return getDateYear() == curYear && getDateMonth() == curMonth && getDateDay() == curDay
}

/**
 * 时间戳是否为昨天的
 * @receiver Long
 * @return Boolean
 */
fun Long.isYesterday(): Boolean {
    val yesterday = curTime.getNextDay(-1)
    return getDateYear() == yesterday.getDateYear() && getDateMonth() == yesterday.getDateMonth() && getDateDay() == yesterday.getDateDay()
}

/**
 * 本地时间转化为UTC时间
 * @receiver Long
 * @return Long
 */
fun Long.toUTCDate(): Long {
    val calendar = Calendar.getInstance().apply {
        timeInMillis = this@toUTCDate
    }
    val zoneOffset = calendar.get(Calendar.ZONE_OFFSET)
    val dstOffset = calendar.get(Calendar.DST_OFFSET)
    calendar.add(Calendar.MILLISECOND, - (zoneOffset + dstOffset))
    return calendar.timeInMillis
}

/**
 * UTC时间转化为本地时间
 * @receiver Long
 * @return Long
 */
fun Long.toLocalDate(): Long {
    val pattern = "yyyyMMddHHmmssSSS"
    val utcSdf = SimpleDateFormat(pattern).apply {
        timeZone = TimeZone.getTimeZone("UTC")
    }
    val utcD = utcSdf.parse(this.toDateStr(pattern))?: return 0L
    val localSdf = SimpleDateFormat(pattern).apply {
        timeZone = TimeZone.getDefault()
    }
    val localStr = localSdf.format(utcD.time)
    return localStr.toDateLong(pattern)
}

/**
 * UTC时间转化为指定timeZone时间
 * @receiver Long
 * @param timeZoneInt Int
 * @return Long
 */
fun Long.toCustomDate(timeZoneInt: Int): Long {
    val pattern = "yyyyMMddHHmmssSSS"
    val utcSdf = SimpleDateFormat(pattern).apply {
        timeZone = TimeZone.getTimeZone("UTC")
    }
    val utcD = utcSdf.parse(this.toDateStr(pattern))?: return 0L
    val localSdf = SimpleDateFormat(pattern).apply {
        timeZone = TimeZone.getTimeZone("GMT" + (if (timeZoneInt >= 0) "+" else "") + timeZoneInt)
    }
    val localStr = localSdf.format(utcD.time)
    return localStr.toDateLong(pattern)
}

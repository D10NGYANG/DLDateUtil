package com.d10ng.datelib

import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.TimeZone
import kotlinx.datetime.plus
import kotlin.time.ExperimentalTime

/**
 * 从给定的毫秒级时间戳计算偏移制定天数的另一个时间戳
 * @receiver [Long] 时间戳，单位毫秒
 * @param offset [Int] 偏移天数
 * @param timeZone [TimeZone] 时区
 * @return [Long] 偏移后的时间戳，单位毫秒
 */
@OptIn(ExperimentalTime::class)
fun Long.timestampPlusDays(offset: Int, timeZone: TimeZone = TimeZone.currentSystemDefault()): Long =
    timestampToInstant().plus(offset, DateTimeUnit.DAY, timeZone).toTimestamp()
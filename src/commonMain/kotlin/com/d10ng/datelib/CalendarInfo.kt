package com.d10ng.datelib

import kotlin.js.JsExport

@JsExport
data class CalendarInfo(
    // 农历年
    val lYear: Int,
    // 农历月
    val lMonth: Int,
    // 农历日
    val lDay: Int,
    // 生肖
    val animal: String,
    // 中文农历月
    val lMonthCn: String,
    // 中文农历日
    val lDayCn: String,
    // 公历年
    val cYear: Int,
    // 公历月
    val cMonth: Int,
    // 公历日
    val cDay: Int,
    // 干支年
    val gzYear: String,
    // 干支月
    val gzMonth: String,
    // 干支日
    val gzDay: String,
    // 是否是今天
    val isToday: Boolean,
    // 是否是闰月
    val isLeap: Boolean,
    // 当前日是一周中的第几天
    val nWeek: Int,
    // 中文星期
    val ncWeek: String,
    // 是否是节气
    val isTerm: Boolean,
    // 节气
    val term: String? = null
)
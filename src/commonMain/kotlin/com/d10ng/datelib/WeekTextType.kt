package com.d10ng.datelib

enum class WeekTextType (val list: List<String>) {

    // 星期日
    CN (listOf("星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期日")),
    // 周日
    CN_SHORT (listOf("周一", "周二", "周三", "周四", "周五", "周六", "周日")),
    // 日
    CN_MINI (listOf("一", "二", "三", "四", "五", "六", "日")),
    // MONDAY
    EN (listOf("MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY", "SATURDAY", "SUNDAY")),
    // MON
    EN_SHORT (listOf("MON", "TUE", "WED", "THUR", "FRI", "SAT", "SUN")),
}
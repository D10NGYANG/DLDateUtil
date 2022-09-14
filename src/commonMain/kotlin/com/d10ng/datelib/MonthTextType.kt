package com.d10ng.datelib

enum class MonthTextType (val list: List<String>) {

    // 一月
    CN (listOf("一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月")),
    // JANUARY
    EN (listOf("JANUARY", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December")),
    // JAN
    EN_SHORT (listOf("JAN", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec")),
}
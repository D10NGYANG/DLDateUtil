package com.d10ng.datelib

import kotlin.js.JsExport

@JsExport
enum class MonthTextType (val list: Array<String>) {

    // 一月
    CN (arrayOf("一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月")),
    // JANUARY
    EN (arrayOf("JANUARY", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December")),
    // JAN
    EN_SHORT (arrayOf("JAN", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec")),
}
package com.d10ng.datelib

import kotlin.test.Test
import kotlin.test.assertTrue

class Test {

    @Test
    fun test() {
        assertTrue {
            println("获取当前时间戳（毫秒）$curTime")
            // 获取当前时间戳（毫秒）1636097830332
            println("获取当前年份 $curYear")
            // 获取当前年份 2021
            println("获取当前月份 $curMonth")
            // 获取当前月份 11
            println("获取当前日 $curDay")
            // 获取当前日 5
            println("获取当前小时 $curHour")
            // 获取当前小时 15
            println("获取当前分钟 $curMinute")
            // 获取当前分钟 37
            println("获取当前秒 $curSecond")
            // 获取当前秒 10
            println("获取当前毫秒 $curMillisecond")
            // 获取当前毫秒 360
            println("获取当前年中的周数 $curWeekOfYear")
            // 获取当前年中的周数 45
            println("获取当前月中的周数 $curWeekOfMonth")
            // 获取当前月中的周数 1
            println("获取当前年中的天数 $curDayOfYear")
            // 获取当前年中的天数 309
            println("获取当前星期几 $curDayOfWeek")
            // 获取当前星期几 5
            println("今天的农历信息 $curDayLunar")
            // 今天的农历信息 CalendarInfo(lYear=2021, lMonth=10, lDay=1, Animal=牛, IMonthCn=十月, IDayCn=初一, cYear=2021, cMonth=11, cDay=5, gzYear=辛丑, gzMonth=戊戌, gzDay=丁巳, isToday=true, isLeap=false, nWeek=5, ncWeek=星期五, isTerm=false, Term=null)

            val time = 1635652800000L
            println("获取时间戳中的年份 ${time.getDateYear()}")
            // 获取时间戳中的年份 2021
            println("修改时间戳中的年份 ${time.setDateYear(2000).getDateYear()}")
            // 修改时间戳中的年份 2000
            println("获取时间戳中的月份 ${time.getDateMonth()}")
            // 获取时间戳中的月份 10
            println("修改时间戳中的月份 ${time.setDateMonth(1).getDateMonth()}")
            // 修改时间戳中的月份 1
            println("获取时间戳中的日 ${time.getDateDay()}")
            // 获取时间戳中的日 31
            println("修改时间戳中的日 ${time.setDateDay(1).getDateDay()}")
            // 修改时间戳中的日 1
            println("获取时间戳中的小时 ${time.getDateHour()}")
            // 获取时间戳中的小时 12
            println("修改时间戳中的小时 ${time.setDateHour(1).getDateHour()}")
            // 修改时间戳中的小时 1
            println("获取时间戳中的分钟 ${time.getDateMinute()}")
            // 获取时间戳中的分钟 0
            println("修改时间戳中的分钟 ${time.setDateMinute(1).getDateMinute()}")
            // 修改时间戳中的分钟 1
            println("获取时间戳中的秒 ${time.getDateSecond()}")
            // 获取时间戳中的秒 0
            println("修改时间戳中的秒 ${time.setDateSecond(1).getDateSecond()}")
            // 修改时间戳中的秒 1
            println("获取时间戳中的毫秒 ${time.getDateMillisecond()}")
            // 获取时间戳中的毫秒 0
            println("修改时间戳中的毫秒 ${time.setDateMillisecond(1).getDateMillisecond()}")
            // 修改时间戳中的毫秒 1
            println("获取时间戳中的年中的周数 ${time.getDateWeekOfYear()}")
            // 获取时间戳中的年中的周数 44
            println("获取时间戳中的月中的周数 ${time.getDateWeekOfMonth()}")
            // 获取时间戳中的月中的周数 5
            println("获取时间戳中的年中的天数 ${time.getDateDayOfYear()}")
            // 获取时间戳中的年中的天数 304
            println("获取时间戳中的星期几 ${time.getDateDayOfWeek()}")
            // 获取时间戳中的星期几 7
            println("获取时间戳中的星期几的文本 ${time.getDateDayOfWeekText(WeekTextType.CN)} " +
                    "${time.getDateDayOfWeekText(WeekTextType.CN_SHORT)} " +
                    "${time.getDateDayOfWeekText(WeekTextType.CN_MINI)} " +
                    "${time.getDateDayOfWeekText(WeekTextType.EN)} " +
                    "${time.getDateDayOfWeekText(WeekTextType.EN_SHORT)} ")
            // 获取时间戳中的星期几的文本 星期日 周日 日 SUNDAY SUN
            println("获取时间戳中的月份的文本 ${time.getDateMonthText(MonthTextType.CN)} " +
                    "${time.getDateMonthText(MonthTextType.EN)} " +
                    "${time.getDateMonthText(MonthTextType.EN_SHORT)} ")
            // 获取时间戳中的月份的文本 十月 October Oct
            println("获取时间戳的农历信息 ${time.getDateDayLunar()}")
            // 获取时间戳的农历信息 CalendarInfo(lYear=2021, lMonth=9, lDay=26, Animal=牛, IMonthCn=九月, IDayCn=廿六, cYear=2021, cMonth=10, cDay=31, gzYear=辛丑, gzMonth=戊戌, gzDay=壬子, isToday=false, isLeap=false, nWeek=7, ncWeek=星期日, isTerm=false, Term=null)

            val pattern = "yyyy年MM月dd日 HH时mm分ss秒SSS毫秒 今年的第ww周 当前月的第W周 今年的第DDD天 星期E a hh"
            println("时间戳转换成字符窜 ${curTime.toDateStr(pattern)}")
            // 时间戳转换成字符窜 2021年11月05日 15时37分10秒384毫秒 今年的第45周 当前月的第1周 今年的第309天 星期5 PM 03
            println("将字符串转为时间戳 ${curTime.toDateStr(pattern).toDateLong(pattern)}")
            // 将字符串转为时间戳 1636097830410
            println("将字符串转为时间戳2 ${"2021-11-05 15:37:10".toDateLong()}")
            // 将字符串转为时间戳 1636097830410
            println("根据年月日获取时间戳 ${getDateBy(2020, 2, 20).toDateStr()}")
            // 根据年月日获取时间戳 2020-02-20 00:00:00
            println("根据年月日时分秒获取时间戳 ${getDateBy(2020, 2, 20, 12, 30, 45).toDateStr()}")
            // 根据年月日时分秒获取时间戳 2020-02-20 12:30:45
            println("根据年月日时分秒毫秒获取时间戳 ${getDateBy(2020, 2, 20, 12, 30, 45, 125).toDateStr(pattern)}")
            // 根据年月日时分秒毫秒获取时间戳 2020年02月20日 12时30分45秒125毫秒 今年的第08周 当前月的第3周 今年的第051天 星期4 AM 12
            println("获取第n天的时间戳（如-1） ${getNextDate(-1).toDateStr()}")
            // 获取第n天的时间戳（如-1） 2021-11-04 15:37:10
            println("获取某个日子为标点的附近的日子时间戳（如-1） ${time.getNextDay(-1).toDateStr()}")
            // 获取某个日子为标点的附近的日子时间戳（如-1） 2021-10-30 12:00:00
            println("获取指定月份的天数 (当前月) ${getDaysOfMonth(curYear, curMonth)}")
            // 获取指定月份的天数 (当前月) 30
            println("判断时间戳是否为今天的（前天） ${getNextDate(-2).isToday()}")
            // 判断时间戳是否为今天的（前天） false
            println("判断时间戳是否为昨天的（前天） ${getNextDate(-2).isYesterday()}")
            // 判断时间戳是否为昨天的（前天） false
            println("判断时间戳是否为今天的某一天偏移（前天） ${getNextDate(-2).isNextDay(-2)}")
            // 判断时间戳是否为今天的某一天偏移（前天） true
            println("本地时间转化为UTC时间 ${curTime.toUTCDate().toDateStr()}")
            // 本地时间转化为UTC时间 2021-11-05 07:54:33
            println("UTC时间转化为本地时间 ${curTime.toUTCDate().toLocalDate().toDateStr()}")
            // UTC时间转化为本地时间 2021-11-05 15:54:33
            println("UTC时间转化为指定timeZone时间 ${curTime.toUTCDate().toCustomDate(+8).toDateStr()}")
            // UTC时间转化为指定timeZone时间 2021-11-05 15:54:33
            println("农历转新历 ${LunarDateUtil.lunar2solar(2020, 10, 23)}")
            // 农历转新历 CalendarInfo(lYear=2020, lMonth=10, lDay=23, Animal=鼠, IMonthCn=十月, IDayCn=廿三, cYear=2020, cMonth=12, cDay=7, gzYear=庚子, gzMonth=戊子, gzDay=甲申, isToday=false, isLeap=false, nWeek=1, ncWeek=星期一, isTerm=true, Term=大雪)
            true
        }
    }
}
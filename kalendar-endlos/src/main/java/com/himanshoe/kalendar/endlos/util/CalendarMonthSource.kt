package com.himanshoe.kalendar.endlos.util

import java.time.YearMonth

/**
 * [CalendarMonthSource] is responsible for generating [List] of [MonthCalendarData]
 * from Month 1-12
 */
object CalendarMonthSource {

    fun getMonthsByYear(year: Int): List<MonthCalendarData> {
        return mutableListOf<MonthCalendarData>().apply {
            repeat(12) { month ->
                add(element = MonthCalendarData(month, year))
            }
        }
    }
}

data class MonthCalendarData(val month: Int, val year: Int)

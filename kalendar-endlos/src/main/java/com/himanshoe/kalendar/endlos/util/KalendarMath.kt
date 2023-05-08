package com.himanshoe.kalendar.util

import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.plus
import kotlinx.datetime.todayIn


object KalendarMath {
    fun getDates(currentDate: LocalDate = Clock.System.todayIn(TimeZone.currentSystemDefault())): List<LocalDate> {
        // Create mutable list of DateTime as a result
        val result = mutableListOf<LocalDate>()
        // Get current day of the week (where 1 = Monday, 7 = Sunday)
        val currentDayOfTheWeek = currentDate.dayOfWeek.value
        // Week count
        val weeksCount = WEEKS_COUNT - 1
        for (weekId in 0..weeksCount) {
            (1..DAYS_IN_WEEK)
                .asSequence()
                .map { (currentDayOfTheWeek * -1) + it - (DAYS_IN_WEEK * (weeksCount - weekId)) }
                .forEach { result.add(currentDate.plus(it.toLong(), DateTimeUnit.DAY)) }
        }
        return result
    }

    private const val DAYS_IN_WEEK = 7
    private const val WEEKS_COUNT = 4
}
package com.himanshoe.kalendar.endlos.paging

import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalDate
import kotlinx.datetime.Month
import kotlinx.datetime.plus
import java.time.YearMonth

data class KalendarModelEntity(
    val name: String,
    val dates: List<LocalDate?>,
    val month: Month,
    val year: Int
)

class KalendarRepository {

    fun generateDates(year: Int = 2023) = Month.values().map { month ->
        val yearMonth = YearMonth.of(year, month)
        val startDate = yearMonth.atDay(1)
        val startDayOfWeek = startDate.dayOfWeek
        val start = getKalendarStartDay(startDayOfWeek)
        val dateRange = (start..yearMonth.lengthOfMonth())
        val dates = dateRange.map { date ->
            if (date > 0) {
                LocalDate(yearMonth.year, yearMonth.month, date)
            } else null
        }

        KalendarModelEntity(month.name, dates, yearMonth.month, yearMonth.year)
    }

    fun generateDateList(
        startDate: LocalDate,
        endDate: LocalDate,
        initialDates: List<LocalDate>
    ): List<LocalDate> {
        val dates = mutableListOf<LocalDate>()
        var currentDate = startDate

        // Add initial dates that fall before the start date
        val initialDatesBeforeStart = initialDates.filter { it < startDate }
        dates.addAll(initialDatesBeforeStart)

        // Add dates within the range
        while (currentDate <= endDate) {
            dates.add(currentDate)
            currentDate = currentDate.plus(1, DateTimeUnit.DAY)
        }

        return dates
    }
}

private fun getKalendarStartDay(dayOfWeek: DayOfWeek): Int {
    return 1.minus(dayOfWeek.value)
}

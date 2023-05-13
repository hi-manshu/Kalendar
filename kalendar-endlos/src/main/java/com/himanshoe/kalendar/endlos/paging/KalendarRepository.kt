package com.himanshoe.kalendar.endlos.paging

import kotlinx.datetime.LocalDate
import kotlinx.datetime.Month
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.toKotlinLocalDate
import java.time.YearMonth

data class KalendarModelEntity(
    val name: String,
    val dates: List<LocalDate?>,
    val month: Month,
    val year: Int
)

class KalendarRepository {

    fun generateDatesGroupedByMonth(year: Int = 2023): List<KalendarModelEntity> {
        return Month.values().map { month ->
            val yearMonth = YearMonth.of(year, month)
            val startDate = yearMonth.atDay(1)
            val endDate = yearMonth.atEndOfMonth()

            val datesInMonth = generateSequence(startDate) { date ->
                if (date == endDate) null else date.plusDays(1)
            }.toList().map { it.toKotlinLocalDate() }

            KalendarModelEntity(month.name, datesInMonth, yearMonth.month, yearMonth.year)
        }
    }

    fun generateDates(year: Int = 2023) = Month.values().map { month ->
        val yearMonth = YearMonth.of(year, month)
        val startDate = yearMonth.atDay(1)
        val startDayOfWeek = startDate.dayOfWeek
        val start = getKalendarStartDay(startDayOfWeek)
        val dateRange = (start..yearMonth.lengthOfMonth())
        val dates = dateRange.map {
            if (it > 0) {
                LocalDate(yearMonth.year, yearMonth.month, it)
            } else null
        }

        KalendarModelEntity(month.name, dates, yearMonth.month, yearMonth.year)
    }
}

private fun getKalendarStartDay(dayOfWeek: DayOfWeek): Int {
    return 1.minus(dayOfWeek.value)
}

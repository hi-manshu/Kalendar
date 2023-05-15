/*
 * Copyright 2023 Kalendar Contributors (https://www.himanshoe.com). All rights reserved.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.himanshoe.kalendar.endlos.paging

import androidx.compose.runtime.Immutable
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalDate
import kotlinx.datetime.Month
import kotlinx.datetime.plus
import java.time.YearMonth

/**
 * Represents a Kalendar model entity.
 *
 * @param name The name of the entity.
 * @param dates The list of dates associated with the entity.
 * @param month The month of the entity.
 * @param year The year of the entity.
 */
@Immutable
data class KalendarModelEntity(
    val name: String,
    val dates: List<LocalDate?>,
    val month: Month,
    val year: Int
)

/**
 * Repository for generating KalendarModelEntity objects and managing date lists.
 */
class KalendarRepository {

    /**
     * Generates a list of [KalendarModelEntity] objects for the specified year.
     *
     * @param year The year for which to generate the [KalendarModelEntity] objects.
     * @return The list of generated [KalendarModelEntity] objects.
     */
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

    /**
     * Generates a list of dates within the specified date range, including initial dates.
     *
     * @param startDate The start date of the range.
     * @param endDate The end date of the range.
     * @param initialDates The initial dates to include in the list.
     * @return The list of generated dates.
     */
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

/**
 * Calculates the start day for the Kalendar based on the specified day of the week.
 *
 * @param dayOfWeek The day of the week.
 * @return The start day for the Kalendar.
 */
private fun getKalendarStartDay(dayOfWeek: DayOfWeek): Int {
    return 1.minus(dayOfWeek.value)
}

package com.himanshoe.kalendar.core.util

import kotlinx.datetime.LocalDate

data class KalendarSelectedDayRange(
    val start: LocalDate,
    val end: LocalDate
) {
    /**
     * Checks if the selected day range is empty (start date is after end date).
     * @return True if the range is empty, false otherwise.
     */
    fun isEmpty() = start > end

    /**
     * Checks if the selected day range contains a single date (start and end dates are the same).
     * @return True if the range contains a single date, false otherwise.
     */
    fun isSingleDate() = start == end
}
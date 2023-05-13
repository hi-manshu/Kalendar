package com.himanshoe.kalendar.ui.firey

import androidx.compose.runtime.Stable
import kotlinx.datetime.LocalDate

/**
 * Data class representing a selected day range in the Kalendar.
 * @param start The start date of the range.
 * @param end The end date of the range.
 */
@Stable
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


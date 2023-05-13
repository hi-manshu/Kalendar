package com.himanshoe.kalendar.endlos.daterange

import androidx.compose.runtime.Stable
import kotlinx.datetime.LocalDate

/**
 * Represents a selected day range in a calendar.
 *
 * @property start The start date of the selected range.
 * @property end The end date of the selected range.
 */
@Stable
data class KalendarSelectedDayRange(
    val start: LocalDate,
    val end: LocalDate
) {
    /**
     * Checks if the selected day range is empty.
     * Returns true if the start date is after the end date, indicating an empty range.
     *
     * @return true if the range is empty, false otherwise.
     */
    fun isEmpty(): Boolean {
        return start > end
    }

    /**
     * Checks if the selected day range contains a single date.
     * Returns true if the start date is the same as the end date, indicating a single date range.
     *
     * @return true if the range is a single date, false otherwise.
     */
    fun isSingleDate(): Boolean {
        return start == end
    }
}


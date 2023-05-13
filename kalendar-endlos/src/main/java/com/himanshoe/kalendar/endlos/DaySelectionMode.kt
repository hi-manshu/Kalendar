package com.himanshoe.kalendar.endlos

/**
 * Represents the selection mode for days in the Kalendar.
 */
sealed interface DaySelectionMode {
    object Single : DaySelectionMode // Allows selection of a single day.
    object Range : DaySelectionMode // Allows selection of a range of days.
}

/**
 * Represents the error types that can occur during range selection in the Kalendar.
 */
sealed interface RangeSelectionError {
    object EndIsBeforeStart : RangeSelectionError // Indicates that the selected end date is before the start date.
}

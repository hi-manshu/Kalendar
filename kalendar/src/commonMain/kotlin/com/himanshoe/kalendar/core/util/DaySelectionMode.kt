package com.himanshoe.kalendar.core.util

sealed interface DaySelectionMode {
    /**
     * Represents the single day selection mode.
     */
    object Single : DaySelectionMode

    /**
     * Represents the range selection mode.
     */
    object Range : DaySelectionMode
}
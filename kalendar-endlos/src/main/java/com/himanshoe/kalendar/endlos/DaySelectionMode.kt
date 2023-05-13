package com.himanshoe.kalendar.endlos

sealed interface DaySelectionMode {
    object Single : DaySelectionMode
    object Range : DaySelectionMode
}

sealed interface RangeSelectionError{
    object EndIsBeforeStart :RangeSelectionError
}

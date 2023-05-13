package com.himanshoe.kalendar.ui.firey

sealed interface DaySelectionMode {
    object Single : DaySelectionMode
    object Range : DaySelectionMode
}

sealed interface RangeSelectionError{
    object EndIsBeforeStart :RangeSelectionError
}

package com.himanshoe.kalendar.core.util

import com.himanshoe.kalendar.event.KalendarEvent
import kotlinx.datetime.LocalDate

sealed class OnDaySelectionAction {
    data class Single(val onDayClick: (LocalDate, List<KalendarEvent>) -> Unit) :
        OnDaySelectionAction()

    data class Range(val onRangeSelected: (KalendarSelectedDayRange, List<KalendarEvent>) -> Unit) :
        OnDaySelectionAction()
}
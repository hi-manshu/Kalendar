package com.himanshoe.kalendar.foundation.action

import com.himanshoe.kalendar.foundation.event.KalendarEvent
import kotlinx.datetime.LocalDate

sealed class OnDaySelectionAction {
    data class Single(val onDayClick: (LocalDate, List<KalendarEvent>) -> Unit) :
        OnDaySelectionAction()

    data class Range(val onRangeSelected: (KalendarSelectedDayRange, List<KalendarEvent>) -> Unit) :
        OnDaySelectionAction()
}

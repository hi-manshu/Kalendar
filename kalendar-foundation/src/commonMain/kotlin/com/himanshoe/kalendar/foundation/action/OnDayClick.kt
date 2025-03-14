package com.himanshoe.kalendar.foundation.action

import com.himanshoe.kalendar.foundation.event.KalendarEvent
import kotlinx.datetime.LocalDate

fun LocalDate.onDayClick(
    events: List<KalendarEvent>,
    rangeStartDate: LocalDate?,
    rangeEndDate: LocalDate?,
    onDaySelectionAction: OnDaySelectionAction,
    onClickedNewDate: (LocalDate) -> Unit,
    onClickedRangeStartDate: (LocalDate?) -> Unit,
    onClickedRangeEndDate: (LocalDate?) -> Unit,
    onUpdateSelectedRange: (KalendarSelectedDayRange?) -> Unit,
) {
    when (onDaySelectionAction) {
        is OnDaySelectionAction.Single -> {
            onClickedNewDate(this)
            onDaySelectionAction.onDayClick(this, events)
        }

        is OnDaySelectionAction.Range -> {
            if (rangeStartDate == null || rangeEndDate != null) {
                onClickedRangeStartDate(this)
                onClickedRangeEndDate(null)
            } else {
                var newRangeStartDate = rangeStartDate
                var newRangeEndDate = this
                if (newRangeStartDate > newRangeEndDate) {
                    val temp = newRangeStartDate
                    newRangeStartDate = newRangeEndDate
                    newRangeEndDate = temp
                }
                onClickedRangeStartDate(newRangeStartDate)
                onClickedRangeEndDate(newRangeEndDate)
                val range = KalendarSelectedDayRange(
                    newRangeStartDate, newRangeEndDate
                )
                onUpdateSelectedRange(range)
                onDaySelectionAction.onRangeSelected(range, events)
            }
            onClickedNewDate(this)
        }
    }
}

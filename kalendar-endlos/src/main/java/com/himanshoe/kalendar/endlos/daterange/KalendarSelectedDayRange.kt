package com.himanshoe.kalendar.endlos.daterange

import kotlinx.datetime.LocalDate

data class KalendarSelectedDayRange(
    val start: LocalDate,
    val end: LocalDate
) {
    fun isEmpty(): Boolean {
        return start > end
    }

    fun isSingleDate(): Boolean {
        return start == end
    }
}

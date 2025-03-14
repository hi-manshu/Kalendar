package com.himanshoe.kalendar.foundation.action

import kotlinx.datetime.LocalDate

data class KalendarSelectedDayRange(
    val start: LocalDate,
    val end: LocalDate
)

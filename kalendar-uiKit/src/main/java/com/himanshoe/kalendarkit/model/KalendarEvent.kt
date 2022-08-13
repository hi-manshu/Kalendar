package com.himanshoe.kalendarkit.model

import java.time.LocalDate

data class KalendarEvent(
    val date: LocalDate,
    val eventName: String,
    val eventDescription: String? = null,
)

package com.himanshoe.kalendar

import kotlinx.datetime.LocalDate

data class KalendarEvent(
    val date: LocalDate,
    val eventName: String,
    val eventDescription: String? = null,
)

data class KalendarEvents(
    val events: List<KalendarEvent> = emptyList()
)

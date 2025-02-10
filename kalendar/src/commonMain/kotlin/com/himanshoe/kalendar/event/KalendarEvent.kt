package com.himanshoe.kalendar.event

import kotlinx.datetime.LocalDate

data class KalendarEvent(
    val date: LocalDate,
    val eventName: String,
    val eventDescription: String? = null
)

data class KalendarEvents(
    val eventList: List<KalendarEvent>
)



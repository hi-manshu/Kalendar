package com.himanshoe.kalendar.model

import kotlinx.datetime.LocalDate

data class KalendarEvent(
    val date: LocalDate,
    val eventName: String,
    val eventDescription: String? = null,
)

package com.himanshoe.kalendar.endlos.model

import androidx.compose.runtime.Stable
import kotlinx.datetime.LocalDate

/**
 * Represents a calendar event.
 *
 * @param date The date of the event.
 * @param eventName The name or title of the event.
 * @param eventDescription The description or additional details of the event (optional).
 */
@Stable
data class KalendarEvent(
    val date: LocalDate,
    val eventName: String,
    val eventDescription: String? = null
)

/**
 * Represents a collection of calendar events.
 *
 * @param events The list of calendar events.
 */
@Stable
data class KalendarEvents(
    val events: List<KalendarEvent> = emptyList()
)

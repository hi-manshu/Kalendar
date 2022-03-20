package com.himanshoe.kalendar.endlos.common.data

import java.time.LocalDate

/**
 * [KalendarEvent] handles the event marked on any
 * @param[date] with specific
 * @param[eventName] and its
 * @param[eventDescription] along with it's
 * @param[eventType] type
 */
data class KalendarEvent(
    val date: LocalDate,
    val eventName: String,
    val eventDescription: String? = null,
    val eventType: EventType = EventType.Event,
)

sealed class EventType {
    object Goal : EventType()
    object Reminder : EventType()
    object Task : EventType()
    object Event : EventType()
}

package com.himanshoe.kalendar.endlos.common.data

import java.time.LocalDate

/**
 * [KalendarEvent] handles the event marked on any
 * @param[date] with specific
 * @param[eventName] and its
 * @param[eventDescription] along with it's
 * @param[eventType]
 */
data class KalendarEvent(
    val date: LocalDate,
    val eventName: String,
    val eventDescription: String? = null,
    val eventType: EventType = EventType.EVENT,
)

sealed class EventType(val type: String) {
    object GOAL : EventType(type = "GOAL")
    object REMINDER : EventType(type = "REMINDER")
    object TASK : EventType(type = "TASK")
    object EVENT : EventType(type = "EVENT")
}

/*
 * Copyright 2023 Kalendar Contributors (https://www.himanshoe.com). All rights reserved.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.himanshoe.kalendar

import androidx.compose.runtime.Immutable
import kotlinx.datetime.LocalDate

/**
 * Represents a calendar event.
 *
 * @param date The date of the event.
 * @param eventName The name or title of the event.
 * @param eventDescription The description or additional details of the event (optional).
 */
@Immutable
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
@Immutable
data class KalendarEvents(
    val events: List<KalendarEvent> = emptyList()
)

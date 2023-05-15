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

package com.himanshoe.kalendar.util

import androidx.compose.runtime.MutableState
import com.himanshoe.kalendar.KalendarEvent
import com.himanshoe.kalendar.ui.firey.DaySelectionMode
import com.himanshoe.kalendar.ui.firey.KalendarSelectedDayRange
import kotlinx.datetime.LocalDate

/**
 * Internal function invoked when a day is clicked.
 *
 * @param date The clicked date.
 * @param events The events associated with the clicked date.
 * @param daySelectionMode The day selection mode.
 * @param selectedRange The state holding the selected day range.
 * @param onRangeSelected Callback invoked when a range of days is selected.
 * @param onDayClick Callback invoked when a day is clicked.
 */
internal fun onDayClicked(
    date: LocalDate,
    events: List<KalendarEvent>,
    daySelectionMode: DaySelectionMode,
    selectedRange: MutableState<KalendarSelectedDayRange?>,
    onRangeSelected: (KalendarSelectedDayRange, List<KalendarEvent>) -> Unit = { _, _ -> },
    onDayClick: (LocalDate, List<KalendarEvent>) -> Unit = { _, _ -> }
) {
    when (daySelectionMode) {
        DaySelectionMode.Single -> {
            onDayClick(date, events)
        }

        DaySelectionMode.Range -> {
            val range = selectedRange.value
            selectedRange.value = when {
                range?.isEmpty() != false -> KalendarSelectedDayRange(start = date, end = date)
                range.isSingleDate() -> KalendarSelectedDayRange(start = range.start, end = date)
                else -> KalendarSelectedDayRange(start = date, end = date)
            }

            selectedRange.value?.let { rangeDates ->
                val selectedEvents = events
                    .filter { it.date in (rangeDates.start..rangeDates.end) }
                    .toList()

                onRangeSelected(rangeDates, selectedEvents)
            }
        }
    }
}

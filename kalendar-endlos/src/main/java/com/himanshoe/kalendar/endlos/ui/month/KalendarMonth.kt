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

package com.himanshoe.kalendar.endlos.ui.month

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastForEach
import com.himanshoe.kalendar.endlos.KalendarDates
import com.himanshoe.kalendar.endlos.daterange.KalendarSelectedDayRange
import com.himanshoe.kalendar.endlos.model.KalendarEvent
import com.himanshoe.kalendar.endlos.model.KalendarEvents
import com.himanshoe.kalendar.endlos.ui.color.KalendarColor
import com.himanshoe.kalendar.endlos.ui.day.KalendarDay
import com.himanshoe.kalendar.endlos.ui.day.KalendarDayKonfig
import com.himanshoe.kalendar.endlos.ui.header.KalendarTextKonfig
import com.himanshoe.kalendar.endlos.ui.header.getTitleText
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.Month
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayIn

/**
 * Renders a single month view of the Kalendar.
 *
 * @param kalendarDates The KalendarDates object containing the dates for the month.
 * @param month The month to be rendered.
 * @param year The year of the month.
 * @param events The KalendarEvents object containing the events for the month.
 * @param kalendarColor The KalendarColor object defining the colors for the Kalendar.
 * @param contentPadding The padding to be applied to the month view content.
 * @param kalendarDayKonfig The KalendarDayKonfig object defining the configuration for the day cells.
 * @param kalendarHeaderTextKonfig The KalendarTextKonfig object defining the configuration for the header text.
 * @param selectedRange The selected day range in the Kalendar.
 * @param modifier The modifier to be applied to the month view.
 * @param selectedDate The currently selected date in the Kalendar.
 * @param dayContent The composable function to render the content of each day cell.
 * @param headerContent The composable function to render the header content of the month view.
 * @param onDayClick The callback function when a day cell is clicked.
 */
@Composable
internal fun KalendarMonth(
    kalendarDates: KalendarDates,
    month: Month,
    year: Int,
    events: KalendarEvents,
    kalendarColor: KalendarColor,
    contentPadding: PaddingValues,
    kalendarDayKonfig: KalendarDayKonfig,
    kalendarHeaderTextKonfig: KalendarTextKonfig?,
    selectedRange: KalendarSelectedDayRange?,
    modifier: Modifier = Modifier,
    selectedDate: LocalDate = Clock.System.todayIn(TimeZone.currentSystemDefault()),
    dayContent: @Composable() ((LocalDate) -> Unit)? = null,
    headerContent: @Composable() ((Month, Int) -> Unit)? = null,
    onDayClick: (LocalDate, List<KalendarEvent>) -> Unit = { _, _ -> },
) {
    val selectedDate = remember { mutableStateOf(selectedDate) }

    Column(
        modifier = modifier
            .padding(contentPadding)
            .background(kalendarColor.backgroundColor)
    ) {
        if (headerContent != null) {
            headerContent(month, year)
        } else {
            kalendarHeaderTextKonfig?.let {
                Text(
                    modifier = Modifier
                        .padding(start = 12.dp, top = 12.dp)
                        .wrapContentHeight()
                        .wrapContentWidth(),
                    color = it.kalendarTextColor,
                    fontSize = it.kalendarTextSize,
                    text = getTitleText(month, year),
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Start
                )
            }
        }

        kalendarDates.dates.fastForEach { week ->
            Row(modifier = Modifier.fillMaxWidth()) {
                week.fastForEach { date ->
                    date?.let { nonNullDate ->
                        if (dayContent != null) {
                            dayContent(nonNullDate)
                        } else {
                            KalendarDay(
                                date = nonNullDate,
                                selectedDate = selectedDate.value,
                                selectedRange = selectedRange,
                                events = events,
                                onDayClick = { date, events ->
                                    selectedDate.value = date
                                    onDayClick(date, events)
                                },
                                kalendarDayKonfig = kalendarDayKonfig,
                                kalendarColor = kalendarColor,
                            )
                        }
                    } ?: run {
                        Box(modifier = Modifier.size(56.dp))
                    }
                }
            }
        }
    }
}

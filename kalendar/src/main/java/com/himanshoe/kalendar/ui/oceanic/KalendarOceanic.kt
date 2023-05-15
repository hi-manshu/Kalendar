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

package com.himanshoe.kalendar.ui.oceanic

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.himanshoe.kalendar.KalendarEvent
import com.himanshoe.kalendar.KalendarEvents
import com.himanshoe.kalendar.color.KalendarColors
import com.himanshoe.kalendar.ui.component.day.KalendarDay
import com.himanshoe.kalendar.ui.component.day.KalendarDayKonfig
import com.himanshoe.kalendar.ui.component.header.KalendarHeader
import com.himanshoe.kalendar.ui.component.header.KalendarTextKonfig
import com.himanshoe.kalendar.ui.firey.DaySelectionMode
import com.himanshoe.kalendar.ui.firey.KalendarSelectedDayRange
import com.himanshoe.kalendar.ui.firey.RangeSelectionError
import com.himanshoe.kalendar.ui.oceanic.util.getNext7Dates
import com.himanshoe.kalendar.ui.oceanic.util.getPrevious7Dates
import com.himanshoe.kalendar.util.MultiplePreviews
import com.himanshoe.kalendar.util.onDayClicked
import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.plus
import kotlinx.datetime.todayIn
import java.time.Month
import java.time.format.TextStyle
import java.util.Locale

/**
 * Creates a composable function for the KalendarOceanic component.
 * @param modifier The modifier for styling or positioning the component.
 * @param daySelectionMode The mode for selecting days in the calendar.
 * @param currentDay The current day to highlight in the calendar.
 * @param showLabel Flag indicating whether to show labels for days of the week.
 * @param kalendarHeaderTextKonfig The configuration for styling the calendar header text.
 * @param kalendarColors The colors to be used in the calendar.
 * @param events The events to be displayed in the calendar.
 * @param labelFormat The format function for generating labels for days of the week.
 * @param kalendarDayKonfig The configuration for styling individual days in the calendar.
 * @param dayContent The content to be displayed for each day in the calendar.
 * @param headerContent The content to be displayed in the calendar header.
 * @param onDayClick The callback function when a day is clicked.
 * @param onRangeSelected The callback function when a range of days is selected.
 * @param onErrorRangeSelected The callback function when there is an error in selecting a range of days.
 */
@Composable
internal fun KalendarOceanic(
    modifier: Modifier = Modifier,
    daySelectionMode: DaySelectionMode = DaySelectionMode.Single,
    currentDay: LocalDate? = null,
    showLabel: Boolean = true,
    kalendarHeaderTextKonfig: KalendarTextKonfig? = null,
    kalendarColors: KalendarColors = KalendarColors.default(),
    events: KalendarEvents = KalendarEvents(),
    labelFormat: (DayOfWeek) -> String = {
        it.getDisplayName(
            TextStyle.SHORT,
            Locale.getDefault()
        )
    },
    kalendarDayKonfig: KalendarDayKonfig = KalendarDayKonfig.default(),
    dayContent: @Composable ((LocalDate) -> Unit)? = null,
    headerContent: @Composable ((Month, Int) -> Unit)? = null,
    onDayClick: (LocalDate, List<KalendarEvent>) -> Unit = { _, _ -> },
    onRangeSelected: (KalendarSelectedDayRange, List<KalendarEvent>) -> Unit = { _, _ -> },
    onErrorRangeSelected: (RangeSelectionError) -> Unit = {}
) {
    val today = currentDay ?: Clock.System.todayIn(TimeZone.currentSystemDefault())
    val weekValue = remember { mutableStateOf(today.getNext7Dates()) }
    val yearAndMonth = getCurrentMonthAndYear(weekValue.value)
    var selectedDate by remember { mutableStateOf(today) }
    val currentMonthIndex = yearAndMonth.first.value.minus(1)
    val selectedRange = remember { mutableStateOf<KalendarSelectedDayRange?>(null) }

    Column(
        modifier = modifier
            .background(
                color = kalendarColors.color[currentMonthIndex].backgroundColor
            )
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(all = 8.dp)
    ) {
        if (headerContent != null) {
            headerContent(yearAndMonth.first, yearAndMonth.second)
        } else {
            KalendarHeader(
                month = yearAndMonth.first,
                year = yearAndMonth.second,
                kalendarTextKonfig = kalendarHeaderTextKonfig ?: KalendarTextKonfig(
                    kalendarTextColor = kalendarColors.color[currentMonthIndex].headerTextColor,
                    kalendarTextSize = 24.sp
                ),
                onPreviousClick = {
                    val firstDayOfDisplayedWeek = weekValue.value.first()
                    weekValue.value = firstDayOfDisplayedWeek.getPrevious7Dates()
                },
                onNextClick = {
                    val lastDayOfDisplayedWeek = weekValue.value.last().plus(1, DateTimeUnit.DAY)
                    weekValue.value = lastDayOfDisplayedWeek.getNext7Dates()
                },
            )
        }
        Spacer(modifier = Modifier.padding(vertical = 4.dp))
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            content = {
                itemsIndexed(weekValue.value) { _, item ->
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        if (showLabel) {
                            Text(
                                modifier = Modifier,
                                color = kalendarDayKonfig.textColor,
                                fontSize = kalendarDayKonfig.textSize,
                                text = labelFormat(item.dayOfWeek),
                                fontWeight = FontWeight.SemiBold,
                                textAlign = TextAlign.Center
                            )
                        }
                        Spacer(modifier = Modifier.padding(vertical = 4.dp))

                        if (dayContent != null) {
                            dayContent(item)
                        } else {
                            KalendarDay(
                                date = item,
                                kalendarColors = kalendarColors.color[currentMonthIndex],
                                onDayClick = { clickedDate, event ->
                                    onDayClicked(
                                        clickedDate,
                                        event,
                                        daySelectionMode,
                                        selectedRange,
                                        onRangeSelected = { range, events ->
                                            if (range.end < range.start) {
                                                onErrorRangeSelected(RangeSelectionError.EndIsBeforeStart)
                                            } else {
                                                onRangeSelected(range, events)
                                            }
                                        },
                                        onDayClick = { newDate, clickedDateEvent ->
                                            selectedDate = newDate
                                            onDayClick(newDate, clickedDateEvent)
                                        }
                                    )
                                },
                                selectedDate = selectedDate,
                                kalendarEvents = events,
                                kalendarDayKonfig = kalendarDayKonfig,
                                selectedRange = selectedRange.value,
                            )
                        }
                    }
                }
            }
        )
    }
}

/**
 * Calculates the current month and year based on a list of dates representing a week.
 * @param weekValue The list of dates representing a week.
 * @return A pair containing the current month and year.
 */
private fun getCurrentMonthAndYear(weekValue: List<LocalDate>): Pair<Month, Int> {
    val month = weekValue.first().month
    val year = weekValue.first().year
    return Pair(month, year)
}

@MultiplePreviews
@Composable
fun KalendarOceanicPreview() {
    KalendarOceanic(
        currentDay = Clock.System.todayIn(
            TimeZone.currentSystemDefault()
        ),
        kalendarHeaderTextKonfig = KalendarTextKonfig.previewDefault(),
        daySelectionMode = DaySelectionMode.Single
    )
}

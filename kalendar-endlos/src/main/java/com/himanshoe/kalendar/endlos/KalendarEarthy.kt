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

package com.himanshoe.kalendar.endlos

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
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
import com.himanshoe.kalendar.endlos.daterange.KalendarSelectedDayRange
import com.himanshoe.kalendar.endlos.model.KalendarEvent
import com.himanshoe.kalendar.endlos.model.KalendarEvents
import com.himanshoe.kalendar.endlos.ui.color.KalendarColors
import com.himanshoe.kalendar.endlos.ui.day.KalendarDay
import com.himanshoe.kalendar.endlos.ui.day.KalendarDayKonfig
import com.himanshoe.kalendar.endlos.ui.header.KalendarHeader
import com.himanshoe.kalendar.endlos.ui.header.KalendarTextKonfig
import com.himanshoe.kalendar.endlos.util.onDayClicked
import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.minus
import kotlinx.datetime.plus
import kotlinx.datetime.todayIn
import java.time.Month
import java.time.format.TextStyle
import java.util.Locale

private const val WEEK_LENGTH = 7
private const val MIN_DAY = 3

/**
 * Composable function that displays a week view calendar using Jetpack Compose.
 *
 * @param modifier The modifier for the root layout of the calendar.
 * @param daySelectionMode The mode for selecting days in the calendar.
 * @param currentDay The current day to be displayed. If null, the system's current date will be used.
 * @param showLabel Whether to show labels for each day of the week.
 * @param kalendarHeaderTextKonfig The configuration for the header text in the calendar.
 * @param kalendarColors The colors used in the calendar.
 * @param events The events to be displayed in the calendar.
 * @param labelFormat The format function for the labels of the days of the week.
 * @param kalendarDayKonfig The configuration for each day in the calendar.
 * @param dayContent The content to be displayed for each day in the calendar.
 * @param headerContent The content to be displayed in the header of the calendar.
 * @param onDayClick The callback function when a day is clicked in the calendar.
 * @param onRangeSelected The callback function when a range of days is selected in the calendar.
 * @param onErrorRangeSelected The callback function when an error occurs during range selection.
 */
@Composable
@ExperimentalFoundationApi
fun KalendarEarthy(
    modifier: Modifier = Modifier,
    daySelectionMode: DaySelectionMode = DaySelectionMode.Range,
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
    val initialPage = remember { mutableStateOf(Int.MAX_VALUE / 2) }
    val weekDays = remember { mutableStateOf(generateDates(0)) }
    val monthOfFirstDay = remember { derivedStateOf { weekDays.value.first().month } }
    val year = remember { derivedStateOf { weekDays.value.first().year } }
    val currentMonthIndex = weekDays.value.first().month.value.minus(1)
    var selectedDate by remember { mutableStateOf(today) }
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
            headerContent(monthOfFirstDay.value, year.value)
        } else {
            KalendarHeader(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                month = monthOfFirstDay.value,
                year = year.value,
                kalendarTextKonfig = kalendarHeaderTextKonfig ?: KalendarTextKonfig(
                    kalendarTextColor = kalendarColors.color[currentMonthIndex].headerTextColor,
                    kalendarTextSize = 24.sp
                ),
                arrowShown = false,
            )
        }

        HorizontalPager(
            pageCount = Int.MAX_VALUE,
            state = rememberPagerState(initialPage = initialPage.value),
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
        ) { pageIndex ->
            val weekIndex = pageIndex.minus(initialPage.value)
            val dates = generateDates(weekIndex)
            weekDays.value = dates

            LazyRow(
                userScrollEnabled = false,
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                content = {
                    itemsIndexed(
                        dates,
                        key = { index, _ ->
                            index
                        }
                    ) { _, date: LocalDate ->
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            if (showLabel) {
                                Text(
                                    modifier = Modifier,
                                    color = kalendarDayKonfig.textColor,
                                    fontSize = kalendarDayKonfig.textSize,
                                    text = labelFormat(date.dayOfWeek),
                                    fontWeight = FontWeight.SemiBold,
                                    textAlign = TextAlign.Center
                                )
                                Spacer(modifier = Modifier.padding(vertical = 4.dp))
                            }

                            if (dayContent != null) {
                                dayContent(date)
                            } else {
                                KalendarDay(
                                    date = date,
                                    kalendarColor = kalendarColors.color[currentMonthIndex],
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
                                    events = events,
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
}

private fun generateDates(currentWeek: Int): List<LocalDate> {
    val currentDate = Clock.System.todayIn(TimeZone.currentSystemDefault())
    val startOfWeek = currentDate.minus(
        (currentDate.dayOfWeek.ordinal - DayOfWeek.MONDAY.ordinal).toLong(),
        DateTimeUnit.DAY
    )
    val weekStartDate = startOfWeek.plus(currentWeek * WEEK_LENGTH.toLong(), DateTimeUnit.DAY)
    return List(WEEK_LENGTH) { index ->
        weekStartDate.plus((index - MIN_DAY).toLong(), DateTimeUnit.DAY)
    }
}

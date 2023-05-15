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
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.himanshoe.kalendar.endlos.daterange.KalendarSelectedDayRange
import com.himanshoe.kalendar.endlos.model.KalendarEvent
import com.himanshoe.kalendar.endlos.model.KalendarEvents
import com.himanshoe.kalendar.endlos.paging.KalendarModelEntity
import com.himanshoe.kalendar.endlos.paging.KalendarPagingController
import com.himanshoe.kalendar.endlos.paging.rememberKalendarPagingController
import com.himanshoe.kalendar.endlos.ui.color.KalendarColors
import com.himanshoe.kalendar.endlos.ui.day.KalendarDayKonfig
import com.himanshoe.kalendar.endlos.ui.header.KalendarTextKonfig
import com.himanshoe.kalendar.endlos.ui.month.KalendarMonth
import com.himanshoe.kalendar.endlos.util.onDayClicked
import kotlinx.datetime.LocalDate
import kotlinx.datetime.Month

private val WeekDays = listOf("M", "T", "W", "T", "F", "S", "S")

/**
 * Displays an endless Kalendar widget that allows selecting and displaying dates.
 *
 * @param modifier The modifier to be applied to the Kalendar.
 * @param showLabel Determines whether to show labels for days of the week.
 * @param pagingController The paging controller for the Kalendar.
 * @param kalendarHeaderTextKonfig The configuration for the Kalendar header text.
 * @param kalendarColors The colors to be used for styling the Kalendar.
 * @param events The events to be displayed in the Kalendar.
 * @param kalendarDayKonfig The configuration for individual days in the Kalendar.
 * @param contentPadding The padding to be applied to the entire Kalendar.
 * @param monthContentPadding The padding to be applied to each month in the Kalendar.
 * @param dayContent The content composable for customizing the display of each day.
 * @param weekValueContent The content composable for customizing the display of the week values.
 * @param headerContent The content composable for customizing the header of each month.
 * @param daySelectionMode The mode for selecting days in the Kalendar.
 * @param onDayClick The callback function to be invoked when a day is clicked.
 * @param onRangeSelected The callback function to be invoked when a range of days is selected.
 * @param onErrorRangeSelected The callback function to handle errors during range selection.
 */
@Composable
@OptIn(ExperimentalFoundationApi::class)
internal fun KalendarEndlos(
    modifier: Modifier = Modifier,
    showLabel: Boolean = true,
    pagingController: KalendarPagingController = rememberKalendarPagingController(),
    kalendarHeaderTextKonfig: KalendarTextKonfig? = null,
    kalendarColors: KalendarColors = KalendarColors.default(),
    events: KalendarEvents = KalendarEvents(),
    kalendarDayKonfig: KalendarDayKonfig = KalendarDayKonfig.default(),
    contentPadding: PaddingValues = PaddingValues(8.dp),
    monthContentPadding: PaddingValues = PaddingValues(4.dp),
    dayContent: (@Composable (LocalDate) -> Unit)? = null,
    weekValueContent: (@Composable () -> Unit)? = null,
    headerContent: (@Composable (Month, Int) -> Unit)? = null,
    daySelectionMode: DaySelectionMode = DaySelectionMode.Range,
    onDayClick: (LocalDate, List<KalendarEvent>) -> Unit = { _, _ -> },
    onRangeSelected: (KalendarSelectedDayRange, List<KalendarEvent>) -> Unit = { _, _ -> },
    onErrorRangeSelected: (RangeSelectionError) -> Unit = {}
) {
    val kalendarItems = pagingController.kalendarItems.collectAsLazyPagingItems()
    val selectedRange = remember { mutableStateOf<KalendarSelectedDayRange?>(null) }

    LazyColumn(
        modifier = modifier.fillMaxWidth(),
        contentPadding = contentPadding,
        content = {
            if (weekValueContent != null) {
                stickyHeader {
                    weekValueContent()
                }
            } else {
                if (showLabel) {
                    stickyHeader {
                        KalendarStickerHeader(
                            kalendarDayKonfig.textColor,
                            kalendarDayKonfig.textSize
                        )
                    }
                }
            }
            items(
                count = kalendarItems.itemCount,
                key = kalendarItems.itemKey(),
                contentType = kalendarItems.itemContentType()
            ) { index ->
                val calendarModel: KalendarModelEntity? = kalendarItems[index]
                val dates: List<List<LocalDate?>>? = calendarModel?.dates?.chunked(7)
                if (dates != null) {
                    val currentMonthIndex = calendarModel.month.value.minus(1)
                    val defaultHeaderColor = KalendarTextKonfig.default(
                        color = kalendarColors.color[currentMonthIndex].headerTextColor
                    )
                    val headerTextKonfig = kalendarHeaderTextKonfig ?: defaultHeaderColor
                    KalendarMonth(
                        kalendarDates = dates.toKalendarDates(),
                        month = calendarModel.month,
                        year = calendarModel.year,
                        selectedRange = selectedRange.value,
                        contentPadding = monthContentPadding,
                        dayContent = dayContent,
                        kalendarDayKonfig = kalendarDayKonfig,
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
                                    onDayClick(newDate, clickedDateEvent)
                                }
                            )
                        },
                        events = events,
                        kalendarHeaderTextKonfig = headerTextKonfig,
                        headerContent = headerContent,
                        kalendarColor = kalendarColors.color[currentMonthIndex],
                    )
                }
            }
        }
    )
}

/**
 * Displays the sticker header for the Kalendar with the specified color and text size.
 *
 * @param color The color to be used for the sticker header.
 * @param textSize The text size to be used for the sticker header.
 */
@Composable
private fun KalendarStickerHeader(color: Color, textSize: TextUnit) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier.padding(start = 4.dp, top = 4.dp, bottom = 4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp)
        ) {
            repeat(WeekDays.size) {
                Text(
                    modifier = Modifier
                        .weight(1F),
                    color = color,
                    fontSize = textSize,
                    text = WeekDays[it],
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

/**
 * Represents a collection of dates for each day in a Kalendar month.
 *
 * @property dates The list of lists containing the dates for each day in a month. Each inner list represents a week
 * and contains nullable LocalDate values for each day of the week.
 */
@Immutable
internal data class KalendarDates(val dates: List<List<LocalDate?>>)

/**
 * Converts a list of lists containing nullable LocalDate values to a [KalendarDates] object.
 *
 * @receiver The source list of lists.
 * @return The converted [KalendarDates] object.
 */
internal fun List<List<LocalDate?>>.toKalendarDates() = KalendarDates(this)

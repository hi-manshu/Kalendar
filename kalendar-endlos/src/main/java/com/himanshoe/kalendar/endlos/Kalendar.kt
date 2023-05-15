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

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.himanshoe.kalendar.endlos.daterange.KalendarSelectedDayRange
import com.himanshoe.kalendar.endlos.model.KalendarEvent
import com.himanshoe.kalendar.endlos.model.KalendarEvents
import com.himanshoe.kalendar.endlos.paging.KalendarPagingController
import com.himanshoe.kalendar.endlos.paging.rememberKalendarPagingController
import com.himanshoe.kalendar.endlos.ui.color.KalendarColors
import com.himanshoe.kalendar.endlos.ui.day.KalendarDayKonfig
import com.himanshoe.kalendar.endlos.ui.header.KalendarTextKonfig
import kotlinx.datetime.LocalDate
import kotlinx.datetime.Month

/**
 * Displays a Kalendar widget that allows selecting and displaying dates.
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
 * @param onDayClicked The callback function to be invoked when a day is clicked.
 * @param onRangeSelected The callback function to be invoked when a range of days is selected.
 * @param onErrorRangeSelected The callback function to handle errors during range selection.
 */
@Composable
fun Kalendar(
    modifier: Modifier = Modifier,
    showLabel: Boolean = true,
    pagingController: KalendarPagingController = rememberKalendarPagingController(),
    kalendarType: KalendarType = KalendarType.Horizontal,
    kalendarHeaderTextKonfig: KalendarTextKonfig? = null,
    kalendarColors: KalendarColors = KalendarColors.default(),
    events: KalendarEvents = KalendarEvents(),
    kalendarDayKonfig: KalendarDayKonfig = KalendarDayKonfig.default(),
    contentPadding: PaddingValues = PaddingValues(8.dp),
    monthContentPadding: PaddingValues = PaddingValues(4.dp),
    dayContent: (@Composable (LocalDate) -> Unit)? = null,
    weekValueContent: (@Composable () -> Unit)? = null,
    headerContent: (@Composable (Month, Int) -> Unit)? = null,
    daySelectionMode: DaySelectionMode = DaySelectionMode.Single,
    onDayClicked: (LocalDate, List<KalendarEvent>) -> Unit = { _, _ -> },
    onRangeSelected: (KalendarSelectedDayRange, List<KalendarEvent>) -> Unit = { _, _ -> },
    onErrorRangeSelected: (RangeSelectionError) -> Unit = {}
) {
    if (kalendarType == KalendarType.Vertical) {
        KalendarEndlos(
            modifier = modifier,
            showLabel = showLabel,
            pagingController = pagingController,
            kalendarHeaderTextKonfig = kalendarHeaderTextKonfig,
            kalendarColors = kalendarColors,
            onDayClick = onDayClicked,
            events = events,
            kalendarDayKonfig = kalendarDayKonfig,
            contentPadding = contentPadding,
            monthContentPadding = monthContentPadding,
            dayContent = dayContent,
            weekValueContent = weekValueContent,
            daySelectionMode = daySelectionMode,
            onRangeSelected = onRangeSelected,
            onErrorRangeSelected = onErrorRangeSelected,
            headerContent = headerContent
        )
    } else {
//        KalendarEarthy()
    }
}

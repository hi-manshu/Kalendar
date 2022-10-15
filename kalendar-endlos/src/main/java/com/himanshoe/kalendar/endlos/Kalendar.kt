/*
 * Copyright 2022 Kalendar Contributors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.himanshoe.kalendar.endlos

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.collectAsLazyPagingItems
import com.himanshoe.kalendar.endlos.color.KalendarColors
import com.himanshoe.kalendar.endlos.color.KalendarThemeColor
import com.himanshoe.kalendar.endlos.component.day.config.KalendarDayColors
import com.himanshoe.kalendar.endlos.component.day.config.KalendarDayDefaultColors
import com.himanshoe.kalendar.endlos.model.KalendarDay
import com.himanshoe.kalendar.endlos.model.KalendarEvent
import com.himanshoe.kalendar.endlos.paging.rememberKalendarPagingController

@Composable
fun Kalendar(
    modifier: Modifier = Modifier,
    kalendarEvents: List<KalendarEvent> = emptyList(),
    kalendarThemeColors: List<KalendarThemeColor> = KalendarColors.defaultColors(),
    onCurrentDayClick: (KalendarDay, List<KalendarEvent>) -> Unit = { _, _ -> },
    kalendarDayColors: KalendarDayColors = KalendarDayDefaultColors.defaultColors(),
) {
    val kalendarPagingController = rememberKalendarPagingController()
    KalendarEndloss(
        modifier = modifier,
        kalendarItems = kalendarPagingController.dates.collectAsLazyPagingItems(),
        kalendarEvents = kalendarEvents,
        onCurrentDayClick = onCurrentDayClick,
        kalendarDayColors = kalendarDayColors,
        kalendarThemeColors = kalendarThemeColors,
    )
}

@Composable
fun Kalendar(
    modifier: Modifier = Modifier,
    kalendarThemeColor: KalendarThemeColor,
    kalendarEvents: List<KalendarEvent> = emptyList(),
    onCurrentDayClick: (KalendarDay, List<KalendarEvent>) -> Unit = { _, _ -> },
    kalendarDayColors: KalendarDayColors = KalendarDayDefaultColors.defaultColors(),
) {
    val kalendarPagingController = rememberKalendarPagingController()
    KalendarEndloss(
        modifier = modifier,
        kalendarItems = kalendarPagingController.dates.collectAsLazyPagingItems(),
        kalendarEvents = kalendarEvents,
        onCurrentDayClick = onCurrentDayClick,
        kalendarDayColors = kalendarDayColors,
        kalendarThemeColors = listOf(kalendarThemeColor),
    )
}

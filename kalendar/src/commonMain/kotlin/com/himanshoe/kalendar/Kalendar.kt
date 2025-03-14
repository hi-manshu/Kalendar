/*
 *
 *  * Copyright 2025 Kalendar Contributors (https://www.himanshoe.com). All rights reserved.
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  * http://www.apache.org/licenses/LICENSE-2.0
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *  *
 *
 */

package com.himanshoe.kalendar

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.himanshoe.kalendar.foundation.action.OnDaySelectionAction
import com.himanshoe.kalendar.foundation.component.config.KalendarKonfig
import com.himanshoe.kalendar.foundation.event.KalendarEvents
import kotlinx.datetime.Clock
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayIn

@Composable
fun Kalendar(
    kalendarType: KalendarType,
    modifier: Modifier = Modifier,
    selectedDate: LocalDate = Clock.System.todayIn(TimeZone.currentSystemDefault()),
    events: KalendarEvents = KalendarEvents(),
    showDayLabel: Boolean = true,
    arrowShown: Boolean = true,
    onDaySelectionAction: OnDaySelectionAction = OnDaySelectionAction.Single { _, _ -> },
    kalendarKonfig: KalendarKonfig = KalendarKonfig(),
    restrictToCurrentWeekOrMonth: Boolean = false,
    startDayOfWeek: DayOfWeek = DayOfWeek.SUNDAY,
) {
    when (kalendarType) {
        KalendarType.Oceanic -> {
            KalendarOceanic(
                selectedDate = selectedDate,
                modifier = modifier,
                arrowShown = arrowShown,
                showDayLabel = showDayLabel,
                onDaySelectionAction = onDaySelectionAction,
                kalendarKonfig = kalendarKonfig,
                events = events,
                startDayOfWeek = startDayOfWeek,
                restrictToCurrentMonth = restrictToCurrentWeekOrMonth,
            )
        }

        KalendarType.Firey -> {
            KalendarFirey(
                modifier = modifier,
                selectedDate = selectedDate,
                events = events,
                startDayOfWeek = startDayOfWeek,
                showDayLabel = showDayLabel,
                arrowShown = arrowShown,
                onDaySelectionAction = onDaySelectionAction,
                kalendarKonfig = kalendarKonfig,
                restrictToCurrentWeek = restrictToCurrentWeekOrMonth,
            )
        }

        KalendarType.Aerial -> {
            KalendarAerial(
                selectedDate = selectedDate,
                modifier = modifier,
                showDayLabel = showDayLabel,
                onDaySelectionAction = onDaySelectionAction,
                kalendarKonfig = kalendarKonfig,
                events = events,
                startDayOfWeek = startDayOfWeek,
            )
        }

        KalendarType.Solaris -> {
            KalendarSolaris(
                modifier = modifier,
                selectedDate = selectedDate,
                events = events,
                startDayOfWeek = startDayOfWeek,
                showDayLabel = showDayLabel,
                onDaySelectionAction = onDaySelectionAction,
                kalendarKonfig = kalendarKonfig,
            )
        }
    }
}

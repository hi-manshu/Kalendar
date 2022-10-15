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
package com.himanshoe.sample.demo

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.himanshoe.kalendar.endlos.Kalendar
import com.himanshoe.kalendar.endlos.model.KalendarEvent
import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.plus
import kotlinx.datetime.todayIn

@Composable
fun KalendarEndlossDemo() {
    val oneWeekFromNow =
        Clock.System.todayIn(TimeZone.currentSystemDefault()).plus(1, DateTimeUnit.WEEK)

    Kalendar(
        Modifier.background(Color.White),
        kalendarEvents = listOf(
            KalendarEvent(LocalDate(2022, 10, 25), "Birthday"),
            KalendarEvent(LocalDate(2022, 10, 25), "Birthday"),
            KalendarEvent(LocalDate(2022, 10, 25), "Birthday"),
            KalendarEvent(LocalDate(2022, 10, 28), "Anniversary"),
            KalendarEvent(LocalDate(2022, 10, 28), "Party"),
            KalendarEvent(LocalDate(2022, 10, 29), "Club"),
        )
    )
}

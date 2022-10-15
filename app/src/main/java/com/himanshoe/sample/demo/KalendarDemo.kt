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
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.himanshoe.kalendar.Kalendar
import com.himanshoe.kalendar.model.KalendarEvent
import com.himanshoe.kalendar.model.KalendarType
import kotlinx.datetime.LocalDate

@Composable
fun KalendarDemo() {

    Column(
        Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Kalendar(
            kalendarType = KalendarType.Oceanic(false),
            kalendarEvents = listOf(
                KalendarEvent(LocalDate(2022, 10, 25), "Birthday"),
                KalendarEvent(LocalDate(2022, 10, 25), "Birthday"),
                KalendarEvent(LocalDate(2022, 10, 25), "Birthday"),
                KalendarEvent(LocalDate(2022, 10, 28), "Anniversary"),
                KalendarEvent(LocalDate(2022, 10, 28), "Party"),
                KalendarEvent(LocalDate(2022, 10, 29), "Club"),
            )
        )
        Text(
            text = "Kalendar Oceanic Type",
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
        )
        Kalendar(
            kalendarType = KalendarType.Firey,
            modifier = Modifier.padding(16.dp),
            kalendarEvents = listOf(
                KalendarEvent(LocalDate(2022, 10, 25), "Birthday"),
                KalendarEvent(LocalDate(2022, 10, 25), "Birthday"),
                KalendarEvent(LocalDate(2022, 10, 25), "Birthday"),
                KalendarEvent(LocalDate(2022, 10, 25), "Birthday"),
                KalendarEvent(LocalDate(2022, 10, 28), "Party"),
                KalendarEvent(LocalDate(2022, 10, 29), "Club"),
            )
        )
        Text(
            text = "Kalendar Firey Type",
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
        )
    }
}

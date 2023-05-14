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

package com.himanshoe.kalendar.ui.component.day

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastForEachIndexed
import com.himanshoe.kalendar.KalendarEvent
import com.himanshoe.kalendar.KalendarEvents
import com.himanshoe.kalendar.color.KalendarColor
import com.himanshoe.kalendar.ui.component.day.modifier.circleLayout
import com.himanshoe.kalendar.ui.component.day.modifier.dayBackgroundColor
import com.himanshoe.kalendar.ui.component.indicator.KalendarIndicator
import com.himanshoe.kalendar.ui.firey.KalendarSelectedDayRange
import com.himanshoe.kalendar.util.MultiplePreviews
import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.minus
import kotlinx.datetime.plus
import kotlinx.datetime.todayIn

/**
 * A composable representing a single day in the Kalendar.
 *
 * @param date The date corresponding to the day.
 * @param kalendarColors The colors used for styling the Kalendar.
 * @param onDayClick The callback function invoked when the day is clicked.
 * @param selectedRange The selected date range in the Kalendar.
 * @param modifier The modifier to be applied to the composable.
 * @param selectedDate The currently selected date.
 * @param kalendarEvents The events associated with the Kalendar.
 * @param kalendarDayKonfig The configuration for the Kalendar day.
 */
@Composable
fun KalendarDay(
    date: LocalDate,
    kalendarColors: KalendarColor,
    onDayClick: (LocalDate, List<KalendarEvent>) -> Unit,
    selectedRange: KalendarSelectedDayRange?,
    modifier: Modifier = Modifier,
    selectedDate: LocalDate = date,
    kalendarEvents: KalendarEvents = KalendarEvents(),
    kalendarDayKonfig: KalendarDayKonfig = KalendarDayKonfig.default(),
) {
    val selected = selectedDate == date
    val currentDay = Clock.System.todayIn(TimeZone.currentSystemDefault()) == date

    Column(
        modifier = modifier
            .border(
                border = getBorder(currentDay, kalendarDayKonfig.borderColor, selected),
                shape = CircleShape
            )
            .clip(shape = CircleShape)
            .clickable { onDayClick(date, kalendarEvents.events) }
            .dayBackgroundColor(
                selected,
                kalendarColors.dayBackgroundColor,
                date,
                selectedRange
            )
            .circleLayout()
            .wrapContentSize()
            .defaultMinSize(kalendarDayKonfig.size),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = date.dayOfMonth.toString(),
            modifier = Modifier.wrapContentSize(),
            textAlign = TextAlign.Center,
            fontSize = kalendarDayKonfig.textSize,
            color = if (selected) kalendarDayKonfig.selectedTextColor else kalendarDayKonfig.textColor,
            fontWeight = if (selected) FontWeight.Bold else FontWeight.SemiBold
        )
        Row {
            kalendarEvents.events
                .filter { it.date == date }
                .take(3)
                .fastForEachIndexed { index, _ ->
                    Row {
                        KalendarIndicator(
                            modifier = Modifier,
                            index = index,
                            size = kalendarDayKonfig.size,
                            color = kalendarColors.headerTextColor
                        )
                    }
                }
        }
    }
}

/**
 * Returns the border stroke based on the current day, color, and selected state.
 *
 * @param currentDay Whether the day is the current day.
 * @param color The color of the border.
 * @param selected Whether the day is selected.
 *
 * @return The border stroke to be applied.
 */
private fun getBorder(currentDay: Boolean, color: Color, selected: Boolean): BorderStroke {
    val emptyBorder = BorderStroke(0.dp, Color.Transparent)
    return if (currentDay && selected.not()) {
        BorderStroke(1.dp, color)
    } else {
        emptyBorder
    }
}

@MultiplePreviews
@Composable
private fun KalendarDayPreview() {
    val date = Clock.System.todayIn(TimeZone.currentSystemDefault())
    val previous =
        Clock.System.todayIn(TimeZone.currentSystemDefault()).minus(1, DateTimeUnit.DAY)
    val events = (0..5).map {
        KalendarEvent(
            date = date,
            eventName = it.toString(),
        )
    }
    Row {
        KalendarDay(
            date = date,
            kalendarColors = KalendarColor.previewDefault(),
            onDayClick = { _, _ -> },
            selectedDate = previous,
            kalendarEvents = KalendarEvents(events),
            selectedRange = null
        )
        Spacer(modifier = Modifier.padding(vertical = 8.dp))
        KalendarDay(
            date = date.plus(1, DateTimeUnit.DAY),
            kalendarColors = KalendarColor.previewDefault(),
            onDayClick = { _, _ -> },
            selectedDate = previous,
            kalendarEvents = KalendarEvents(events),
            selectedRange = null
        )
        Spacer(modifier = Modifier.padding(vertical = 8.dp))
        KalendarDay(
            date = date,
            kalendarColors = KalendarColor.previewDefault(),
            onDayClick = { _, _ -> },
            selectedDate = previous,
            kalendarEvents = KalendarEvents(events),
            selectedRange = null
        )
    }
}

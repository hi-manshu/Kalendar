package com.himanshoe.kalendar.ui.oceanic
/*
* MIT License
*
* Copyright (c) 2022 Himanshu Singh
*
* Permission is hereby granted, free of charge, to any person obtaining a copy
* of this software and associated documentation files (the "Software"), to deal
* in the Software without restriction, including without limitation the rights
* to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
* copies of the Software, and to permit persons to whom the Software is
* furnished to do so, subject to the following conditions:
*
* The above copyright notice and this permission notice shall be included in all
* copies or substantial portions of the Software.
*
* THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
* IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
* FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
* AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
* LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
* OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
* SOFTWARE.
*/

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.himanshoe.kalendar.common.KalendarKonfig
import com.himanshoe.kalendar.common.KalendarSelector
import com.himanshoe.kalendar.common.data.KalendarEvent
import com.himanshoe.kalendar.common.theme.KalendarShape
import com.himanshoe.kalendar.common.ui.KalendarDot
import java.time.LocalDate
import java.time.format.TextStyle

@Composable
internal fun KalendarOceanWeek(
    startDate: LocalDate = LocalDate.now(),
    selectedDay: LocalDate = startDate,
    kalendarKonfig: KalendarKonfig,
    onCurrentDayClick: (LocalDate, KalendarEvent?) -> Unit,
    errorMessageLogged: (String) -> Unit,
    kalendarSelector: KalendarSelector,
    kalendarEvents: List<KalendarEvent>,
) {
    val isDot = kalendarSelector is KalendarSelector.Dot
    val haptic = LocalHapticFeedback.current
    val displayWeek = remember {
        mutableStateOf(startDate.getNext7Dates())
    }
    val clickedDate = remember {
        mutableStateOf(selectedDay)
    }

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        val size = (maxWidth / 7)
        val monthName = "${
        displayWeek.value.last().month.getDisplayName(
            TextStyle.FULL,
            kalendarKonfig.locale
        )
        } ${displayWeek.value.last().year}"
        Column(Modifier.fillMaxWidth()) {
            KalendarOceanHeader(
                monthName,
                displayWeek,
                haptic,
                kalendarKonfig.yearRange,
                errorMessageLogged
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                displayWeek.value.forEach { date ->
                    val isSelected = date == clickedDate.value

                    Column(
                        modifier = Modifier,
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        val event: KalendarEvent? = kalendarEvents.find { it.date == date }
                        Text(
                            text = date.dayOfWeek
                                .getDisplayName(TextStyle.FULL, kalendarKonfig.locale).toString()
                                .subSequence(0, kalendarKonfig.weekCharacters).toString(),
                            style = MaterialTheme.typography.body1,
                            modifier = Modifier
                                .width(size)
                                .wrapContentHeight(),
                            textAlign = TextAlign.Center
                        )
                        Text(
                            text = date.dayOfMonth.toString(),
                            style = MaterialTheme.typography.body1,
                            fontWeight = FontWeight.SemiBold,
                            color = getTextColor(isSelected, kalendarSelector, event != null),
                            modifier = Modifier
                                .size(size)
                                .clip(if (isDot) KalendarShape.DefaultRectangle else kalendarSelector.shape)
                                .background(
                                    if (!isDot) {
                                        getBackgroundColor(
                                            kalendarSelector = kalendarSelector,
                                            selectedDate = clickedDate.value,
                                            providedDate = date
                                        )
                                    } else Color.Transparent
                                )
                                .clickable {
                                    haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                                    clickedDate.value = date
                                    onCurrentDayClick(date, event)
                                }
                                .wrapContentHeight(),
                            textAlign = TextAlign.Center
                        )
                        if (isDot) {
                            KalendarDot(
                                kalendarSelector = kalendarSelector,
                                isSelected = isSelected,
                                isToday = date == LocalDate.now()
                            )
                        }
                    }
                }
            }
        }
    }
}

private fun getBackgroundColor(
    selectedDate: LocalDate,
    providedDate: LocalDate,
    kalendarSelector: KalendarSelector,
): Color {
    return if (providedDate == LocalDate.now()) {
        when (selectedDate) {
            providedDate -> kalendarSelector.selectedColor
            else -> kalendarSelector.todayColor
        }
    } else {
        when (selectedDate) {
            providedDate -> kalendarSelector.selectedColor
            else -> kalendarSelector.defaultColor
        }
    }
}

private fun getTextColor(
    isSelected: Boolean,
    kalendarSelector: KalendarSelector,
    isEvent: Boolean,
): Color {
    return when {
        isEvent -> kalendarSelector.eventTextColor
        else -> when {
            isSelected -> kalendarSelector.selectedTextColor
            else -> kalendarSelector.defaultTextColor
        }
    }
}

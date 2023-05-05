package com.himanshoe.kalendar.ui.firey

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.himanshoe.kalendar.KalendarEvent
import com.himanshoe.kalendar.KalendarEvents
import com.himanshoe.kalendar.color.KalendarColors
import com.himanshoe.kalendar.ui.component.day.KalendarDay
import com.himanshoe.kalendar.ui.component.day.KalendarDayColors
import com.himanshoe.kalendar.ui.component.day.KalendarDayKonfig
import com.himanshoe.kalendar.ui.component.header.KalendarHeader
import com.himanshoe.kalendar.ui.component.header.KalendarTextConfig
import com.himanshoe.kalendar.ui.oceanic.util.getNext7Dates
import com.himanshoe.kalendar.ui.oceanic.util.getPrevious7Dates
import com.himanshoe.kalendar.util.MultiplePreview
import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.plus
import kotlinx.datetime.toLocalDate
import kotlinx.datetime.todayIn
import java.time.Month

val WeekDays = listOf("M", "T", "W", "T", "F", "S", "S")

@Composable
fun KalendarFirey(
    currentDay: LocalDate?,
    modifier: Modifier = Modifier,
    showLabel: Boolean = true,
    kalendarHeaderTextConfig: KalendarTextConfig? = null,
    kalendarColors: KalendarColors = KalendarColors.default(),
    onDayClick: (LocalDate, List<KalendarEvent>) -> Unit = { _, _ -> },
    events: KalendarEvents = KalendarEvents(),
    kalendarDayKonfig: KalendarDayKonfig = KalendarDayKonfig.default(),
    kalendarDayColors: KalendarDayColors = KalendarDayColors.default(),
    dayContent: (@Composable () -> Unit)? = null,
    dayLabelContent: (@Composable (String) -> Unit)? = null,
    headerContent: (@Composable () -> Unit)? = null,
) {
    val today = currentDay ?: Clock.System.todayIn(TimeZone.currentSystemDefault())
    val selectedDate = remember { mutableStateOf(today) }
    val displayedMonth = remember { mutableStateOf(today.month) }
    val displayedYear = remember { mutableStateOf(today.year) }
    val currentMonth = displayedMonth.value
    val currentYear = displayedYear.value
    val currentMonthIndex = currentMonth.value.minus(1)

    val newHeaderTextKonfig = kalendarHeaderTextConfig ?: KalendarTextConfig(
        kalendarTextColor = kalendarColors.color[currentMonthIndex].headerTextColor,
        kalendarTextSize = 24.sp
    )

    val daysInMonth = currentMonth.minLength()
    val monthValue =
        if (currentMonth.value.toString().length == 1) "0" + currentMonth.value.toString() else currentMonth.value.toString()
    val startDayOfMonth = "$currentYear-$monthValue-01".toLocalDate()
    val firstDayOfMonth = startDayOfMonth.dayOfWeek

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
            headerContent()
        } else {
            KalendarHeader(
                month = displayedMonth.value,
                year = displayedYear.value,
                kalendarTextConfig = newHeaderTextKonfig,
                onPreviousClick = {
                    if (displayedMonth.value.value == 1) {
                        displayedYear.value = displayedYear.value.minus(1)
                    }
                    displayedMonth.value = displayedMonth.value.minus(1)
                },
                onNextClick = {
                    if (displayedMonth.value.value == 12) {
                        displayedYear.value = displayedYear.value.plus(1)
                    }
                    displayedMonth.value = displayedMonth.value.plus(1)
                },
            )
        }
        Spacer(modifier = Modifier.padding(vertical = 4.dp))
        LazyVerticalGrid(
            modifier = Modifier.fillMaxWidth(),
            columns = GridCells.Fixed(7),
            content = {
                if (showLabel) {
                    itemsIndexed(WeekDays) { index, item ->
                        if (dayLabelContent != null) {
                            dayLabelContent(item)
                        } else {
                            Text(
                                modifier = Modifier,
                                color = kalendarDayColors.textColor,
                                fontSize = kalendarDayKonfig.textSize,
                                text = item,
                                fontWeight = FontWeight.SemiBold,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }

                items((getFirstDayOfMonth(firstDayOfMonth)..daysInMonth).toList()) {
                    if (it > 0) {
                        val day = calculateDay(it, currentMonth, currentYear)
                        if (dayContent != null) {
                            dayContent()
                        } else {
                            KalendarDay(
                                date = day,
                                selectedDate = selectedDate.value,
                                kalendarColors = kalendarColors.color[currentMonthIndex],
                                kalendarEvents = events,
                                kalendarDayColors = kalendarDayColors,
                                kalendarDayKonfig = kalendarDayKonfig,
                                onDayClick = { date, event ->
                                    selectedDate.value = date
                                    onDayClick(date, event)
                                }
                            )
                        }
                    }
                }
            }
        )
    }
}


private fun getFirstDayOfMonth(firstDayOfMonth: DayOfWeek) = -(firstDayOfMonth.value).minus(2)

private fun calculateDay(day: Int, currentMonth: Month, currentYear: Int): LocalDate {
    val monthValue =
        if (currentMonth.value.toString().length == 1) "0${currentMonth.value}" else currentMonth.value.toString()
    val newDay = if (day.toString().length == 1) "0$day" else day
    return "$currentYear-$monthValue-$newDay".toLocalDate()
}

@MultiplePreview
@Composable
fun KalendarFireyPreview() {
    KalendarFirey(
        currentDay = Clock.System.todayIn(
            TimeZone.currentSystemDefault()
        ), kalendarHeaderTextConfig = KalendarTextConfig.previewDefault()
    )
}
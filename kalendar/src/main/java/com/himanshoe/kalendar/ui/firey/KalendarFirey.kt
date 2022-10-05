package com.himanshoe.kalendar.ui.firey

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.himanshoe.kalendar.color.KalendarThemeColor
import com.himanshoe.kalendar.component.KalendarHeader
import com.himanshoe.kalendar.component.day.KalendarDay
import com.himanshoe.kalendar.component.day.config.KalendarDayColors
import com.himanshoe.kalendar.component.text.KalendarNormalText
import com.himanshoe.kalendar.model.KalendarDay
import com.himanshoe.kalendar.model.KalendarEvent
import com.himanshoe.kalendar.model.toKalendarDay
import kotlinx.datetime.Clock
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalDate
import kotlinx.datetime.Month
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDate
import kotlinx.datetime.todayIn

val WeekDays = listOf("M", "T", "W", "T", "F", "S", "S")

@Composable
fun KalendarFirey(
    modifier: Modifier = Modifier,
    kalendarEvents: List<KalendarEvent> = emptyList(),
    onCurrentDayClick: (KalendarDay, List<KalendarEvent>) -> Unit = { _, _ -> },
    takeMeToDate: LocalDate?,
    kalendarDayColors: KalendarDayColors,
    kalendarThemeColors: List<KalendarThemeColor>
) {
    val currentDay = takeMeToDate ?: Clock.System.todayIn(TimeZone.currentSystemDefault())
    val displayedMonth = remember {
        mutableStateOf(currentDay.month)
    }
    val displayedYear = remember {
        mutableStateOf(currentDay.year)
    }
    val currentMonth = displayedMonth.value
    val currentYear = displayedYear.value

    val daysInMonth = currentMonth.minLength()
    val monthValue = if (currentMonth.value.toString().length == 1) "0" + currentMonth.value.toString() else currentMonth.value.toString()
    val startDayOfMonth = "$currentYear-$monthValue-01".toLocalDate()
    val firstDayOfMonth = startDayOfMonth.dayOfWeek
    val selectedKalendarDate = remember { mutableStateOf(currentDay) }

    Column(
        modifier = modifier
            .background(
                color = kalendarThemeColors[currentMonth.value.minus(1)].backgroundColor
            )
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(vertical = 16.dp, horizontal = 8.dp)
    ) {
        KalendarHeader(
            modifier = Modifier.padding(vertical = 12.dp),
            month = displayedMonth.value,
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
            year = displayedYear.value,
            textColor = kalendarThemeColors[currentMonth.value.minus(1)].headerTextColor,
        )
        LazyVerticalGrid(
            modifier = Modifier.fillMaxWidth(),
            columns = GridCells.Fixed(7),
            content = {
                items(WeekDays) {
                    KalendarNormalText(
                        text = it,
                        fontWeight = FontWeight.Normal,
                        color = kalendarDayColors.textColor,
                    )
                }
                items((getInitialDayOfMonth(firstDayOfMonth)..daysInMonth).toList()) {
                    if (it > 0) {
                        val day = getGeneratedDay(it, currentMonth, currentYear)
                        val isCurrentDay = day == currentDay
                        KalendarDay(
                            kalendarDay = day.toKalendarDay(),
                            modifier = Modifier,
                            kalendarEvents = kalendarEvents,
                            isCurrentDay = isCurrentDay,
                            onCurrentDayClick = { kalendarDay, events ->
                                selectedKalendarDate.value = kalendarDay.localDate
                                onCurrentDayClick(kalendarDay, events)
                            },
                            selectedKalendarDay = selectedKalendarDate.value,
                            kalendarDayColors = kalendarDayColors,
                            dotColor = kalendarThemeColors[currentMonth.value.minus(1)].headerTextColor,
                            dayBackgroundColor = kalendarThemeColors[currentMonth.value.minus(1)].dayBackgroundColor,
                        )
                    }
                }
            }
        )
    }
}

@Composable
fun KalendarFirey(
    modifier: Modifier = Modifier,
    kalendarEvents: List<KalendarEvent> = emptyList(),
    onCurrentDayClick: (KalendarDay, List<KalendarEvent>) -> Unit = { _, _ -> },
    takeMeToDate: LocalDate?,
    kalendarDayColors: KalendarDayColors,
    kalendarThemeColor: KalendarThemeColor
) {
    val currentDay = takeMeToDate ?: Clock.System.todayIn(TimeZone.currentSystemDefault())
    val displayedMonth = remember {
        mutableStateOf(currentDay.month)
    }
    val displayedYear = remember {
        mutableStateOf(currentDay.year)
    }
    val currentMonth = displayedMonth.value
    val currentYear = displayedYear.value

    val daysInMonth = currentMonth.minLength()
    val monthValue = if (currentMonth.value.toString().length == 1) "0" + currentMonth.value.toString() else currentMonth.value.toString()
    val startDayOfMonth = "$currentYear-$monthValue-01".toLocalDate()
    val firstDayOfMonth = startDayOfMonth.dayOfWeek
    val selectedKalendarDate = remember { mutableStateOf(currentDay) }

    Column(
        modifier = modifier
            .background(
                color = kalendarThemeColor.backgroundColor
            )
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(vertical = 16.dp, horizontal = 8.dp)
    ) {
        KalendarHeader(
            modifier = Modifier.padding(vertical = 12.dp),
            month = displayedMonth.value,
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
            year = displayedYear.value,
            textColor = kalendarThemeColor.headerTextColor,
        )

        LazyVerticalGrid(
            modifier = Modifier.fillMaxWidth(),
            columns = GridCells.Fixed(7),
            content = {
                items(WeekDays) {
                    KalendarNormalText(
                        text = it,
                        fontWeight = FontWeight.Normal,
                        color = kalendarDayColors.textColor,
                    )
                }
                items((getInitialDayOfMonth(firstDayOfMonth)..daysInMonth).toList()) {
                    if (it > 0) {
                        val day = getGeneratedDay(it, currentMonth, currentYear)
                        val isCurrentDay = day == currentDay
                        KalendarDay(
                            kalendarDay = day.toKalendarDay(),
                            modifier = Modifier,
                            kalendarEvents = kalendarEvents,
                            isCurrentDay = isCurrentDay,
                            onCurrentDayClick = { kalendarDay, events ->
                                selectedKalendarDate.value = kalendarDay.localDate
                                onCurrentDayClick(kalendarDay, events)
                            },
                            selectedKalendarDay = selectedKalendarDate.value,
                            kalendarDayColors = kalendarDayColors,
                            dotColor = kalendarThemeColor.headerTextColor,
                            dayBackgroundColor = kalendarThemeColor.dayBackgroundColor,
                        )
                    }
                }
            }
        )
    }
}

private fun getInitialDayOfMonth(firstDayOfMonth: DayOfWeek) = -(firstDayOfMonth.value).minus(2)

private fun getGeneratedDay(day: Int, currentMonth: Month, currentYear: Int): LocalDate {
    val monthValue =
        if (currentMonth.value.toString().length == 1) "0${currentMonth.value}" else currentMonth.value.toString()
    val newDay = if (day.toString().length == 1) "0$day" else day
    return "$currentYear-$monthValue-$newDay".toLocalDate()
}

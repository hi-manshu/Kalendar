package com.himanshoe.kalendar.ui.oceanic

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.himanshoe.kalendar.color.KalendarThemeColor
import com.himanshoe.kalendar.component.KalendarHeader
import com.himanshoe.kalendar.component.day.KalendarDay
import com.himanshoe.kalendar.component.day.config.KalendarDayColors
import com.himanshoe.kalendar.model.KalendarDay
import com.himanshoe.kalendar.model.KalendarEvent
import com.himanshoe.kalendar.model.toKalendarDay
import com.himanshoe.kalendar.ui.oceanic.data.getNext7Dates
import com.himanshoe.kalendar.ui.oceanic.data.getPrevious7Dates
import kotlinx.datetime.*

@Composable
fun KalendarOceanic(
    modifier: Modifier = Modifier,
    kalendarEvents: List<KalendarEvent> = emptyList(),
    onCurrentDayClick: (KalendarDay, List<KalendarEvent>) -> Unit = { _, _ -> },
    takeMeToDate: LocalDate?,
    kalendarDayColors: KalendarDayColors,
    kalendarThemeColors: List<KalendarThemeColor>,
) {
    val currentDay = takeMeToDate ?: Clock.System.todayIn(TimeZone.currentSystemDefault())
    val weekValue = remember { mutableStateOf(currentDay.getNext7Dates()) }
    val month = weekValue.value.first().month
    val year = weekValue.value.first().year
    val selectedKalendarDate = remember { mutableStateOf(currentDay) }

    Column(
        modifier = modifier
            .background(color = kalendarThemeColors[month.value.minus(1)].backgroundColor)
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(all = 8.dp)
    ) {
        KalendarHeader(
            modifier = Modifier.fillMaxWidth(),
            month = month,
            year = year,
            onPreviousClick = {
                val firstDayOfDisplayedWeek = weekValue.value.first()
                weekValue.value = firstDayOfDisplayedWeek.getPrevious7Dates()
            },
            onNextClick = {
                val lastDayOfDisplayedWeek = weekValue.value.last().plus(1, DateTimeUnit.DAY)
                weekValue.value = lastDayOfDisplayedWeek.getNext7Dates()
            },
            textColor = kalendarThemeColors[month.value.minus(1)].headerTextColor,
        )
        Row(modifier = Modifier.wrapContentWidth()) {
            weekValue.value.forEach { localDate ->
                val isCurrentDay = localDate == currentDay

                KalendarDay(
                    modifier = Modifier,
                    isCurrentDay = isCurrentDay,
                    kalendarDay = localDate.toKalendarDay(),
                    kalendarEvents = kalendarEvents,
                    onCurrentDayClick = { kalendarDay, events ->
                        selectedKalendarDate.value = kalendarDay.localDate
                        onCurrentDayClick(kalendarDay, events)
                    },
                    kalendarDayColors = kalendarDayColors,
                    selectedKalendarDay = selectedKalendarDate.value,
                    dotColor = kalendarThemeColors[month.value.minus(1)].headerTextColor,
                    dayBackgroundColor = kalendarThemeColors[month.value.minus(1)].dayBackgroundColor
                )
            }
        }
    }
}

@Composable
fun KalendarOceanic(
    modifier: Modifier = Modifier,
    kalendarEvents: List<KalendarEvent> = emptyList(),
    onCurrentDayClick: (KalendarDay, List<KalendarEvent>) -> Unit = { _, _ -> },
    takeMeToDate: LocalDate?,
    kalendarDayColors: KalendarDayColors,
    kalendarThemeColor: KalendarThemeColor,
) {
    val currentDay = takeMeToDate ?: Clock.System.todayIn(TimeZone.currentSystemDefault())
    val weekValue = remember { mutableStateOf(currentDay.getNext7Dates()) }
    val month = weekValue.value.first().month
    val year = weekValue.value.first().year
    val selectedKalendarDate = remember { mutableStateOf(currentDay) }

    Column(
        modifier = modifier
            .background(color = kalendarThemeColor.backgroundColor)
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(all = 8.dp)
    ) {
        KalendarHeader(
            modifier = Modifier.fillMaxWidth(),
            month = month,
            year = year,
            onPreviousClick = {
                val firstDayOfDisplayedWeek = weekValue.value.first()
                weekValue.value = firstDayOfDisplayedWeek.getPrevious7Dates()
            },
            onNextClick = {
                val lastDayOfDisplayedWeek = weekValue.value.last().plus(1, DateTimeUnit.DAY)
                weekValue.value = lastDayOfDisplayedWeek.getNext7Dates()
            },
            textColor = kalendarThemeColor.headerTextColor,
        )
        Row(modifier = Modifier.wrapContentWidth()) {
            weekValue.value.forEach { localDate ->
                val isCurrentDay = localDate == currentDay

                KalendarDay(
                    kalendarDay = localDate.toKalendarDay(),
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
}

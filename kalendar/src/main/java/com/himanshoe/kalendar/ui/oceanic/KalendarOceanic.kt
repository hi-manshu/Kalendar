package com.himanshoe.kalendar.ui.oceanic

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.himanshoe.kalendar.color.KalendarThemeColor
import com.himanshoe.kalendar.component.day.KalendarDay
import com.himanshoe.kalendar.component.day.config.KalendarDayColors
import com.himanshoe.kalendar.component.header.KalendarHeader
import com.himanshoe.kalendar.component.header.config.KalendarHeaderConfig
import com.himanshoe.kalendar.component.text.KalendarNormalText
import com.himanshoe.kalendar.component.text.config.KalendarTextColor
import com.himanshoe.kalendar.component.text.config.KalendarTextConfig
import com.himanshoe.kalendar.component.text.config.KalendarTextSize
import com.himanshoe.kalendar.model.KalendarDay
import com.himanshoe.kalendar.model.KalendarEvent
import com.himanshoe.kalendar.model.toKalendarDay
import com.himanshoe.kalendar.ui.oceanic.data.getNext7Dates
import com.himanshoe.kalendar.ui.oceanic.data.getPrevious7Dates
import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.plus
import kotlinx.datetime.todayIn
import java.time.format.TextStyle
import java.util.Locale

@Composable
fun KalendarOceanic(
    takeMeToDate: LocalDate?,
    kalendarDayColors: KalendarDayColors,
    kalendarThemeColors: List<KalendarThemeColor>,
    modifier: Modifier = Modifier,
    showWeekDays: Boolean = true,
    kalendarHeaderConfig: KalendarHeaderConfig? = null,
    kalendarEvents: List<KalendarEvent> = emptyList(),
    onCurrentDayClick: (KalendarDay, List<KalendarEvent>) -> Unit = { _, _ -> },
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
            kalendarHeaderConfig = kalendarHeaderConfig ?: KalendarHeaderConfig(
                kalendarTextConfig = KalendarTextConfig(
                    kalendarTextColor = KalendarTextColor(
                        kalendarThemeColors[
                            month.value.minus(
                                1
                            )
                        ].headerTextColor
                    ),
                    kalendarTextSize = KalendarTextSize.SubTitle
                )
            ),
        )

        Row(modifier = Modifier.wrapContentWidth()) {
            weekValue.value.forEach { localDate ->
                val isCurrentDay = localDate == currentDay
                Column {
                    if (showWeekDays) {
                        KalendarNormalText(
                            modifier = Modifier.align(Alignment.CenterHorizontally),
                            text = localDate.dayOfWeek.getDisplayName(
                                TextStyle.FULL, Locale.getDefault()
                            ).take(1),
                            fontWeight = FontWeight.Normal,
                            textColor = kalendarDayColors.textColor
                        )
                    }
                    KalendarDay(
                        modifier = Modifier,
                        isCurrentDay = isCurrentDay,
                        kalendarDay = localDate.toKalendarDay(),
                        kalendarEvents = kalendarEvents.filter { it.date.dayOfMonth == localDate.dayOfMonth },
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
}

@Composable
fun KalendarOceanic(
    modifier: Modifier = Modifier,
    showWeekDays: Boolean = true,
    kalendarEvents: List<KalendarEvent> = emptyList(),
    onCurrentDayClick: (KalendarDay, List<KalendarEvent>) -> Unit = { _, _ -> },
    takeMeToDate: LocalDate?,
    kalendarDayColors: KalendarDayColors,
    kalendarThemeColor: KalendarThemeColor,
    kalendarHeaderConfig: KalendarHeaderConfig? = null
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
            kalendarHeaderConfig = kalendarHeaderConfig ?: KalendarHeaderConfig(
                KalendarTextConfig(
                    kalendarTextColor = KalendarTextColor(
                        kalendarThemeColor.headerTextColor
                    ),
                    kalendarTextSize = KalendarTextSize.SubTitle
                )
            )
        )
        Row(modifier = Modifier.wrapContentWidth()) {
            weekValue.value.forEach { localDate ->
                val isCurrentDay = localDate == currentDay
                Column {
                    if (showWeekDays) {
                        KalendarNormalText(
                            modifier = Modifier.align(Alignment.CenterHorizontally),
                            text = localDate.dayOfWeek.getDisplayName(
                                TextStyle.FULL, Locale.getDefault()
                            ).take(1),
                            fontWeight = FontWeight.Normal,
                            textColor = kalendarDayColors.textColor
                        )
                    }
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
}

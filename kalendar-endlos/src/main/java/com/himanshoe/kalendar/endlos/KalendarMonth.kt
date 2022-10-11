package com.himanshoe.kalendar.endlos

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.himanshoe.kalendar.endlos.color.KalendarThemeColor
import com.himanshoe.kalendar.endlos.component.KalendarHeader
import com.himanshoe.kalendar.endlos.component.day.EmptyKalendarDay
import com.himanshoe.kalendar.endlos.component.day.KalendarDay
import com.himanshoe.kalendar.endlos.component.day.config.KalendarDayColors
import com.himanshoe.kalendar.endlos.component.header.config.KalendarHeaderConfig
import com.himanshoe.kalendar.endlos.component.text.KalendarNormalText
import com.himanshoe.kalendar.endlos.model.KalendarDay
import com.himanshoe.kalendar.endlos.model.KalendarEvent
import com.himanshoe.kalendar.endlos.model.toKalendarDay
import kotlinx.datetime.Clock
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalDate
import kotlinx.datetime.Month
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDate
import kotlinx.datetime.todayIn

private const val DaysInWeek = 7
private val WeekDays = listOf("M", "T", "W", "T", "F", "S", "S")

@Composable
internal fun KalendarMonth(
    date: LocalDate,
    modifier: Modifier = Modifier,
    kalendarEvents: List<KalendarEvent>,
    onCurrentDayClick: (KalendarDay, List<KalendarEvent>) -> Unit,
    selectedDay: LocalDate,
    kalendarDayColors: KalendarDayColors,
    kalendarThemeColors: KalendarThemeColor,
    kalendarHeaderConfig: KalendarHeaderConfig,
) {
    val currentDay = Clock.System.todayIn(TimeZone.currentSystemDefault())

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp, start = 4.dp, top = 4.dp, end = 4.dp),
        verticalArrangement = Arrangement.spacedBy(6.dp),
    ) {
        KalendarHeader(
            modifier = Modifier.padding(vertical = 28.dp, horizontal = 16.dp),
            month = date.month,
            year = date.year,
            kalendarHeaderConfig = kalendarHeaderConfig
        )
        val start = getKalendarStartDay(date.dayOfWeek)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            WeekDays.forEach {
                KalendarNormalText(
                    text = it,
                    fontWeight = FontWeight.Normal,
                    color = kalendarDayColors.textColor
                )
            }
        }
        (start..date.month.minLength()).map {
            if (it > 0) {
                getGeneratedDay(it, date.month, date.year)
            } else -1
        }.toList().chunked(DaysInWeek).forEach { weekDays ->
            BoxWithConstraints(modifier = Modifier.fillMaxWidth()) {
                val size = (maxWidth.div(DaysInWeek))

                Row(horizontalArrangement = Arrangement.spacedBy(0.dp)) {
                    weekDays.forEach { localDate ->
                        if (localDate is LocalDate) {
                            val isCurrentDay = localDate == currentDay
                            KalendarDay(
                                modifier = Modifier,
                                size = size,
                                kalendarDay = localDate.toKalendarDay(),
                                kalendarEvents = kalendarEvents.filter { it.date.dayOfMonth == localDate.dayOfMonth },
                                onCurrentDayClick = onCurrentDayClick,
                                kalendarDayColors = kalendarDayColors,
                                selectedKalendarDay = selectedDay,
                                isCurrentDay = isCurrentDay,
                                dotColor = kalendarThemeColors.headerTextColor,
                                dayBackgroundColor = kalendarThemeColors.dayBackgroundColor
                            )
                        } else {
                            EmptyKalendarDay(
                                background = Color.Transparent,
                                modifier = Modifier
                            )
                        }
                    }
                }
            }
        }
    }
}

private fun getKalendarStartDay(dayOfWeek: DayOfWeek): Int {
    return 1.minus(dayOfWeek.value)
}

private fun getGeneratedDay(day: Int, currentMonth: Month, currentYear: Int): LocalDate {
    val monthValue =
        if (currentMonth.value.toString().length == 1) "0${currentMonth.value}" else currentMonth.value.toString()
    val newDay = if (day.toString().length == 1) "0$day" else day
    return "$currentYear-$monthValue-$newDay".toLocalDate()
}

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
import com.himanshoe.kalendar.endlos.component.KalendarHeader
import com.himanshoe.kalendar.endlos.component.day.EmptyKalendarDay
import com.himanshoe.kalendar.endlos.component.day.KalendarDay
import com.himanshoe.kalendar.endlos.component.day.config.KalendarDayConfig
import com.himanshoe.kalendar.endlos.component.text.KalendarNormalText
import com.himanshoe.kalendar.endlos.model.KalendarDay
import com.himanshoe.kalendar.endlos.model.KalendarEvent
import com.himanshoe.kalendar.endlos.model.toKalendarDay
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalDate
import kotlinx.datetime.Month
import kotlinx.datetime.toLocalDate

private const val DaysInWeek = 7
private val WeekDays = listOf("M", "T", "W", "T", "F", "S", "S")

@Composable
internal fun KalendarMonth(
    date: LocalDate,
    modifier: Modifier = Modifier,
    kalendarEvents: List<KalendarEvent>,
    onCurrentDayClick: (KalendarDay, List<KalendarEvent>) -> Unit,
    kalendarDayConfig: KalendarDayConfig,
    selectedDay: LocalDate,
) {

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp, start = 4.dp, top = 4.dp, end = 4.dp),
        verticalArrangement = Arrangement.spacedBy(6.dp),
    ) {
        KalendarHeader(
            modifier = Modifier.padding(vertical = 28.dp, horizontal = 16.dp),
            date = date,
        )
        val start = getStartDay(date.dayOfWeek)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            WeekDays.forEach {
                KalendarNormalText(
                    text = it,
                    fontWeight = FontWeight.Normal,
                    color = kalendarDayConfig.kalendarDayColors.textColor
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
                            val isSelected =
                                localDate.month.value == selectedDay.month.value && localDate.year == selectedDay.year && localDate == selectedDay

                            KalendarDay(
                                size = size,
                                kalendarDay = localDate.toKalendarDay(),
                                selectedKalendarDay = selectedDay,
                                kalendarDayConfig = kalendarDayConfig,
                                kalendarEvents = kalendarEvents,
                                onCurrentDayClick = onCurrentDayClick,
                                isSelected = isSelected
                            )
                        } else {
                            EmptyKalendarDay(
                                size = size,
                                background = Color.Transparent
                            )
                        }
                    }
                }
            }
        }
    }
}

fun getStartDay(dayOfWeek: DayOfWeek): Int {
    return 1.minus(dayOfWeek.value)
}

private fun getGeneratedDay(day: Int, currentMonth: Month, currentYear: Int): LocalDate {
    val monthValue =
        if (currentMonth.value.toString().length == 1) "0${currentMonth.value}" else currentMonth.value.toString()
    val newDay = if (day.toString().length == 1) "0$day" else day
    return "$currentYear-$monthValue-$newDay".toLocalDate()
}

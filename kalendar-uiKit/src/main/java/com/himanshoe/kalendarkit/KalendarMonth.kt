package com.himanshoe.kalendarkit

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.himanshoe.kalendarkit.component.KalendarHeader
import com.himanshoe.kalendarkit.component.day.EmptyKalendarDay
import com.himanshoe.kalendarkit.component.day.KalendarDay
import com.himanshoe.kalendarkit.component.day.config.KalendarDayConfig
import com.himanshoe.kalendarkit.model.KalendarDay
import com.himanshoe.kalendarkit.model.KalendarEvent
import com.himanshoe.kalendarkit.model.toKalendarDay
import kotlinx.datetime.*

private const val DaysInWeek = 7

@Composable
internal fun KalendarMonth(
    date: LocalDate,
    modifier: Modifier = Modifier,
    kalendarEvents: List<KalendarEvent>,
    takeMeToDate: LocalDate?,
    onCurrentDayClick: (KalendarDay, List<KalendarEvent>) -> Unit,
    kalendarDayConfig: KalendarDayConfig,
) {
    val today = Clock.System.todayIn(TimeZone.currentSystemDefault())

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(4.dp),
        verticalArrangement = Arrangement.spacedBy(6.dp),
    ) {
        KalendarHeader(
            modifier = Modifier.padding(vertical = 16.dp, horizontal =16.dp),
            date = date,
        )
        val start = getStartDay(date.dayOfWeek)
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
                            KalendarDay(
                                size = size,
                                kalendarDay = localDate.toKalendarDay(),
                                selectedKalendarDay = today,
                                kalendarDayConfig = kalendarDayConfig,
                                kalendarEvents = kalendarEvents,
                                onCurrentDayClick = onCurrentDayClick,
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

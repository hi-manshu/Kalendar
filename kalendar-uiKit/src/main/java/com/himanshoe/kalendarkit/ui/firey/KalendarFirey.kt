package com.himanshoe.kalendarkit.ui.firey

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.himanshoe.kalendarkit.component.day.config.KalendarDayConfig
import com.himanshoe.kalendarkit.config.KalendarConfigs
import com.himanshoe.kalendarkit.model.KalendarDay
import com.himanshoe.kalendarkit.model.KalendarEvent
import com.himanshoe.kalendarkit.ui.KalendarViewModel
import kotlinx.datetime.*

private val WeekDays = listOf("M", "T", "W", "T", "F", "S", "S")

@Composable
internal fun KalendarFirey(
    modifier: Modifier = Modifier,
    kalendarViewModel: KalendarViewModel = viewModel(),
    kalendarConfigs: KalendarConfigs,
    kalendarDayConfig: KalendarDayConfig,
    kalendarEvents: List<KalendarEvent> = emptyList(),
    onCurrentDayClick: (KalendarDay, List<KalendarEvent>) -> Unit = { _, _ -> },
) {}
private fun getDaysInMonth(month: Month?) = month?.minLength()

private fun getInitialDayOfMonth(firstDayOfMonth: DayOfWeek) = -(firstDayOfMonth.value).minus(2)

private fun getGeneratedDay(day: Int, currentMonth: Month, currentYear: Int): LocalDate {
    val monthValue =
        if (currentMonth.value.toString().length == 1) "0${currentMonth.value}" else currentMonth.value.toString()
    val newDay = if (day.toString().length == 1) "0$day" else day
    return "$currentYear-$monthValue-$newDay".toLocalDate()
}

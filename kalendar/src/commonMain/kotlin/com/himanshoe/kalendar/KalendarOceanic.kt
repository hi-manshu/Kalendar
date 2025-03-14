package com.himanshoe.kalendar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import com.himanshoe.kalendar.foundation.KalendarScaffold
import com.himanshoe.kalendar.foundation.action.KalendarSelectedDayRange
import com.himanshoe.kalendar.foundation.action.OnDaySelectionAction
import com.himanshoe.kalendar.foundation.action.onDayClick
import com.himanshoe.kalendar.foundation.color.KalendarColor
import com.himanshoe.kalendar.foundation.component.KalendarDay
import com.himanshoe.kalendar.foundation.component.KalendarHeader
import com.himanshoe.kalendar.foundation.component.config.KalendarDayKonfig
import com.himanshoe.kalendar.foundation.component.config.KalendarDayLabelKonfig
import com.himanshoe.kalendar.foundation.component.config.KalendarKonfig
import com.himanshoe.kalendar.foundation.event.KalendarEvents
import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.minus
import kotlinx.datetime.plus
import kotlinx.datetime.todayIn

@Composable
internal fun KalendarOceanic(
    selectedDate: LocalDate,
    arrowShown: Boolean,
    showDayLabel: Boolean,
    kalendarKonfig: KalendarKonfig,
    events: KalendarEvents,
    modifier: Modifier = Modifier,
    restrictToCurrentMonth: Boolean,
    startDayOfWeek: DayOfWeek = DayOfWeek.SUNDAY,
    onDaySelectionAction: OnDaySelectionAction = OnDaySelectionAction.Single { _, _ -> },
) {
    KalendarOceanicContent(
        selectedDate = selectedDate,
        startDayOfWeek = startDayOfWeek,
        modifier = modifier,
        arrowShown = arrowShown,
        backgroundColor = kalendarKonfig.backgroundColor,
        showDayLabel = showDayLabel,
        restrictToCurrentMonth = restrictToCurrentMonth,
        onDaySelectionAction = onDaySelectionAction,
        dayKonfig = kalendarKonfig.kalendarDayKonfig,
        kalendarDayLabelKonfig = kalendarKonfig.kalendarDayLabelKonfig,
        events = events
    )
}

@Composable
private fun KalendarOceanicContent(
    selectedDate: LocalDate,
    startDayOfWeek: DayOfWeek,
    arrowShown: Boolean,
    restrictToCurrentMonth: Boolean,
    backgroundColor: KalendarColor,
    showDayLabel: Boolean,
    onDaySelectionAction: OnDaySelectionAction,
    dayKonfig: KalendarDayKonfig,
    events: KalendarEvents,
    kalendarDayLabelKonfig: KalendarDayLabelKonfig,
    modifier: Modifier = Modifier,
) {
    var currentMonth by remember {
        mutableStateOf(
            selectedDate.minus(
                selectedDate.dayOfMonth - 1,
                DateTimeUnit.DAY
            )
        )
    }
    val today = remember { Clock.System.todayIn(TimeZone.currentSystemDefault()) }
    val selectedRange = remember { mutableStateOf<KalendarSelectedDayRange?>(null) }
    var rangeStartDate by remember { mutableStateOf<LocalDate?>(null) }
    var rangeEndDate by remember { mutableStateOf<LocalDate?>(null) }
    var clickedNewDate by remember { mutableStateOf(selectedDate) }
    val daysOfWeek = DayOfWeek.entries.rotate(startDayOfWeek.ordinal)
    val displayDates by remember(currentMonth, startDayOfWeek) {
        mutableStateOf(getMonthDates(currentMonth, startDayOfWeek))
    }
    val isCurrentMonth = currentMonth.year == today.year && currentMonth.month > today.month

    Column(
        modifier = modifier.background(brush = Brush.linearGradient(backgroundColor.value)),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        KalendarHeader(
            modifier = Modifier,
            month = currentMonth.month,
            year = currentMonth.year,
            arrowShown = arrowShown,
            canNavigateBack = !restrictToCurrentMonth || isCurrentMonth,
            onPreviousClick = {
                if (!restrictToCurrentMonth || isCurrentMonth) {
                    currentMonth = currentMonth.minus(1, DateTimeUnit.MONTH)
                }
            },
            onNextClick = { currentMonth = currentMonth.plus(1, DateTimeUnit.MONTH) }
        )
        KalendarScaffold(
            modifier = Modifier.fillMaxWidth(),
            showDayLabel = showDayLabel,
            dayOfWeek = { daysOfWeek },
            kalendarDayLabelKonfig = kalendarDayLabelKonfig,
            dates = { displayDates },
        ) { date ->
            if (date.month == currentMonth.month) {
                KalendarDay(
                    modifier = Modifier,
                    date = date,
                    selectedRange = selectedRange.value,
                    onDayClick = { clickedDate, events ->
                        clickedDate.onDayClick(
                            events = events,
                            rangeStartDate = rangeStartDate,
                            rangeEndDate = rangeEndDate,
                            onDaySelectionAction = onDaySelectionAction,
                            onClickedNewDate = {
                                clickedNewDate = it
                            },
                            onClickedRangeStartDate = {
                                rangeStartDate = it
                            },
                            onClickedRangeEndDate = {
                                rangeEndDate = it
                            },
                            onUpdateSelectedRange = {
                                selectedRange.value = it
                            },
                        )
                    },
                    dayKonfig = dayKonfig,
                    events = events,
                    selectedDate = clickedNewDate,
                )
            } else {
                Box(modifier = Modifier.fillMaxWidth())
            }
        }
    }
}

private fun getMonthDates(
    currentMonth: LocalDate,
    startDayOfWeek: DayOfWeek
): List<LocalDate> {
    val firstDayOfMonth = currentMonth.minus(currentMonth.dayOfMonth - 1, DateTimeUnit.DAY)
    val lastDayOfMonth = firstDayOfMonth.plus(1, DateTimeUnit.MONTH).minus(1, DateTimeUnit.DAY)
    val firstDayOffset = (firstDayOfMonth.dayOfWeek.ordinal - startDayOfWeek.ordinal + 7) % 7
    return (-firstDayOffset until lastDayOfMonth.dayOfMonth).map {
        firstDayOfMonth.plus(
            it.toLong(),
            DateTimeUnit.DAY
        )
    }
}

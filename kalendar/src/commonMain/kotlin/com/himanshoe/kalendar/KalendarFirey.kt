package com.himanshoe.kalendar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.style.TextAlign
import com.himanshoe.kalendar.core.color.KalendarColor
import com.himanshoe.kalendar.core.component.KalendarDay
import com.himanshoe.kalendar.core.component.KalendarHeader
import com.himanshoe.kalendar.core.config.KalendarDayKonfig
import com.himanshoe.kalendar.core.config.KalendarDayLabelKonfig
import com.himanshoe.kalendar.core.config.KalendarHeaderKonfig
import com.himanshoe.kalendar.core.config.KalendarKonfig
import com.himanshoe.kalendar.core.util.KalendarSelectedDayRange
import com.himanshoe.kalendar.core.util.OnDaySelectionAction
import com.himanshoe.kalendar.event.KalendarEvent
import com.himanshoe.kalendar.event.KalendarEvents
import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.minus
import kotlinx.datetime.plus
import kotlinx.datetime.todayIn

@Composable
fun KalendarFirey(
    date: LocalDate,
    modifier: Modifier = Modifier,
    selectedDate: LocalDate = date,
    events: KalendarEvents = KalendarEvents(),
    showDayLabel: Boolean = true,
    arrowShown: Boolean = true,
    onDaySelectionAction: OnDaySelectionAction = OnDaySelectionAction.Single { _, _ -> },
    kalendarKonfig: KalendarKonfig = KalendarKonfig(),
    restrictToCurrentWeek: Boolean = false,
) {
    KalendarFireyContent(
        selectedDate = selectedDate,
        modifier = modifier,
        arrowShown = arrowShown,
        showDayLabel = showDayLabel,
        onDaySelectionAction = onDaySelectionAction,
        dayKonfig = kalendarKonfig.kalendarDayKonfig,
        kalendarHeaderKonfig = kalendarKonfig.kalendarHeaderKonfig,
        kalendarDayLabelKonfig = kalendarKonfig.kalendarDayLabelKonfig,
        restrictToCurrentWeek = restrictToCurrentWeek,
        events = events,
        backgroundColor = kalendarKonfig.backgroundColor
    )
}

@Composable
private fun KalendarFireyContent(
    selectedDate: LocalDate,
    arrowShown: Boolean,
    backgroundColor: KalendarColor,
    showDayLabel: Boolean,
    onDaySelectionAction: OnDaySelectionAction,
    dayKonfig: KalendarDayKonfig,
    events: KalendarEvents,
    kalendarHeaderKonfig: KalendarHeaderKonfig,
    kalendarDayLabelKonfig: KalendarDayLabelKonfig,
    modifier: Modifier = Modifier,
    restrictToCurrentWeek: Boolean = false,
) {
    val today = remember { Clock.System.todayIn(TimeZone.currentSystemDefault()) }
    val startOfWeek = remember(today) { today.minus(today.dayOfWeek.ordinal, DateTimeUnit.DAY) }
    var currentDay by remember { mutableStateOf(today) }

    val selectedRange = remember { mutableStateOf<KalendarSelectedDayRange?>(null) }
    var rangeStartDate by remember { mutableStateOf<LocalDate?>(null) }
    var rangeEndDate by remember { mutableStateOf<LocalDate?>(null) }

    var clickedNewDate by remember { mutableStateOf(selectedDate) }
    val daysOfWeek = DayOfWeek.entries.toTypedArray()
    val displayDates by remember(currentDay) {
        mutableStateOf(generateWeekDates(currentDay))
    }

    fun onDayClick(
        clickedDate: LocalDate,
        events: List<KalendarEvent>,
    ) {
        when (onDaySelectionAction) {
            is OnDaySelectionAction.Single -> {
                clickedNewDate = clickedDate
                onDaySelectionAction.onDayClick(clickedDate, events)
            }

            is OnDaySelectionAction.Range -> {
                if (rangeStartDate == null || rangeEndDate != null) {
                    rangeStartDate = clickedDate
                    rangeEndDate = null
                } else {
                    rangeEndDate = clickedDate
                    if (rangeStartDate!! > rangeEndDate!!) {
                        val temp = rangeStartDate
                        rangeStartDate = rangeEndDate
                        rangeEndDate = temp
                    }
                    selectedRange.value =
                        KalendarSelectedDayRange(
                            rangeStartDate!!,
                            rangeEndDate!!
                        )
                    selectedRange.value?.let {
                        onDaySelectionAction.onRangeSelected(
                            it,
                            events
                        )
                    }
                }
                clickedNewDate = clickedDate
            }
        }
    }

    Column(
        modifier = modifier.background(brush = Brush.linearGradient(backgroundColor.value)),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        KalendarHeader(
            modifier = Modifier,
            month = currentDay.month,
            year = currentDay.year,
            arrowShown = arrowShown,
            kalendarHeaderKonfig = kalendarHeaderKonfig,
            canNavigateBack = !restrictToCurrentWeek || currentDay > today,
            onPreviousClick = {
                val newDay = currentDay.minus(7, DateTimeUnit.DAY)
                if (!restrictToCurrentWeek || newDay >= startOfWeek) {
                    currentDay = newDay
                }
            },
            onNextClick = { currentDay = currentDay.plus(7, DateTimeUnit.DAY) }
        )

        LazyVerticalGrid(
            modifier = Modifier.fillMaxWidth(),
            columns = GridCells.Fixed(7),
            horizontalArrangement = Arrangement.Center,
            content = {
                if (showDayLabel) {
                    items(daysOfWeek) { dayOfWeek ->
                        Text(
                            text = dayOfWeek.name.take(kalendarDayLabelKonfig.textCharCount),
                            modifier = Modifier
                                .fillMaxWidth()
                                .align(Alignment.CenterHorizontally),
                            style = kalendarDayLabelKonfig.textStyle
                        )
                    }
                }
                items(items = displayDates) { date ->
                    KalendarDay(
                        date = date,
                        selectedRange = selectedRange.value,
                        onDayClick = { clickedDate, events ->
                            onDayClick(
                                clickedDate = clickedDate,
                                events = events,
                            )
                        },
                        dayKonfig = dayKonfig,
                        events = events,
                        selectedDate = clickedNewDate,
                    )
                }
            }
        )
    }
}

private fun generateWeekDates(currentDay: LocalDate) =
    (-3..3).map { currentDay.plus(it, DateTimeUnit.DAY) }

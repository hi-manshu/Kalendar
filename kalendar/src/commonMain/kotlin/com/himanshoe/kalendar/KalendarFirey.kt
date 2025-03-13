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
import com.himanshoe.kalendar.core.color.KalendarColorScheme
import com.himanshoe.kalendar.core.component.KalendarDay
import com.himanshoe.kalendar.core.component.KalendarHeader
import com.himanshoe.kalendar.core.config.KalendarDayKonfig
import com.himanshoe.kalendar.core.config.KalendarKonfig
import com.himanshoe.kalendar.core.util.KalendarSelectedDayRange
import com.himanshoe.kalendar.core.util.OnDaySelectionAction
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
    colorScheme: KalendarColorScheme = KalendarColorScheme.default(),
    restrictToCurrentWeek: Boolean = false,
) {
    KalendarFireyContent(
        selectedDate = selectedDate,
        modifier = modifier,
        arrowShown = arrowShown,
        colorScheme = colorScheme,
        showDayLabel = showDayLabel,
        onDaySelectionAction = onDaySelectionAction,
        dayKonfig = kalendarKonfig.kalendarDayKonfig,
        restrictToCurrentWeek = restrictToCurrentWeek,
        events = events
    )
}

@Composable
private fun KalendarFireyContent(
    selectedDate: LocalDate,
    arrowShown: Boolean,
    colorScheme: KalendarColorScheme,
    showDayLabel: Boolean,
    onDaySelectionAction: OnDaySelectionAction,
    dayKonfig: KalendarDayKonfig,
    events: KalendarEvents,
    modifier: Modifier = Modifier,
    restrictToCurrentWeek: Boolean = false,
) {
    var currentDay by remember { mutableStateOf(Clock.System.todayIn(TimeZone.currentSystemDefault())) }
    val today = Clock.System.todayIn(TimeZone.currentSystemDefault())
    val startOfWeek = today.minus(today.dayOfWeek.ordinal, DateTimeUnit.DAY)

    val selectedRange = remember { mutableStateOf<KalendarSelectedDayRange?>(null) }
    var rangeStartDate by remember { mutableStateOf<LocalDate?>(null) }
    var rangeEndDate by remember { mutableStateOf<LocalDate?>(null) }

    var clickedNewDate by remember { mutableStateOf(selectedDate) }
    val daysOfWeek = DayOfWeek.entries.toTypedArray()
    val displayDates by remember(currentDay) {
        mutableStateOf((-3..3).map { currentDay.plus(it, DateTimeUnit.DAY) })
    }

    Column(
        modifier = modifier.background(brush = Brush.linearGradient(colorScheme.backgroundColor.value)),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        KalendarHeader(
            modifier = Modifier,
            month = currentDay.month,
            year = currentDay.year,
            arrowShown = arrowShown,
            colorScheme = colorScheme,
            canNavigateBack = if (restrictToCurrentWeek) !restrictToCurrentWeek || currentDay > today else true,
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
                            text = dayOfWeek.name.take(1),
                            modifier = Modifier
                                .fillMaxWidth()
                                .align(Alignment.CenterHorizontally),
                            textAlign = TextAlign.Center
                        )
                    }
                }
                items(items = displayDates) { date ->
                    KalendarDay(
                        date = date,
                        selectedRange = selectedRange.value,
                        onDayClick = { clickedDate, events ->
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
                                        if (rangeStartDate != null && rangeEndDate != null) {
                                            if (rangeStartDate!! > rangeEndDate!!) {
                                                // Swap the dates if start date is after end date
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
                                    }
                                    clickedNewDate = clickedDate
                                }
                            }
                        },
                        dayKonfig = dayKonfig,
                        colorScheme = colorScheme,
                        events = events,
                        selectedDate = clickedNewDate,
                    )
                }
            }
        )
    }
}
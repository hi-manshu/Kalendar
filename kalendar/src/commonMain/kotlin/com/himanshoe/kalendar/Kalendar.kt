package com.himanshoe.kalendar

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.himanshoe.kalendar.foundation.action.OnDaySelectionAction
import com.himanshoe.kalendar.foundation.component.config.KalendarKonfig
import com.himanshoe.kalendar.foundation.event.KalendarEvents
import kotlinx.datetime.Clock
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayIn

@Composable
fun Kalendar(
    kalendarType: KalendarType,
    modifier: Modifier = Modifier,
    selectedDate: LocalDate = Clock.System.todayIn(TimeZone.currentSystemDefault()),
    events: KalendarEvents = KalendarEvents(),
    showDayLabel: Boolean = true,
    arrowShown: Boolean = true,
    onDaySelectionAction: OnDaySelectionAction = OnDaySelectionAction.Single { _, _ -> },
    kalendarKonfig: KalendarKonfig = KalendarKonfig(),
    restrictToCurrentWeekOrMonth: Boolean = false,
    startDayOfWeek: DayOfWeek = DayOfWeek.SUNDAY,
) {
    when (kalendarType) {
        KalendarType.Oceanic -> {
            KalendarOceanic(
                selectedDate = selectedDate,
                modifier = modifier,
                arrowShown = arrowShown,
                showDayLabel = showDayLabel,
                onDaySelectionAction = onDaySelectionAction,
                kalendarKonfig = kalendarKonfig,
                events = events,
                startDayOfWeek = startDayOfWeek,
                restrictToCurrentMonth = restrictToCurrentWeekOrMonth,
            )
        }

        KalendarType.Firey -> {
            KalendarFirey(
                modifier = modifier,
                selectedDate = selectedDate,
                events = events,
                startDayOfWeek = startDayOfWeek,
                showDayLabel = showDayLabel,
                arrowShown = arrowShown,
                onDaySelectionAction = onDaySelectionAction,
                kalendarKonfig = kalendarKonfig,
                restrictToCurrentWeek = restrictToCurrentWeekOrMonth,
            )
        }

        KalendarType.Aerial -> {
            KalendarAerial(
                selectedDate = selectedDate,
                modifier = modifier,
                showDayLabel = showDayLabel,
                onDaySelectionAction = onDaySelectionAction,
                kalendarKonfig = kalendarKonfig,
                events = events,
                startDayOfWeek = startDayOfWeek,
            )
        }
    }
}

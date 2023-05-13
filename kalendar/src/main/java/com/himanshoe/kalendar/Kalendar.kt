package com.himanshoe.kalendar

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.himanshoe.kalendar.color.KalendarColors
import com.himanshoe.kalendar.ui.component.day.KalendarDayKonfig
import com.himanshoe.kalendar.ui.component.header.KalendarTextKonfig
import com.himanshoe.kalendar.ui.firey.DaySelectionMode
import com.himanshoe.kalendar.ui.firey.KalendarFirey
import com.himanshoe.kalendar.ui.firey.KalendarSelectedDayRange
import com.himanshoe.kalendar.ui.firey.RangeSelectionError
import com.himanshoe.kalendar.ui.oceanic.KalendarOceanic
import kotlinx.datetime.LocalDate
import java.time.Month

@Composable
fun Kalendar(
    currentDay: LocalDate?,
    kalendarType: KalendarType,
    modifier: Modifier = Modifier,
    showLabel: Boolean = true,
    kalendarHeaderTextKonfig: KalendarTextKonfig? = null,
    kalendarColors: KalendarColors = KalendarColors.default(),
    kalendarDayKonfig: KalendarDayKonfig = KalendarDayKonfig.default(),
    dayContent: (@Composable (LocalDate) -> Unit)? = null,
    daySelectionMode: DaySelectionMode = DaySelectionMode.Single,
    headerContent: (@Composable (Month, Int) -> Unit)? = null,
    onDayClick: (LocalDate, List<KalendarEvent>) -> Unit = { _, _ -> },
    onRangeSelected: (KalendarSelectedDayRange, List<KalendarEvent>) -> Unit = { _, _ -> },
    onErrorRangeSelected: (RangeSelectionError) -> Unit = {}
) {
    Kalendar(
        currentDay = currentDay,
        kalendarType = kalendarType,
        modifier = modifier,
        daySelectionMode = daySelectionMode,
        showLabel = showLabel,
        kalendarHeaderTextKonfig = kalendarHeaderTextKonfig,
        kalendarColors = kalendarColors,
        kalendarDayKonfig = kalendarDayKonfig,
        onDayClick = onDayClick,
        dayContent = dayContent,
        headerContent = headerContent,
        events = KalendarEvents(),
        onRangeSelected = onRangeSelected,
        onErrorRangeSelected = onErrorRangeSelected
    )
}

@Composable
fun Kalendar(
    currentDay: LocalDate?,
    kalendarType: KalendarType,
    modifier: Modifier = Modifier,
    showLabel: Boolean = true,
    events: KalendarEvents = KalendarEvents(),
    kalendarHeaderTextKonfig: KalendarTextKonfig? = null,
    kalendarColors: KalendarColors = KalendarColors.default(),
    kalendarDayKonfig: KalendarDayKonfig = KalendarDayKonfig.default(),
    daySelectionMode: DaySelectionMode = DaySelectionMode.Single,
    dayContent: (@Composable (LocalDate) -> Unit)? = null,
    headerContent: (@Composable (Month, Int) -> Unit)? = null,
    onDayClick: (LocalDate, List<KalendarEvent>) -> Unit = { _, _ -> },
    onRangeSelected: (KalendarSelectedDayRange, List<KalendarEvent>) -> Unit = { _, _ -> },
    onErrorRangeSelected: (RangeSelectionError) -> Unit = {}
) {
    when (kalendarType) {
        KalendarType.Oceanic -> {
            KalendarOceanic(
                currentDay = currentDay,
                modifier = modifier,
                showLabel = showLabel,
                kalendarHeaderTextKonfig = kalendarHeaderTextKonfig,
                kalendarColors = kalendarColors,
                events = events,
                kalendarDayKonfig = kalendarDayKonfig,
                onDayClick = onDayClick,
                dayContent = dayContent,
                headerContent = headerContent,
                daySelectionMode = daySelectionMode,
                onRangeSelected = onRangeSelected,
                onErrorRangeSelected = onErrorRangeSelected
            )
        }

        KalendarType.Firey -> {
            KalendarFirey(
                currentDay = currentDay,
                modifier = modifier,
                showLabel = showLabel,
                kalendarHeaderTextKonfig = kalendarHeaderTextKonfig,
                kalendarColors = kalendarColors,
                events = events,
                kalendarDayKonfig = kalendarDayKonfig,
                onDayClick = onDayClick,
                dayContent = dayContent,
                headerContent = headerContent,
                daySelectionMode = daySelectionMode,
                onRangeSelected = onRangeSelected,
                onErrorRangeSelected = onErrorRangeSelected
            )
        }
    }
}

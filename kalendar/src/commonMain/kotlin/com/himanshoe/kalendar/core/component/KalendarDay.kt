package com.himanshoe.kalendar.core.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastFilter
import androidx.compose.ui.util.fastForEachIndexed
import com.himanshoe.kalendar.core.color.KalendarColorScheme
import com.himanshoe.kalendar.core.config.KalendarDayKonfig
import com.himanshoe.kalendar.core.util.KalendarSelectedDayRange
import com.himanshoe.kalendar.core.util.circleLayout
import com.himanshoe.kalendar.core.util.dayBackgroundColor
import com.himanshoe.kalendar.event.KalendarEvent
import com.himanshoe.kalendar.event.KalendarEvents
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayIn

@Composable
fun KalendarDay(
    date: LocalDate,
    modifier: Modifier = Modifier,
    selectedRange: KalendarSelectedDayRange? = null,
    selectedDate: LocalDate = date,
    events: KalendarEvents = KalendarEvents(),
    dayKonfig: KalendarDayKonfig = KalendarDayKonfig.default(),
    colorScheme: KalendarColorScheme = KalendarColorScheme.default(),
    onDayClick: (LocalDate, List<KalendarEvent>) -> Unit = { _, _ -> },
) {
    KalendarDayContent(
        date = date,
        selectedDate = selectedDate,
        events = events,
        selectedRange = selectedRange,
        dayKonfig = dayKonfig,
        colorScheme = colorScheme,
        modifier = modifier,
        onDayClick = onDayClick,
    )
}

@Composable
private fun KalendarDayContent(
    date: LocalDate,
    modifier: Modifier = Modifier,
    selectedRange: KalendarSelectedDayRange? = null,
    selectedDate: LocalDate = date,
    dayKonfig: KalendarDayKonfig = KalendarDayKonfig.default(),
    events: KalendarEvents = KalendarEvents(),
    colorScheme: KalendarColorScheme = KalendarColorScheme.default(),
    onDayClick: (LocalDate, List<KalendarEvent>) -> Unit = { _, _ -> }
) {
    val today = remember(TimeZone.currentSystemDefault()) {
        Clock.System.todayIn(TimeZone.currentSystemDefault())
    }
    val currentDay = today == date
    val selected = date == selectedDate
    val color = if (selected) dayKonfig.selectedTextColor.value else dayKonfig.textColor.value
    val fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal
    val currentDayEvents = remember(events) { events.eventList.fastFilter { it.date == date } }

    Column(
        modifier = modifier.background(brush = Brush.linearGradient(colorScheme.backgroundColor.value))
            .border(
                border = getBorderStroke(
                    currentDay = currentDay,
                    brush = Brush.linearGradient(dayKonfig.borderColor.value),
                    selected = selected
                ),
                shape = CircleShape
            )
            .clip(CircleShape)
            .dayBackgroundColor(
                selected = selected,
                date = date,
                selectedRange = selectedRange,
                colors = colorScheme.dayBackgroundColor.value
            )
            .clickable { onDayClick(date, events.eventList) }
            .circleLayout()
            .defaultMinSize(dayKonfig.size),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = date.dayOfMonth.toString(),
            modifier = Modifier.wrapContentSize(),
            textAlign = TextAlign.Center,
            style = TextStyle(
                brush = Brush.linearGradient(color),
                fontWeight = fontWeight
            ),
        )
        if (currentDayEvents.isNotEmpty()) {
            EventIndicators(
                events = currentDayEvents,
                dayKonfig = dayKonfig,
                modifier = modifier.wrapContentSize().padding(top = 4.dp)
            )
        }
    }
}

@Composable
private fun EventIndicators(
    events: List<KalendarEvent>, dayKonfig: KalendarDayKonfig, modifier: Modifier = Modifier
) {
    val itemCount = if (events.count() > 3) 3 else events.size

    Row(modifier = modifier) {
        events.take(itemCount).fastForEachIndexed { index, _ ->
            KalendarIndicator(
                modifier = Modifier,
                index = index,
                size = dayKonfig.size,
                color = dayKonfig.indicatorColor
            )
        }
    }
}

private fun getBorderStroke(
    currentDay: Boolean, brush: Brush, selected: Boolean
) = if (currentDay && !selected) {
    BorderStroke(1.dp, brush)
} else {
    BorderStroke(0.dp, Color.Transparent)
}
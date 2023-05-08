package com.himanshoe.kalendar.endlos.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.himanshoe.kalendar.color.KalendarColor
import com.himanshoe.kalendar.endlos.model.KalendarDayKonfig
import com.himanshoe.kalendar.endlos.model.KalendarEvent
import com.himanshoe.kalendar.endlos.model.KalendarEvents
import com.himanshoe.kalendar.ui.component.day.modifier.circleLayout
import com.himanshoe.kalendar.ui.component.day.modifier.dayBackgroundColor
import com.himanshoe.kalendar.util.MultiplePreview
import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.minus
import kotlinx.datetime.plus
import kotlinx.datetime.todayIn

@Composable
fun KalendarDay(
    date: LocalDate,
    kalendarColors: KalendarColor,
    onDayClick: (LocalDate, List<KalendarEvent>) -> Unit,
    modifier: Modifier = Modifier,
    selectedDate: LocalDate = date,
    kalendarEvents: KalendarEvents = KalendarEvents(),
    kalendarDayKonfig: KalendarDayKonfig = KalendarDayKonfig.default(),
) {
    val selected = selectedDate == date
    Column(
        modifier = modifier
            .border(
                border = getBorder(date),
                shape = CircleShape
            )
            .clip(shape = CircleShape)
            .clickable { onDayClick(date, kalendarEvents.events) }
            .dayBackgroundColor(
                selected = selected,
                selectedColor = kalendarColors.dayBackgroundColor
            )
            .circleLayout()
            .wrapContentSize()
            .defaultMinSize(kalendarDayKonfig.size),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = date.dayOfMonth.toString(),
            modifier = Modifier.wrapContentSize(),
            textAlign = TextAlign.Center,
            fontSize = kalendarDayKonfig.textSize,
            color = getTextColor(selected = selected, kalendarDayKonfig = kalendarDayKonfig),
            fontWeight = getTextWeight(selected = selected)
        )
        Row(
            modifier = Modifier
                .padding(top = 4.dp)
                .wrapContentSize(),
            horizontalArrangement = Arrangement.Center
        ) {
            val kalendarEventForDay = kalendarEvents.events.filter { it.date == date }
            if (kalendarEventForDay.isNotEmpty()) {
                val dayEvents =
                    if (kalendarEventForDay.count() > 3) kalendarEventForDay.take(3) else kalendarEventForDay
                dayEvents.forEachIndexed { index, _ ->
                    KalendarIndicator(
                        modifier = Modifier,
                        index = index,
                        size = kalendarDayKonfig.size,
                        color = kalendarColors.headerTextColor
                    )
                }
            }
        }
    }
}

private fun getBorder(day: LocalDate): BorderStroke {
    val isCurrentDay = Clock.System.todayIn(TimeZone.currentSystemDefault()) == day
    return BorderStroke(
        width = if (isCurrentDay) 1.dp else 0.dp,
        color = if (isCurrentDay) Color.Black else Color.Transparent,
    )
}

private fun getTextColor(
    selected: Boolean,
    kalendarDayKonfig: KalendarDayKonfig,
) = when {
    selected -> kalendarDayKonfig.selectedTextColor
    else -> kalendarDayKonfig.textColor
}

private fun getTextWeight(selected: Boolean) = when {
    selected -> FontWeight.Bold
    else -> FontWeight.SemiBold
}

@MultiplePreview
@Composable
private fun KalendarDayPreview() {
    val date = Clock.System.todayIn(TimeZone.currentSystemDefault())
    val previous = Clock.System.todayIn(TimeZone.currentSystemDefault()).minus(1, DateTimeUnit.DAY)
    val events = (0..5).map {
        KalendarEvent(
            date = date,
            eventName = it.toString(),
        )
    }
    Row {
        KalendarDay(date = date,
            selectedDate = previous,
            kalendarColors = KalendarColor.previewDefault(),
            kalendarEvents = KalendarEvents(events),
            onDayClick = { _, _ -> })
        Spacer(modifier = Modifier.padding(vertical = 8.dp))
        KalendarDay(date = date.plus(1, DateTimeUnit.DAY),
            selectedDate = previous,
            kalendarColors = KalendarColor.previewDefault(),
            kalendarEvents = KalendarEvents(events),
            onDayClick = { _, _ -> })
        Spacer(modifier = Modifier.padding(vertical = 8.dp))
        KalendarDay(date = date,
            selectedDate = previous,
            kalendarColors = KalendarColor.previewDefault(),
            kalendarEvents = KalendarEvents(events),
            onDayClick = { _, _ -> })
    }
}
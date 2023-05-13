package com.himanshoe.kalendar.endlos.ui.day

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
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
import androidx.compose.ui.util.fastForEachIndexed
import com.himanshoe.kalendar.endlos.model.KalendarEvent
import com.himanshoe.kalendar.endlos.model.KalendarEvents
import com.himanshoe.kalendar.endlos.ui.KalendarIndicator
import com.himanshoe.kalendar.endlos.ui.color.KalendarColor
import com.himanshoe.kalendar.endlos.ui.modifier.circleLayout
import com.himanshoe.kalendar.endlos.util.MultiplePreviews
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
    kalendarColor: KalendarColor,
    onDayClick: (LocalDate, List<KalendarEvent>) -> Unit,
    modifier: Modifier = Modifier,
    selectedDate: LocalDate = date,
    events: KalendarEvents = KalendarEvents(),
    kalendarDayKonfig: KalendarDayKonfig = KalendarDayKonfig.default(),
) {
    val selected = selectedDate == date
    val currentDay = Clock.System.todayIn(TimeZone.currentSystemDefault()) == date

    Column(
        modifier = modifier
            .border(
                border = getBorder(currentDay, kalendarDayKonfig.borderColor, selected),
                shape = CircleShape
            )
            .clip(shape = CircleShape)
            .clickable { onDayClick(date, events.events) }
            .background(
                color = if (selected) kalendarColor.dayBackgroundColor else Color.Transparent,
                shape = CircleShape
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
            color = if (selected) kalendarDayKonfig.selectedTextColor else kalendarDayKonfig.textColor,
            fontWeight = if (selected) FontWeight.Bold else FontWeight.SemiBold
        )
        Row {
            events.events
                .filter { it.date == date }
                .take(3)
                .fastForEachIndexed { index, _ ->
                    KalendarIndicator(
                        modifier = Modifier,
                        index = index,
                        size = kalendarDayKonfig.size,
                        color = kalendarColor.headerTextColor
                    )
                }
        }
    }
}

private fun getBorder(currentDay: Boolean, color: Color, selected: Boolean): BorderStroke {
    val emptyBorder = BorderStroke(0.dp, Color.Transparent)
    return if (currentDay && selected.not()) {
        BorderStroke(1.dp, color)
    } else {
        emptyBorder
    }
}

@Composable
@MultiplePreviews
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
        KalendarDay(
            date = date,
            selectedDate = previous,
            kalendarColor = KalendarColor.previewDefault(),
            events = KalendarEvents(events),
            onDayClick = { _, _ -> }
        )
        Spacer(modifier = Modifier.padding(vertical = 8.dp))
        KalendarDay(
            date = date.plus(1, DateTimeUnit.DAY),
            selectedDate = previous,
            kalendarColor = KalendarColor.previewDefault(),
            events = KalendarEvents(events),
            onDayClick = { _, _ -> }
        )
        Spacer(modifier = Modifier.padding(vertical = 8.dp))
        KalendarDay(
            date = date,
            selectedDate = previous,
            kalendarColor = KalendarColor.previewDefault(),
            events = KalendarEvents(events),
            onDayClick = { _, _ -> }
        )
    }
}

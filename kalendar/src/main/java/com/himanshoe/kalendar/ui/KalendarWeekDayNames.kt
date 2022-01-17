package com.himanshoe.kalendar.ui

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp
import com.himanshoe.design.primitive.texts.Demibold
import com.himanshoe.design.primitive.texts.KalendarText
import com.himanshoe.design.theme.Grid

private val weekdays = listOf("Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat")

@Composable
fun KalendarWeekDayNames() {
    BoxWithConstraints(modifier = Modifier.fillMaxWidth()) {
        val width = (maxWidth / DAYS_IN_WEEK)
        Row(modifier = Modifier.fillMaxWidth()) {
            weekdays.forEach { weekDay: String ->
                KalendarWeekDay(
                    modifier = Modifier
                        .requiredWidth(width)
                        .requiredHeight(50.dp),
                    text = weekDay
                )
            }
        }
    }
}

@Composable
private fun KalendarWeekDay(
    modifier: Modifier = Modifier,
    text: String
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        KalendarText.Text4.Demibold(text = text,modifier = Modifier.alpha(0.5F))
    }
}



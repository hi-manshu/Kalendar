package com.himanshoe.kalendar.ui.ocean

import android.util.Log
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.consumeAllChanges
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.style.TextAlign
import com.himanshoe.design.primitive.texts.Demibold
import com.himanshoe.design.primitive.texts.KalendarText
import com.himanshoe.design.primitive.texts.Regular
import java.time.LocalDate

@Composable
fun KalendarOceanWeek(
    startDate: LocalDate = LocalDate.now(),
) {
    val next7Dates = remember {
        startDate.getNext7Dates()
    }
    val previous7Dates = remember {
        startDate.getPrevious7Dates()
    }
    BoxWithConstraints(modifier = Modifier
        .fillMaxWidth()
        .pointerInput(Unit) {
            detectDragGestures { change, dragAmount ->
                change.consumeAllChanges()
                val (x, y) = dragAmount
                when {
                    x > 0 -> {
                        Log.d("dsfsfsdfsdf1", "right")
                    }
                    x < 0 -> {
                        Log.d("dsfsfsdfsdf", "left")
                    }
                }
            }
        }) {
        val size = (maxWidth / 7)
        val state = rememberLazyListState()
        Row(modifier = Modifier.fillMaxWidth()) {
            next7Dates.forEach { item ->
                Column {
                    KalendarText.Text5.Regular(
                        text = item.dayOfWeek.toString().subSequence(0, 3).toString(),
                        modifier = Modifier.width(size),
                        textAlign = TextAlign.Center
                    )
                    KalendarText.Text4.Demibold(
                        text = item.dayOfMonth.toString(),
                        modifier = Modifier.size(size),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

private fun LocalDate.getNext7Dates(): List<LocalDate> {
    val dates = mutableListOf<LocalDate>()
    repeat(7) { day ->
        dates.add(this.plusDays(day.toLong()))
    }
    return dates
}

private fun LocalDate.getPrevious7Dates(): List<LocalDate> {
    val dates = mutableListOf<LocalDate>()
    repeat(7) { day ->
        dates.add(this.plusDays(day.toLong()))
    }
    return dates.reversed()
}

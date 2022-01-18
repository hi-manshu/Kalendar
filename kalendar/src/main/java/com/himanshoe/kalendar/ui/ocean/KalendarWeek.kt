package com.himanshoe.kalendar.ui.ocean

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.himanshoe.design.primitive.texts.Demibold
import com.himanshoe.design.primitive.texts.KalendarText
import com.himanshoe.design.primitive.texts.Regular
import java.time.LocalDate

@Composable
fun KalendarOceanWeek(
    startDate: LocalDate = LocalDate.now(),
) {
    val next7Dates = startDate.getNext7Dates()
    val previous7Dates = startDate.getPrevious7Dates()

    BoxWithConstraints(modifier = Modifier.fillMaxWidth()) {
        val size = (maxWidth / 7)
        LazyRow(modifier = Modifier.fillMaxWidth()) {
            items(items = next7Dates, itemContent = { item ->
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

            })

        }
    }
}

private fun LocalDate.getNext7Dates(): List<LocalDate> {
    val dates = mutableListOf<LocalDate>()
    repeat(14) { day ->
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

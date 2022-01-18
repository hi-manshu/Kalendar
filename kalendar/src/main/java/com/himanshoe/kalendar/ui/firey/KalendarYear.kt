package com.himanshoe.kalendar.ui.firey

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.himanshoe.design.theme.Grid
import java.time.LocalDate
import java.time.YearMonth

@Composable
internal fun KalendarView(
    yearMonth: YearMonth = YearMonth.now(),
    onCurrentDayClick: (LocalDate) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(Grid.Half)
    ) {
        KalendarMonth(
            yearMonth,
            onCurrentDayClick,
        )
    }
}


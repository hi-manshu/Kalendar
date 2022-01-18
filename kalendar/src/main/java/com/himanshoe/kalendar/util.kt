package com.himanshoe.kalendar

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.remember
import java.time.Year
import java.time.YearMonth
import java.time.format.DateTimeFormatter

@Composable
fun State<YearMonth>.getMonthNameFormatter(): DateTimeFormatter {
    return remember {
        DateTimeFormatter.ofPattern(
            if (Year.now().value == this.value.year) "MMMM" else "MMMM y"
        )
    }
}

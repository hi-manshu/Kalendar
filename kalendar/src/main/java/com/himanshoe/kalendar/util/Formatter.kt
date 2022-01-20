package com.himanshoe.kalendar

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import java.time.format.DateTimeFormatter

@Composable
fun getMonthNameFormatter(): DateTimeFormatter {
    return remember {
        DateTimeFormatter.ofPattern(
            "MMMM y"
        )
    }
}

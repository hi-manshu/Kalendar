package com.himanshoe.kalendar.endlos.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.himanshoe.kalendar.endlos.component.header.config.KalendarHeaderConfig
import com.himanshoe.kalendar.endlos.component.text.KalendarTitle
import kotlinx.datetime.Month
import java.time.format.TextStyle
import java.util.Locale

@Composable
internal fun KalendarHeader(
    modifier: Modifier = Modifier,
    month: Month,
    year: Int,
    kalendarHeaderConfig: KalendarHeaderConfig,

) {
    KalendarTitle(
        modifier = modifier.fillMaxWidth(),
        text = getTitleText(month, year),
        kalendarTextConfig = kalendarHeaderConfig.kalendarTextConfig
    )
}

internal fun getTitleText(month: Month, year: Int): String {
    return month.getDisplayName(TextStyle.FULL, Locale.getDefault()).lowercase().replaceFirstChar {
        if (it.isLowerCase()) it.titlecase(
            Locale.getDefault()
        ) else it.toString()
    } + " " + year
}

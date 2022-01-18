package com.himanshoe.kalendar.ui.firey

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign

@Composable
internal fun KalendarEmptyDay(modifier: Modifier = Modifier) {
    Text(
        modifier = modifier,
        text = " ",
        maxLines = 1,
        textAlign = TextAlign.Center
    )
}


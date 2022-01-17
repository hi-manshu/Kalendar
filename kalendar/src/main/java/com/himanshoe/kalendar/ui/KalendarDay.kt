package com.himanshoe.kalendar.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.himanshoe.design.primitive.texts.KalendarText
import com.himanshoe.design.primitive.texts.Regular
import com.himanshoe.design.theme.KalendarShape
import com.himanshoe.design.theme.KalendarTheme

@Composable
fun KalendarDay(
    size: Dp,
    modifier: Modifier = Modifier,
    day: Int,
    isSelected: Boolean,
    isToday: Boolean,
    onDayClick: (Int) -> Unit,
) {
    Surface(
        color = if (isSelected) KalendarTheme.colors.generalColor else KalendarTheme.colors.background,
        shape = if (isSelected) KalendarShape.SelectedShape else KalendarShape.DefaultRectangle
    ) {
        var localModifier = modifier
            .clickable {
                onDayClick(day)
            }
        if (isToday) {
            localModifier = localModifier
                .size(size)
                .border(
                    width = 2.dp,
                    color = KalendarTheme.colors.generalSelected,
                    shape = KalendarShape.SelectedShape
                )
        }
        Box(
            modifier = localModifier,
            contentAlignment = Alignment.Center
        ) {
            KalendarText.Text4.Regular(
                text = day.toString(),
                maxLines = 1,
                textAlign = TextAlign.End,
                color = if (isSelected) KalendarTheme.colors.white else KalendarTheme.colors.black
            )
        }
    }
}

@Preview
@Composable
fun KalendarDayPreview() {
}

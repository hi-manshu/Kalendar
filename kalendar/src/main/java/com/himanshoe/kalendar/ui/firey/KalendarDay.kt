package com.himanshoe.kalendar.ui.firey

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.himanshoe.design.primitive.texts.KalendarText
import com.himanshoe.design.primitive.texts.Regular
import com.himanshoe.design.theme.KalendarShape
import com.himanshoe.design.theme.KalendarTheme
import com.himanshoe.kalendar.common.KalendarSelector
import com.himanshoe.kalendar.common.ui.KalendarDot
import java.time.LocalDate

@Composable
internal fun KalendarDay(
    size: Dp,
    modifier: Modifier = Modifier,
    date: LocalDate,
    isSelected: Boolean,
    isToday: Boolean,
    onDayClick: (LocalDate) -> Unit,
    kalendarSelector: KalendarSelector,
) {
    val isDot = kalendarSelector is KalendarSelector.Dot

    Surface(
        color = if (isSelected && !isDot) kalendarSelector.selectedColor else kalendarSelector.defaultColor,
        shape = if (!isDot) kalendarSelector.shape else KalendarShape.DefaultRectangle
    ) {
        var localModifier = modifier
            .size(size)
            .clickable {
                onDayClick(date)
            }

        if (isToday && !isDot) {
            localModifier = localModifier.border(
                width = 2.dp,
                color = KalendarTheme.colors.todayColor,
                shape = kalendarSelector.shape
            )
        }
        Column(
            modifier = localModifier,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            KalendarText.H4.Regular(
                text = date.dayOfMonth.toString(),
                maxLines = 1,
                textAlign = TextAlign.End,
                color = if (isSelected) kalendarSelector.selectedTextColor else kalendarSelector.defaultTextColor
            )
            if (isDot) {
                KalendarDot(kalendarSelector = kalendarSelector,
                    isSelected = isSelected,
                    isToday = isToday)
            }
        }
    }
}

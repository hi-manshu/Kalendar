package com.himanshoe.kalendar.ui.oceanic

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.hapticfeedback.HapticFeedback
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.text.style.TextAlign
import com.himanshoe.design.primitive.texts.Demibold
import com.himanshoe.design.primitive.texts.KalendarText
import com.himanshoe.design.theme.Grid
import com.himanshoe.design.theme.KalendarTheme
import java.time.LocalDate

@Composable
internal fun KalendarOceanHeader(
    text: String,
    displayWeek: MutableState<List<LocalDate>>,
    haptic: HapticFeedback
) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(start = Grid.One, bottom = Grid.Two),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        KalendarText.Text3.Demibold(
            text = text,
            modifier = Modifier.align(Alignment.CenterVertically),
            color = KalendarTheme.colors.black,
            textAlign = TextAlign.Justify
        )
        Row(
            modifier = Modifier.alpha(0.6F),
            horizontalArrangement = Arrangement.End,
        ) {
            KalendarOceanButton(
                Modifier,
                imageVector = Icons.Default.KeyboardArrowLeft,
                contentDescription = "Previous Week",
                onClick = {
                    haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                    displayWeek.value = displayWeek.value.first().getPrevious7Dates()
                }
            )
            KalendarOceanButton(
                Modifier,
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = "Next Month",
                onClick = {
                    haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                    displayWeek.value = displayWeek.value.last().getNext7Dates()
                }
            )
        }
    }
}

@Composable
internal fun KalendarOceanButton(
    modifier: Modifier,
    imageVector: ImageVector,
    contentDescription: String,
    onClick: () -> Unit
) {
    IconButton(
        onClick = onClick,
        modifier = modifier
            .clip(CircleShape)
    ) {
        Icon(
            modifier = Modifier
                .alpha(0.5F),
            imageVector = imageVector,
            contentDescription = contentDescription
        )
    }
}


internal fun LocalDate.getNext7Dates(): List<LocalDate> {
    val dates = mutableListOf<LocalDate>()
    repeat(7) { day ->
        dates.add(this.plusDays(day.toLong()))
    }
    return dates
}

internal fun LocalDate.getPrevious7Dates(): List<LocalDate> {
    val dates = mutableListOf<LocalDate>()
    repeat(7) { day ->
        dates.add(this.minusDays(day.toLong()))
    }
    return dates.reversed()
}

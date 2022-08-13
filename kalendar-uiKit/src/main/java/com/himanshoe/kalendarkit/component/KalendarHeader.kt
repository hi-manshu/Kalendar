package com.himanshoe.kalendarkit.component

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.himanshoe.kalendarkit.component.button.KalendarIconButton
import com.himanshoe.kalendarkit.component.text.KalendarSubTitle
import java.util.*

@OptIn(ExperimentalAnimationApi::class)
@Composable
internal fun KalendarHeader(
    modifier: Modifier,
    monthName: String,
    onPreviousWeekClick: () -> Unit = {},
    onNextWeekClick: () -> Unit = {}
) {

    Row(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(start = 8.dp, bottom = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        AnimatedContent(
            modifier = Modifier
                .wrapContentHeight()
                .wrapContentWidth()
                .align(Alignment.CenterVertically),
            targetState = getTitleText(monthName),
            transitionSpec = {
                addAnimation().using(
                    SizeTransform(clip = false)
                )
            }
        ) {
            KalendarSubTitle(
                text = it,
                modifier = Modifier
            )
        }

        Row(
            modifier = Modifier
                .wrapContentWidth()
                .align(Alignment.CenterVertically),
            horizontalArrangement = Arrangement.End,
        ) {
            KalendarIconButton(
                modifier = Modifier.wrapContentSize(),
                imageVector = Icons.Default.KeyboardArrowLeft,
                contentDescription = "Previous Week",
                onClick = { onPreviousWeekClick() }

            )
            KalendarIconButton(
                modifier = Modifier.wrapContentSize(),
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = "Next Month",
                onClick = { onNextWeekClick() }
            )
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
internal fun addAnimation(duration: Int = 500): ContentTransform {
    return slideInVertically(animationSpec = tween(durationMillis = duration)) { height -> height } + fadeIn(
        animationSpec = tween(durationMillis = duration)
    ) with slideOutVertically(animationSpec = tween(durationMillis = duration)) { height -> -height } + fadeOut(
        animationSpec = tween(durationMillis = duration)
    )
}

internal fun getTitleText(monthName: String): String {
    return monthName.lowercase().replaceFirstChar {
        if (it.isLowerCase()) it.titlecase(
            Locale.getDefault()
        ) else it.toString()
    }
}

package com.himanshoe.kalendar.core.component

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ContentTransform
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.toLowerCase
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.himanshoe.kalendar.core.color.KalendarColorScheme
import kotlinx.datetime.Month

@Composable
fun KalendarHeader(
    month: Month,
    year: Int,
    modifier: Modifier = Modifier,
    canNavigateBack: Boolean = true,
    colorScheme: KalendarColorScheme = KalendarColorScheme.default(),
    arrowShown: Boolean = true,
    onPreviousClick: () -> Unit = {},
    onNextClick: () -> Unit = {},
) {
    KalendarHeaderContent(
        modifier = modifier,
        colorScheme = colorScheme,
        month = month,
        year = year,
        canNavigateBack = canNavigateBack,
        arrowShown = arrowShown,
        onPreviousClick = onPreviousClick,
        onNextClick = onNextClick
    )
}

@Composable
private fun KalendarHeaderContent(
    colorScheme: KalendarColorScheme,
    month: Month,
    year: Int,
    arrowShown: Boolean,
    onPreviousClick: () -> Unit,
    onNextClick: () -> Unit,
    canNavigateBack: Boolean,
    modifier: Modifier = Modifier,
) {
    var isNext by rememberSaveable { mutableStateOf(true) }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(brush = Brush.linearGradient(colorScheme.backgroundColor.value))
            .wrapContentHeight()
            .padding(start = 16.dp, top = 8.dp, bottom = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        val titleText =
            remember(month, year, Locale.current) { getTitleText(month, year, Locale.current) }

        AnimatedContent(
            targetState = titleText,
            transitionSpec = {
                addAnimation(isNext = isNext).using(
                    SizeTransform(clip = false)
                )
            }
        ) { month ->
            Text(
                modifier = Modifier
                    .wrapContentSize()
                    .align(Alignment.CenterVertically),
                text = month,
                color = colorScheme.headerTextColor,
                fontWeight = FontWeight.SemiBold,
                style = TextStyle.Default.copy(fontSize = 20.sp),
                textAlign = TextAlign.Start
            )
        }
        if (arrowShown) {
            Row(
                modifier = Modifier
                    .wrapContentSize()
                    .align(Alignment.CenterVertically),
                horizontalArrangement = Arrangement.End,
            ) {
                KalendarIconButton(
                    modifier = Modifier.wrapContentSize(),
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                    contentDescription = "Previous Month",
                    enabled = canNavigateBack,
                    onClick = {
                        isNext = false
                        onPreviousClick()
                    }
                )
                KalendarIconButton(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                    modifier = Modifier.wrapContentSize(),
                    contentDescription = "Next Month",
                    onClick = {
                        isNext = true
                        onNextClick()
                    },
                    enabled = true
                )
            }
        }
    }
}

private fun addAnimation(duration: Int = 200, isNext: Boolean): ContentTransform {
    return (slideInVertically(
        animationSpec = tween(durationMillis = duration)
    ) { height -> if (isNext) height else -height } + fadeIn(
        animationSpec = tween(durationMillis = duration)
    )).togetherWith(
        slideOutVertically(
        animationSpec = tween(durationMillis = duration)
    ) { height -> if (isNext) -height else height } + fadeOut(
        animationSpec = tween(durationMillis = duration)
    ))
}

private fun getTitleText(month: Month, year: Int, locale: Locale): String {
    val monthDisplayName = month.name.toLowerCase(locale)
        .replaceFirstChar {
            if (it.isLowerCase()) it.toString().capitalize(locale) else it.toString()
        }
    val shortYear = year.toString().takeLast(2)
    return "$monthDisplayName '$shortYear"
}
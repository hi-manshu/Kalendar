package com.himanshoe.kalendar.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.himanshoe.design.primitive.texts.Demibold
import com.himanshoe.design.primitive.texts.KalendarText
import com.himanshoe.kalendar.monthNames
import com.himanshoe.design.theme.Grid
import com.himanshoe.design.theme.KalendarTheme

@Composable
fun KalendarHeader(
    currentYear: Int,
    currentMonth: Int,
    onPreviousMonthClick: () -> Unit,
    onNextMonthClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        KalendarButton(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = "Previous Month",
            onClick = onPreviousMonthClick
        )
        KalendarText.Text4.Demibold(
            modifier = Modifier
                .padding(Grid.Two),
            text = "${monthNames[currentMonth]} $currentYear",
            textAlign = TextAlign.Center,
        )
        KalendarButton(
            imageVector = Icons.Default.ArrowForward,
            contentDescription = "Next Month",
            onClick = onNextMonthClick
        )
    }
}

@Composable
private fun KalendarButton(
    imageVector: ImageVector,
    contentDescription: String,
    onClick: () -> Unit
) {
    IconButton(
        onClick = onClick,
        modifier = Modifier
            .size(Grid.TwoHalf)
            .clip(CircleShape)
            .background(KalendarTheme.colors.generalSelected)
    ) {
        Icon(
            modifier = Modifier
                .padding(Grid.Half)
                .alpha(0.6F),
            imageVector = imageVector,
            contentDescription = contentDescription
        )
    }
}

package com.himanshoe.kalendar.ui.firey
/*
* MIT License
*
* Copyright (c) 2022 Himanshu Singh
*
* Permission is hereby granted, free of charge, to any person obtaining a copy
* of this software and associated documentation files (the "Software"), to deal
* in the Software without restriction, including without limitation the rights
* to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
* copies of the Software, and to permit persons to whom the Software is
* furnished to do so, subject to the following conditions:
*
* The above copyright notice and this permission notice shall be included in all
* copies or substantial portions of the Software.
*
* THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
* IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
* FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
* AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
* LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
* OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
* SOFTWARE.
*/
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
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
import androidx.compose.ui.text.style.TextAlign
import com.himanshoe.kalendar.common.KalendarSelector
import com.himanshoe.kalendar.common.theme.Grid

@Composable
internal fun KalendarHeader(
    text: String,
    onPreviousMonthClick: () -> Unit,
    onNextMonthClick: () -> Unit,
    kalendarSelector: KalendarSelector,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        KalendarButton(
            kalendarSelector = kalendarSelector,
            imageVector = Icons.Default.ArrowBack,
            contentDescription = "Previous Month",
            onClick = onPreviousMonthClick
        )
        Text(
            modifier = Modifier
                .padding(Grid.Two),
            style = MaterialTheme.typography.h6,
            text = text,
            textAlign = TextAlign.Center,
        )
        KalendarButton(
            imageVector = Icons.Default.ArrowForward,
            contentDescription = "Next Month",
            onClick = onNextMonthClick,
            kalendarSelector = kalendarSelector
        )
    }
}

@Composable
private fun KalendarButton(
    imageVector: ImageVector,
    contentDescription: String,
    onClick: () -> Unit,
    kalendarSelector: KalendarSelector,
) {
    IconButton(
        onClick = onClick,
        modifier = Modifier
            .size(Grid.Three)
            .clip(CircleShape)
            .background(kalendarSelector.todayColor)
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

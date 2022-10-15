/*
 * Copyright 2022 Kalendar Contributors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.himanshoe.kalendar.endlos.component.text

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.himanshoe.kalendar.endlos.component.text.config.KalendarTextSize

@Composable
fun KalendarNormalText(
    text: String,
    modifier: Modifier = Modifier,
    fontWeight: FontWeight,
    color: Color,
    textSize: TextUnit = KalendarTextSize.Normal.size

) {
    Text(
        modifier = modifier,
        color = color,
        fontSize = textSize,
        text = text,
        fontWeight = fontWeight,
        textAlign = TextAlign.Center
    )
}

@Preview
@Composable
private fun KalendarNormalTextPreview() {
    KalendarNormalText(
        text = "Hye Himanshu",
        modifier = Modifier,
        fontWeight = FontWeight.SemiBold,
        color = Color.Black,
        textSize = 26.sp
    )
}

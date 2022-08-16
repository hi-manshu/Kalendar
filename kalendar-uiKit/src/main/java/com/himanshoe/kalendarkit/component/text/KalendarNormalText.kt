package com.himanshoe.kalendarkit.component.text

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.himanshoe.kalendarkit.component.text.config.KalendarTextConfig
import com.himanshoe.kalendarkit.component.text.config.KalendarTextDefaults

@Composable
fun KalendarNormalText(
    text: String,
    modifier: Modifier = Modifier,
    fontWeight: FontWeight,
    color: Color,
    kalendarTextConfig: KalendarTextConfig = KalendarTextDefaults.kalendarNormalTextConfig(),

) {
    Text(
        modifier = modifier,
        color = color,
        fontSize = kalendarTextConfig.kalendarTextSize.size,
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
    )
}

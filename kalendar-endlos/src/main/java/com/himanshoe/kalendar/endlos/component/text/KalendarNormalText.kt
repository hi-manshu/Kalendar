package com.himanshoe.kalendar.endlos.component.text

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import com.himanshoe.kalendar.endlos.component.text.config.KalendarTextConfig
import com.himanshoe.kalendar.endlos.component.text.config.KalendarTextDefaults

@Composable
fun KalendarNormalText(
    text: String,
    modifier: Modifier = Modifier,
    fontWeight: FontWeight,
    color: Color,
    kalendarTextConfig: KalendarTextConfig = KalendarTextDefaults.kalendarNormalTextConfig(),
    textSize: TextUnit? = null
) {
    val fontSize = textSize ?: kalendarTextConfig.kalendarTextSize.size
    Text(
        modifier = modifier,
        color = color,
        fontSize = fontSize,
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
        textSize = null
    )
}

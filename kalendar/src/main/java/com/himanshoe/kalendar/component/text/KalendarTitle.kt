package com.himanshoe.kalendar.component.text

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.himanshoe.kalendar.component.text.config.KalendarTextConfig
import com.himanshoe.kalendar.component.text.config.KalendarTextDefaults

@Composable
fun KalendarTitle(
    text: String,
    modifier: Modifier = Modifier,
    textAlign: TextAlign = TextAlign.Start,
    fontWeight: FontWeight = FontWeight.SemiBold,
    kalendarTextConfig: KalendarTextConfig = KalendarTextDefaults.kalendarTitleTextConfig()
) {
    Text(
        modifier = modifier,
        color = kalendarTextConfig.kalendarTextColor.textColor,
        fontSize = kalendarTextConfig.kalendarTextSize.size,
        text = text,
        fontWeight = fontWeight,
        textAlign = textAlign
    )
}

@Preview
@Composable
private fun KalendarTitlePreview() {
    KalendarTitle(modifier = Modifier, text = "Hye Himanshu")
}

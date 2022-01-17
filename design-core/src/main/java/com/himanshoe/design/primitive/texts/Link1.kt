package com.himanshoe.design.primitive.texts

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.intl.LocaleList
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.tooling.preview.Preview
import  com.himanshoe.design.primitive.texts.KalendarText
import com.himanshoe.design.theme.KalendarTheme

@Composable
fun KalendarText.Link1.Regular(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    textAlign: TextAlign? = null,
    overflow: TextOverflow = TextOverflow.Clip,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
) {
    Text(
        text = text,
        modifier = modifier,
        color = color,
        textAlign = textAlign,
        overflow = overflow,
        softWrap = softWrap,
        maxLines = maxLines,
        style = KalendarTheme.typography.link1Regular,
    )
}

@Composable
@Preview
private fun Link1RegularPreview() {
    KalendarTheme {
        KalendarText.Link1.Regular(text = "Link1Regular")
    }
}

@Composable
fun KalendarText.Link2.Demibold(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    textAlign: TextAlign? = null,
    overflow: TextOverflow = TextOverflow.Clip,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
) {
    Text(
        text = text.toUpperCase(LocaleList.current),
        modifier = modifier,
        color = color,
        textAlign = textAlign,
        overflow = overflow,
        softWrap = softWrap,
        maxLines = maxLines,
        style = KalendarTheme.typography.link2Demibold,
    )
}

@Composable
@Preview
private fun Link2DemiboldPreview() {
    KalendarTheme {
        KalendarText.Link2.Demibold(text = "Link2Demibold")
    }
}

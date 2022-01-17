package  com.himanshoe.design.primitive.texts

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.himanshoe.design.theme.KalendarTheme

@Composable
fun KalendarText.Text3.Regular(
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
        style = KalendarTheme.typography.text3Regular,
    )
}

@Composable
@Preview
private fun Text3RegularPreview() {
    KalendarTheme {
        KalendarText.Text3.Regular(text = "Text3Regular")
    }
}

@Composable
fun KalendarText.Text3.Demibold(
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
        style = KalendarTheme.typography.text3Demibold,
    )
}

@Composable
@Preview
private fun Text3DemiboldPreview() {
    KalendarTheme {
        KalendarText.Text3.Demibold(text = "Text3Demibold")
    }
}

@Composable
fun KalendarText.Text3.Bold(
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
        style = KalendarTheme.typography.text3Bold,
    )
}

@Composable
@Preview
private fun Text3BoldPreview() {
    KalendarTheme {
        KalendarText.Text3.Bold(text = "Text3Bold")
    }
}

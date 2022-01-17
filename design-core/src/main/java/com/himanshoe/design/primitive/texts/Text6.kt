package  com.himanshoe.design.primitive.texts

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.himanshoe.design.theme.KalendarTheme

@Composable
fun KalendarText.Text6.Regular(
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
        style = KalendarTheme.typography.text6Regular,
    )
}

@Composable
@Preview
private fun Text6RegularPreview() {
    KalendarTheme {
        KalendarText.Text6.Regular(text = "Text6Regular")
    }
}

@Composable
fun KalendarText.Text6.Italic(
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
        style = KalendarTheme.typography.text6Regular,
        fontStyle = FontStyle.Italic
    )
}

@Composable
@Preview
private fun Text6ItalicPreview() {
    KalendarTheme {
        KalendarText.Text6.Italic(text = "Text6Italic")
    }
}

@Composable
fun KalendarText.Text6.Demibold(
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
        style = KalendarTheme.typography.text6Demibold,
    )
}

@Composable
@Preview
private fun Text6DemiboldPreview() {
    KalendarTheme {
        KalendarText.Text6.Demibold(text = "Text6Demibold")
    }
}

@Composable
fun KalendarText.Text6.InlineDemibold(
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
        style = KalendarTheme.typography.text6Demibold,
        textDecoration = TextDecoration.Underline
    )
}

@Composable
@Preview
private fun Text6InlineDemiboldPreview() {
    KalendarTheme {
        KalendarText.Text6.InlineDemibold(text = "Text6InlineDemibold")
    }
}

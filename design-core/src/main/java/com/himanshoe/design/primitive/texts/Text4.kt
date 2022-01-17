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
fun KalendarText.Text4.Regular(
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
        style = KalendarTheme.typography.text4Regular,
    )
}

@Composable
@Preview
private fun Text4RegularPreview() {
    KalendarTheme {
        KalendarText.Text4.Regular(text = "Text4Regular")
    }
}

@Composable
fun KalendarText.Text4.RegularMoreLineHeight(
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
        style = KalendarTheme.typography.text4RegularMoreLineHeight,
    )
}

@Composable
@Preview
private fun Text4RegularMoreLineHeightPreview() {
    KalendarTheme {
        KalendarText.Text4.RegularMoreLineHeight(text = "Text4RegularMoreLineHeight")
    }
}

@Composable
fun KalendarText.Text4.Italic(
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
        style = KalendarTheme.typography.text4Regular,
        fontStyle = FontStyle.Italic
    )
}

@Composable
@Preview
private fun Text4ItalicPreview() {
    KalendarTheme {
        KalendarText.Text4.Italic(text = "Text4Italic")
    }
}

@Composable
fun KalendarText.Text4.Demibold(
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
        style = KalendarTheme.typography.text4Demibold,
    )
}

@Composable
@Preview
private fun Text4DemiboldPreview() {
    KalendarTheme {
        KalendarText.Text4.Demibold(text = "Text4Demibold")
    }
}

@Composable
fun KalendarText.Text4.InlineRegular(
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
        style = KalendarTheme.typography.text4Regular,
        textDecoration = TextDecoration.Underline
    )
}

@Composable
@Preview
private fun Text4InlineRegularPreview() {
    KalendarTheme {
        KalendarText.Text4.InlineRegular(text = "Text4InlineRegular")
    }
}

@Composable
fun KalendarText.Text4.InlineDemibold(
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
        style = KalendarTheme.typography.text4Demibold,
        textDecoration = TextDecoration.Underline
    )
}

@Composable
@Preview
private fun Text4InlineDemiboldPreview() {
    KalendarTheme {
        KalendarText.Text4.InlineDemibold(text = "Text4InlineDemibold")
    }
}

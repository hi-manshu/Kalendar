package com.himanshoe.kalendar.color

import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color

@Stable
@SuppressWarnings("MagicNumber")
private val backgroundColor = listOf(
    Color(0xffF7CFD3),
    Color(0xffEFBDCF),
    Color(0xffDBBFE4),
    Color(0xffCFC4E5),
    Color(0xffC6CAE6),
    Color(0xffC1DEF9),
    Color(0xffBDE3F9),
    Color(0xffBEE8F1),
    Color(0xffBBDEDB),
    Color(0xffCEE5CB),
    Color(0xffDEEBCB),
    Color(0xffF1F4C8),
)

@Stable
@SuppressWarnings("MagicNumber")
private val kalendarBackgroundColor = listOf(
    Color.White,
    Color(0xFFFCEFFE),
    Color(0xFFFDF2FE),
    Color(0xFFFEF7FE),
    Color(0xFFF9FDFE),
    Color(0xFFF1FEFF),
    Color(0xFFEBFEFF),
    Color(0xFFE9FEFF),
    Color(0xFFEBFEFF),
    Color(0xFFFCFFFC),
    Color(0xFFFFFFFB),
    Color(0xFFFFFFF7),
)

@Stable
@SuppressWarnings("MagicNumber")
private val headerColors = listOf(
    Color(0xFFC39EA1),
    Color(0xFFBB8D9E),
    Color(0xFFAA8FB1),
    Color(0xFF9E94B4),
    Color(0xFF9599B4),
    Color(0xFF91ABC5),
    Color(0xFF8CB2C6),
    Color(0xFF8CB7BE),
    Color(0xFF8BACA9),
    Color(0xFF9DB39A),
    Color(0xFFADBA9A),
    Color(0xFFBEC196),
)

data class KalendarColor(
    val backgroundColor: Color,
    val dayBackgroundColor: Color,
    val headerTextColor: Color,
) {
    companion object {

        internal fun previewDefault() = KalendarColor(
            kalendarBackgroundColor.first(), backgroundColor.first(), headerColors.first()
        )
    }
}

private const val TOTAL_MONTH = 12

data class KalendarColors(
    val color: List<KalendarColor> = emptyList()
) {
    companion object {
        internal fun default(): KalendarColors {
            val colors = List(TOTAL_MONTH) { index ->
                KalendarColor(
                    kalendarBackgroundColor[index],
                    backgroundColor[index],
                    headerColors[index]
                )
            }
            return KalendarColors(colors)
        }
    }
}

/*
 * Copyright 2023 Kalendar Contributors (https://www.himanshoe.com). All rights reserved.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.himanshoe.kalendar.color

import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color

/**
 * The `color` package provides color-related classes and utilities for the Kalendar library.
 *
 * It includes predefined color schemes for backgrounds, day backgrounds, and header text colors.
 *
 * @see KalendarColor
 * @see KalendarColors
 */

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

/**
 * A stable representation of a specific color scheme for Kalendar.
 *
 * @property backgroundColor The background color.
 * @property dayBackgroundColor The color for day backgrounds.
 * @property headerTextColor The color for header text.
 */
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

/**
 * A collection of predefined color schemes for Kalendar.
 *
 * @property color A list of [KalendarColor] instances.
 */
data class KalendarColors(
    val color: List<KalendarColor> = emptyList()
) {
    companion object {
        /**
         * Returns the default set of colors.
         *
         * @return The default [KalendarColors] instance.
         */
        fun default(): KalendarColors {
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

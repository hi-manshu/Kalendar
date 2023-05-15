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

package com.himanshoe.kalendar.ui.component.header

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

/**
 * Configuration data class for styling the Kalendar header text.
 * @param kalendarTextColor The color of the header text.
 * @param kalendarTextSize The size of the header text.
 */
@Immutable
data class KalendarTextKonfig(
    val kalendarTextColor: Color,
    val kalendarTextSize: TextUnit
) {
    companion object {
        /**
         * Creates a default configuration with the specified text color.
         * @param color The color of the header text.
         * @return The default configuration.
         */
        internal fun default(color: Color) = KalendarTextKonfig(
            kalendarTextColor = color,
            kalendarTextSize = 24.sp
        )

        /**
         * Creates a preview default configuration for previewing purposes.
         * @return The preview default configuration.
         */
        @SuppressWarnings("MagicNumber")
        internal fun previewDefault() = KalendarTextKonfig(
            kalendarTextSize = 24.sp,
            kalendarTextColor = Color(0xFFD2827A)
        )
    }
}

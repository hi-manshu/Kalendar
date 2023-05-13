package com.himanshoe.kalendar.ui.component.button

import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * An internal composable function that renders an icon button for the Kalendar library.
 *
 * @param imageVector The vector icon to display.
 * @param modifier The modifier for the icon button.
 * @param contentDescription The content description for accessibility.
 * @param onClick The callback function to invoke when the button is clicked.
 */
@Composable
internal fun KalendarIconButton(
    imageVector: ImageVector,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
    onClick: () -> Unit = {}
) {
    IconButton(
        onClick = onClick,
        modifier = modifier
            .wrapContentSize()
            .clip(CircleShape)
    ) {
        Icon(
            modifier = Modifier,
            tint = Color(0xFF413D4B),
            imageVector = imageVector,
            contentDescription = contentDescription
        )
    }
}

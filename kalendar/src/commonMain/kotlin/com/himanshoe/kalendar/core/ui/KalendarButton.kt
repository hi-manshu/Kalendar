package com.himanshoe.kalendar.core.ui

import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.himanshoe.kalendar.core.component.KalendarColor

@Composable
internal fun KalendarIconButton(
    imageVector: ImageVector,
    modifier: Modifier = Modifier,
    tintColor: KalendarColor = KalendarColor.Solid(Color(0xFF413D4B)),
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
            tint = tintColor.value.first(),
            imageVector = imageVector,
            contentDescription = contentDescription
        )
    }
}
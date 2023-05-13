package com.himanshoe.kalendar.ui.component.day.modifier

import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layout

/**
 * A custom modifier that applies circular layout behavior to a composable.
 *
 * This modifier is used to create a circular layout by taking the maximum dimension
 * of the composable and assigning it as both the width and height. The composable is then
 * centered within the circular layout.
 *
 * @return The modified layout modifier.
 */
fun Modifier.circleLayout() =
    layout { measurable, constraints ->
        // Measure the composable
        val placeable = measurable.measure(constraints)

        // get the current max dimension to assign width=height
        val currentHeight = placeable.height
        val currentWidth = placeable.width
        val newDiameter = maxOf(currentHeight, currentWidth)

        // assign the dimension and the center position
        layout(newDiameter, newDiameter) {
            // Where the composable gets placed
            placeable.placeRelative((newDiameter - currentWidth) / 2, (newDiameter - currentHeight) / 2)
        }
    }

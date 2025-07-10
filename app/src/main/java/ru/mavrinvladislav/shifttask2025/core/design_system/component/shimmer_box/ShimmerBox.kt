package ru.mavrinvladislav.shifttask2025.core.design_system.component.shimmer_box

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.shimmer
import com.google.accompanist.placeholder.placeholder

@Composable
fun ShimmerBox(modifier: Modifier = Modifier, shape: Shape) {
    Box(
        modifier = modifier
            .placeholder(
                visible = true,
                highlight = PlaceholderHighlight.shimmer(),
                color = Color.LightGray,
                shape = shape
            )
    )
}
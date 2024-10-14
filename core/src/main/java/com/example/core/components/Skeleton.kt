package com.example.core.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun Skeleton(
    width: Double,
    height: Double,
    leftPadding: Double = 0.0,
    rightPadding: Double = 0.0,
    topPadding: Double = 0.0,
    bottomPadding: Double = 0.0,
    cornerRadius: Double = 8.0,
    isCircleShape: Boolean = false,
) {
    Box(
        modifier = Modifier
            .width(width.dp)
            .height(height.dp)
            .padding(
                start = leftPadding.dp,
                end = rightPadding.dp,
                top = topPadding.dp,
                bottom = bottomPadding.dp
            )
            .clip(if (isCircleShape) CircleShape else RoundedCornerShape(cornerRadius.dp))
            .background(Color.LightGray)
    )
}
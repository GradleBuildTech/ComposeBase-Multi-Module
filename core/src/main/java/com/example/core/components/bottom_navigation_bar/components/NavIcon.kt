package com.example.core.components.bottom_navigation_bar.components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import com.example.core.lib.constants.Constants

@Composable
fun NavIcon(
    painter: Painter,
    label: String,
    colorTint: Color = Color.White,
    iconSize: Double = Constants.ICON_TAB_SIZE
) {
    Icon(
        painter = painter,
        contentDescription = label,
        tint = colorTint,
        modifier = Modifier.size(iconSize.dp)
    )
}
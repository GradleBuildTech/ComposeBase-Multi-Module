package com.example.core.lib.constants

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

object DesignSystem {
    val INSIDE_PADDING = 16.dp
    val OUTSIDE_PADDING = 24.dp
    val BUTTON_HEIGHT = 48.dp
    val SPACE_BETWEEN = 12.dp
    val MEDIUM_SPACE_BETWEEN = 24.dp
    val LARGE_SPACE_BETWEEN = 36.dp


    val BUTTON_CORNER_RADIUS = 8.dp
    val BUTTON_ELEVATION = 2.dp
    val BUTTON_STROKE_WIDTH = 2.dp

    ///Title small
    val TITLE_SMALL_STYLE = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold,
        letterSpacing = 0.15.sp
    )

    ///Title medium
    val TITLE_MEDIUM_STYLE = TextStyle(
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
        letterSpacing = 0.15.sp
    )
    
    ///Title large
    val TITLE_LARGE_STYLE = TextStyle(
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        letterSpacing = 0.15.sp
    )




}
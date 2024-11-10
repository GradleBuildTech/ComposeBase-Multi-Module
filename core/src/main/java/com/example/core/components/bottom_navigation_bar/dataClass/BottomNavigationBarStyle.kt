package com.example.core.components.bottom_navigation_bar.dataClass

import androidx.compose.animation.core.EaseInOut
import androidx.compose.animation.core.Easing
import androidx.compose.ui.graphics.Color

data class BottomNavigationBarStyle(
    val type: NavigationBarType = NavigationBarType.NONE,
    // Can be edit
    val selectedColor: Color = Color.Red,
    val unselectedColor: Color = Color.Gray,
    val backgroundSelectedColor: Color = Color.White,
    val backgroundUnSelectedColor: Color = Color.Transparent,
    val durationMillis: Int = 300,
    val easing: Easing = EaseInOut,
    val targetValueSelected: Float = 1f,
    val targetValueUnselected: Float = 0.8f
)
package com.example.core.components.bottom_navigation_bar

import androidx.compose.runtime.Composable

data class NavigationBarItemModel(
    val label: String,
    val idResourceIcon: Int,
    val screen: @Composable () -> Unit,
)
package com.example.core.components.bottom_navigation_bar.dataClass

import androidx.compose.runtime.Composable

data class NavigationBarItemModel(
    val label: String,
    val routeDestination: String = "",
    val idResourceIcon: Int,
    val screen: @Composable () -> Unit,
)
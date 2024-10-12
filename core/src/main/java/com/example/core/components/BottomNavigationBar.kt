package com.example.core.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.core.R

@Composable
fun RowScope.NavigationBarItem(
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    alwaysShowLabel: Boolean = true,
    item: @Composable () -> Unit,
    selectedItem: @Composable () -> Unit = item,
    label: @Composable (() -> Unit)? = null,
    selectedIconColor: Color = Color.Transparent,
    selectedTextColor: Color = Color.Transparent,
    indicatorColor: Color = Color.Transparent,
    unselectedIconColor: Color = Color.Transparent,
    unselectedTextColor: Color = Color.Transparent,
    disabledIconColor: Color = Color.Transparent,
    disabledTextColor: Color = Color.Transparent,

    ) {
    NavigationBarItem(
        selected = selected,
        onClick = onClick,
        icon = if (selected) selectedItem else item,
        modifier = modifier,
        enabled = enabled,
        label = label,
        alwaysShowLabel = alwaysShowLabel,
        colors = NavigationBarItemDefaults.colors(
            selectedIconColor = selectedIconColor,
            selectedTextColor = selectedTextColor,
            indicatorColor = indicatorColor,
            unselectedIconColor = unselectedIconColor,
            unselectedTextColor = unselectedTextColor,
            disabledIconColor = disabledIconColor,
            disabledTextColor = disabledTextColor,
        ),
    )
}

@Composable
fun BottomNavigationBar(
    modifier: Modifier = Modifier,
    containerColor: Color = colorResource(id = R.color.white),
    contentColor: Color = colorResource(id = R.color.black),
    tonalElevation: Dp = 0.dp,
    content: @Composable RowScope.() -> Unit,
) {
    NavigationBar(
        modifier = modifier,
        contentColor = contentColor,
        containerColor = containerColor,
        tonalElevation = tonalElevation,
        content = content,
    )
}
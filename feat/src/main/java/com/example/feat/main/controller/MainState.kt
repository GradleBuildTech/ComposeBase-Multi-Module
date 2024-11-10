package com.example.feat.main.controller

import com.example.core.components.bottom_navigation_bar.dataClass.NavigationBarItemModel
import com.example.core.lib.constants.Constants
import com.example.core.lib.constants.DrawableConst

data class MainState(
    val currentIndex: Int = 0,

    val tabs: List<NavigationBarItemModel> = listOf(
        NavigationBarItemModel(
            label = Constants.HOME_TAB,
            idResourceIcon = DrawableConst.HOME_ICON,
            screen = {},
        ),
        NavigationBarItemModel(
            label = Constants.DOCUMENT_TAB,
            idResourceIcon = DrawableConst.DOCUMENT_ICON,
            screen = {},
        ),
        NavigationBarItemModel(
            label = Constants.SEARCH_TAB,
            idResourceIcon = DrawableConst.SEARCH_ICON,
            screen = {},
        ),
        NavigationBarItemModel(
            label = Constants.CALENDAR_TAB,
            idResourceIcon = DrawableConst.CALENDAR_ICON,
            screen = {},
        ),
        NavigationBarItemModel(
            label = Constants.PROFILE_TAB,
            idResourceIcon = DrawableConst.PROFILE_ICON,
            screen = {},
        ),
    ),
)
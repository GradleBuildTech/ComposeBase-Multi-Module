package com.example.core.lib.utils.navigation

import com.example.core.components.bottom_navigation_bar.dataClass.NavigationBarItemModel
import com.example.core.lib.constants.Constants
import com.example.core.lib.constants.DrawableConst
import com.example.core.navigation.AppDecorator

val bottomNavigationTabs: List<NavigationBarItemModel> = listOf(
    NavigationBarItemModel(
        routeDestination = AppDecorator.HOME,
        label = Constants.HOME_TAB,
        idResourceIcon = DrawableConst.HOME_ICON,
        screen = {},
    ),
    NavigationBarItemModel(
        routeDestination = AppDecorator.DOCUMENT,
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
)

val bottomNavigationRoute: List<String> = listOf(
    AppDecorator.HOME,
    AppDecorator.DOCUMENT,
    "",
    "",
    "",
)
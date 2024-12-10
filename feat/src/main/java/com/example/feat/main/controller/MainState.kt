package com.example.feat.main.controller

import androidx.compose.runtime.Composable
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
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
    val navController: NavHostController? = null,
){
    val currentDestination: NavDestination?
        @Composable get() = navController
            ?.currentBackStackEntryAsState()?.value?.destination

//    val currentTopLevelDestination: TopLevelDestination?
//        @Composable get() {
//            return TopLevelDestination.entries.firstOrNull { topLevelDestination ->
//                currentDestination?.hasRoute(route = topLevelDestination.route) ?: false
//            }
//        }
}
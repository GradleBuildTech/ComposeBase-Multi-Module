package com.example.feat.main.controller

import androidx.compose.runtime.Composable
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState


data class MainState(
    val currentIndex: Int = 0,

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
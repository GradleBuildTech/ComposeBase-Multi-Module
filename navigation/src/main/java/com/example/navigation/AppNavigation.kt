package com.example.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.navigation.graph.DetailScreens
import com.example.navigation.graph.detailGraph
import kotlinx.coroutines.flow.collectLatest

@Composable
fun AppNavigation(
    navigator: Navigator,
    signInScreen: @Composable () -> Unit,
    detailScreensWithGraph: DetailScreens
) {
    val navController = rememberNavController()
    LaunchedEffect(Unit) {
        navigator.actions.collectLatest {
            when(it) {
                is Navigator.NavigationActions.Navigate -> {
                    navController.navigate(it.destination, builder = it.navOptions)
                }
                is Navigator.NavigationActions.Back -> {
                    navController.popBackStack()
                }
            }
        }
    }

    NavHost(
        navController = navController,
        startDestination = Destination.signIn.route
    ) {
        detailGraph(detailScreensWithGraph)
        composable(Destination.signIn.route) {
            signInScreen()
        }
    }
}
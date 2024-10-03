package com.example.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.feat.home.HomeScreen
import com.example.navigation.graph.DetailScreens
import com.example.navigation.graph.detailGraph
import kotlinx.coroutines.flow.collectLatest

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNavigation(
    navigator: Navigator,
    signInScreen: @Composable () -> Unit,
    detailScreensWithGraph: DetailScreens,
    isAuthState: Boolean = false
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

    LaunchedEffect(key1 = isAuthState) {
        if (isAuthState) {
//            navController.navigate(Destination.detailMain.route)
        } else {
            navController.navigate(Destination.signIn.route)
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
        composable(Destination.home.route) {
            HomeScreen()
        }

    }
}
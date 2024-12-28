package com.example.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.core.navigation.AppDecorator
import com.example.feat.course_detail.CourseDetailScreen
import com.example.feat.main.MainScreen
import com.example.feat.search.SearchScreen
import com.example.navigation.graph.DetailScreens
import com.example.navigation.graph.detailGraph
import kotlinx.coroutines.flow.collectLatest

@Composable
fun AppNavigation(
    navigator: Navigator,
    signInScreen: @Composable () -> Unit,
    tutorDetailScreen: @Composable (String) -> Unit,
    detailScreensWithGraph: DetailScreens,
    isAuthState: Boolean = false,
    bottomNavigationWrapper: @Composable (String?) -> Unit
) {
    val navController = rememberNavController()
    LaunchedEffect(Unit) {
        navigator.actions.collectLatest {
            when (it) {
                is Navigator.NavigationActions.Navigate -> {
                    navController.navigate(it.destination, builder = it.navOptions)
                    Log.d("AppNavigation", "Navigate to ${it.destination}")
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
        composable(Destination.main.route) {
            MainScreen()
        }
        composable(Destination.bottomWrapper.route) {
            bottomNavigationWrapper(navController.currentBackStackEntry?.destination?.route)
        }
        composable(Destination.search.route) {
            SearchScreen()
        }
        composable(Destination.courseDetail.route, arguments = Destination.courseDetail.arguments) {
            CourseDetailScreen(
                courseId = Destination.courseDetail.objectParser(navController.currentBackStackEntry!!)
            )
        }
        composable(Destination.tutorDetail.route, arguments = Destination.tutorDetail.arguments) {
            tutorDetailScreen(
                Destination.tutorDetail.objectParser(navController.currentBackStackEntry!!)
            )
        }
    }
}
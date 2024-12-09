package com.example.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.feat.document.DocumentScreen
import com.example.feat.home.HomeScreen

@Composable
fun LetTutorAppHost(
    navController: NavHostController,
) {
    NavHost(navController = navController, startDestination = Destination.home.route) {
        composable(Destination.home.route) {
            HomeScreen()
        }
        composable(Destination.document.route) {
            DocumentScreen()
        }
    }
}
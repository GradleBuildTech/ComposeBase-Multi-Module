package com.example.feat.main.controller

import androidx.navigation.NavHostController

sealed class MainEvent {
    data class TabSelected(val index: Int) : MainEvent()
    data class SetNavController(val navController: NavHostController) : MainEvent()
}
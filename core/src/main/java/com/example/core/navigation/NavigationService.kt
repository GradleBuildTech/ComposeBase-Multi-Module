package com.example.core.navigation
import androidx.navigation.NavOptionsBuilder

///✨ ================================================
///[NavigationService] is an interface that provides navigation functionality.
///This is used to navigate to a destination and go back.
///[navigateTo] is a function that navigates to a destination.
///[goBack] is a function that goes back.
///✨ ================================================

interface NavigationService {
    fun navigateTo(destination: String, navOptions: NavOptionsBuilder.() -> Unit = {})
    fun goBack()
}
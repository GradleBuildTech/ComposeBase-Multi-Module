package com.example.navigation.utils

import androidx.navigation.NamedNavArgument

/// NodeScreen is an interface that represents a screen with a route and arguments.
/// It is used to define the screens in the app.
/// [route] is a string that represents the route of the screen.
/// [arguments] is a list of NamedNavArgument that represents the arguments of the screen.
/// This is used to define the screens in the app.

interface NodeScreen {
    val route: String
    val arguments: List<NamedNavArgument>
}
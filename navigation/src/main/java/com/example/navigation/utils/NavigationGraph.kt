package com.example.navigation.utils

/// NavigationGraph is an interface that represents a navigation graph.
/// [route] is a string that represents the route of the navigation graph.
/// [startDestination] is a string that represents the start destination of the navigation graph.
/// This is used to define the navigation graphs in the app.

/// Example:
/// ```kotlin
/// object MainGraph: NavigationGraph {
///     override val route = "main"
///     override val startDestination = "home"
/// }

interface NavigationGraph {
    val route: String
    val startDestination: String
}
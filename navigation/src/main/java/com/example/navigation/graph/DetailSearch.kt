package com.example.navigation.graph

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import com.example.navigation.utils.ArgsScreen
import com.example.navigation.utils.DestinationRoute

/// DetailSearch is an object that represents a screen with a route and arguments.
/// It is used to define the screens in the app.
/// This is used to define the screens in the app.
/// Example:
/// ```kotlin
/// object DetailSearch: ArgsScreen<Unit> {
///     override val route = "detail/search"
///     override val arguments = emptyList()
/// }
/// ```


object DetailSearch: ArgsScreen<Unit> {
    override fun destination(arg: Unit): DestinationRoute = route

    override val route: String = "detail/search"

    override val arguments: List<NamedNavArgument> = emptyList()

    override fun objectParser(entry: NavBackStackEntry) {}
}
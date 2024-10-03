package com.example.navigation.utils

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry

///WithoutArgsScreen is a class that represents a screen with no arguments.
///It is used to define the screens in the app.
///[route] is a string that represents the route of the screen.
///This is used to define the screens in the app.
///Example
///```kotlin
///object SignIn: WithoutArgsScreen() {
///    override val route = "signIn"
///}
///```


abstract class WithoutArgsScreen : NodeScreen, NavDestination<Unit> {
    override val arguments: List<NamedNavArgument> get() = emptyList()

    override fun objectParser(entry: NavBackStackEntry) {}
    override fun destination(arg: Unit): DestinationRoute = route

}
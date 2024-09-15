package com.example.navigation.utils

/// ArgsScreen is an interface that represents a screen with a route and arguments.
/// It is used to define the screens in the app.
/// [T] is the type of the arguments of the screen.
/// This is used to define the screens in the app.

/// Example:
/// ```kotlin
/// object SignIn: ArgsScreen<SignInArgs>() {
///     override val route = "signIn"
///     override val arguments = SignInArgs.navArguments
/// }
/// ```


interface ArgsScreen<T>: NodeScreen, NavDestination<T>
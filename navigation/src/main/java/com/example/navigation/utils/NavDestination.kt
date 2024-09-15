package com.example.navigation.utils

import androidx.navigation.NavBackStackEntry

/// Represents a destination in the navigation graph
/// [T] is the type of the argument of the destination
/// [DestinationRoute] is the type of the route of the destination
/// [destination] is a function that takes an argument of type [T] and returns the route of the destination
/// [objectParser] is a function that takes a NavBackStackEntry and returns an object of type [T]


typealias DestinationRoute = String

interface NavDestination<T> {
    fun destination(arg: T): DestinationRoute
    fun objectParser(entry: NavBackStackEntry): T
}
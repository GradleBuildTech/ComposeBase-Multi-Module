package com.example.navigation.graph

import com.example.navigation.utils.NavigationGraph

/// DetailGraph is an object that represents the detail navigation graph.
/// [route] is a string that represents the route of the navigation graph.
/// [startDestination] is a string that represents the start destination of the navigation graph.
/// This is used to define the navigation graphs in the app.


object DetailGraph: NavigationGraph {
    override val route: String
        get() = "detailGraph"
    override val startDestination: String
        get() = detailMain.destination(Unit)

    val detailMain: DetailMain = DetailMain
    val detailSearch = DetailSearch
}
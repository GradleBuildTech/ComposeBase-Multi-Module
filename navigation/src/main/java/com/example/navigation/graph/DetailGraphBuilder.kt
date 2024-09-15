package com.example.navigation.graph

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import javax.annotation.concurrent.Immutable

/// Represents the detail graph
/// [detailMain] is the main detail screen
/// [detailSearch] is the search detail screen
/// This is used to define the detail graph in the app.
/// Example:
/// ```kotlin
/// val detailScreens = DetailScreens(
///     detailMain = { DetailMainScreen() },
///     detailSearch = { DetailSearchScreen() }
/// )
/// ```

@Immutable
data class DetailScreens(
    val detailMain: @Composable () -> Unit,
    val detailSearch: @Composable () -> Unit
)

/// Represents the detail graph
/// [route] is a string that represents the route of the navigation graph.
/// [startDestination] is a string that represents the start destination of the navigation graph.
/// This is used to define the navigation graphs in the app.

/// Example:
/// ```kotlin
/// object DetailGraph: NavigationGraph {
///     override val route = "detailGraph"
///     override val startDestination = detailMain.route
/// }
/// ```

internal fun NavGraphBuilder.detailGraph(
    screens: DetailScreens
) {
    navigation(
        startDestination = DetailGraph.startDestination,
        route = DetailGraph.route
    ) {
        composable(DetailGraph.detailMain.route){
            screens.detailMain()
        }

        composable(DetailGraph.detailSearch.route){
            screens.detailSearch()
        }
    }
}
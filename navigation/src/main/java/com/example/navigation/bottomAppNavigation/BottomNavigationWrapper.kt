package com.example.navigation.bottomAppNavigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.core.components.bottom_navigation_bar.BottomNavigationBar
import com.example.core.components.bottom_navigation_bar.dataClass.NavigationBarType
import com.example.core.lib.utils.navigation.bottomNavigationRoute
import com.example.core.lib.utils.navigation.bottomNavigationTabs

/// Wrapper for the bottom navigation bar

///How to use
/// ```kotlin
/// BottomNavigationWrapper(
///     body = {
///         // Your content here
///     },
///     currentDestination = currentDestination,
///     onChangeTab = { destination ->
///         navController.navigate(destination)
///     }
/// )
/// ```


@Composable
fun BottomNavigationWrapper(
    body: @Composable () -> Unit,
    currentDestination: String,
    onChangeTab: (String) -> Unit,
    paddingValues: PaddingValues = PaddingValues(20.dp, 10.dp, 20.dp, 15.dp)
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            BottomNavigationBar(
                tabs = bottomNavigationTabs,
                onItemClicked = { index ->
                    onChangeTab(bottomNavigationRoute[index])
                },
                type = NavigationBarType.LABEL,
                currentIndex = bottomNavigationRoute.indexOfFirst { it == currentDestination },
                paddingValues = paddingValues
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier.padding(innerPadding),
            contentAlignment = Alignment.Center,
        ) {
            body()
        }
    }
}
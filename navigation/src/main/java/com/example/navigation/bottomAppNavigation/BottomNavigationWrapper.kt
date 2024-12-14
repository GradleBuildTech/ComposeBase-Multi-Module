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

@Composable
fun BottomNavigationWrapper(
    body: @Composable () -> Unit,
    currentDestination: String,
    onChangeTab: (String) -> Unit
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
                paddingValues = PaddingValues(20.dp, 10.dp, 20.dp, 15.dp)
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
package com.example.feat.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.core.components.BottomNavigationBar
import com.example.core.components.NavigationBarItem
import com.example.feat.main.components.NavItem
import com.example.feat.main.controller.MainEvent
import com.example.feat.main.controller.MainViewModel

@Composable
fun MainScreen(
    mainViewModel: MainViewModel = hiltViewModel(),
) {
    val uiState by mainViewModel.uiState.collectAsState()

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        bottomBar = {
            BottomNavigationBar(
                tonalElevation = 0.3.dp
            ) {
                uiState.tabs.forEachIndexed { index, tab ->
                    NavigationBarItem(
                        item = {
                            NavItem(
                                contentDescription = tab,
                                iconPath = uiState.icons[index],
                                selected = uiState.currentIndex == index
                            )
                        },
                        selected = uiState.currentIndex == index,
                        onClick = {
                            mainViewModel.onEvent(MainEvent.ChangeTab(index));
                        },
                    )
                }
            }
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding))
    }
}
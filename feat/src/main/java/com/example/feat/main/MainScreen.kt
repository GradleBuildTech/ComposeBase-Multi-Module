package com.example.feat.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.core.R
import com.example.core.components.bottom_navigation_bar.BottomNavigationBar
import com.example.core.components.bottom_navigation_bar.dataClass.NavigationBarType
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
        /// TODO: uncomment this to use bottom bar
//        bottomBar = {
//            BottomNavigationBar(
//                tabs = uiState.tabs,
//                onItemClicked = { index ->
//                    mainViewModel.onEvent(MainEvent.TabSelected(index))
//                },
//                type = NavigationBarType.LABEL,
//                currentIndex = uiState.currentIndex,
//                paddingValues = PaddingValues(20.dp, 10.dp, 20.dp, 15.dp)
//            )
//        }
    ) { innerPadding ->
        /// TODO: for testing nav bar
        Box(
            modifier = Modifier.padding(innerPadding),
            contentAlignment = Alignment.Center,
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                val selectedColor = colorResource(id = R.color.primaryColor)
                val unselectedColor = colorResource(id = R.color.black)

                NavigationBarType.entries.forEach { type ->
                    Spacer(modifier = Modifier.size(20.dp))
                    Text(
                        text = "BOTTOM NAVIGATION BAR: $type",
                        style = MaterialTheme.typography.bodyMedium,
                    )
                    Spacer(modifier = Modifier.size(10.dp))
                    BottomNavigationBar(
                        tabs = uiState.tabs,
                        onItemClicked = { index ->
                            mainViewModel.onEvent(MainEvent.TabSelected(index))
                        },
                        type = type,
                        currentIndex = uiState.currentIndex,
                        paddingValues = PaddingValues(20.dp, 10.dp, 20.dp, 15.dp),
                        selectedColor = selectedColor,
                        unselectedColor = unselectedColor
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun MainScreenPreview() {
    MainScreen()
}
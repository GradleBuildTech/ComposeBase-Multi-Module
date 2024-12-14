package com.example.composebase

import BottomAppNavigation
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import com.example.auth.signIn.SignInScreen
import com.example.composebase.ui.theme.ComposeBaseTheme
import com.example.feat.document.DocumentScreen
import com.example.feat.home.HomeScreen
import com.example.feat.main.MainScreen
import com.example.guard.auth.AuthGuardController
import com.example.guard.auth.AuthStateData
import com.example.navigation.AppNavigation
import com.example.navigation.Navigator
import com.example.navigation.graph.DetailScreens
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SingleActivity: ComponentActivity() {
    @Inject
    lateinit var navigator: Navigator

    @Inject
    lateinit var bottomNavigator: Navigator

    @Inject
    lateinit var authGuardController: AuthGuardController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val autState = authGuardController.uiState.collectAsState()
            ComposeBaseTheme {
                AppNavigation(
                    navigator = navigator,
                    signInScreen = { SignInScreen() },
                    detailScreensWithGraph = DetailScreens(
                        detailMain = { Text(text ="Detail main") },
                        detailSearch = { Text(text = "Detail search") }
                    ),
                    isAuthState = autState.value.state == AuthStateData.AUTH,
                    bottomNavigationWrapper = {
                        MainScreen {
                            documentViewModel, homeViewModel ->
                            BottomAppNavigation(
                                navigator = bottomNavigator,
                                homeScreen = { HomeScreen(homeViewModel) },
                                documentScreen = { DocumentScreen(documentViewModel) }
                            )
                        }
                    }
                )
            }
        }
    }
}
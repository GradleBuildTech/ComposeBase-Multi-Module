package com.example.composebase

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import com.example.auth.presentation.signIn.SignInScreen
import com.example.composebase.ui.theme.ComposeBaseTheme
import com.example.navigation.AppNavigation
import com.example.navigation.Navigator
import com.example.navigation.graph.DetailScreens
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SingleActivity: ComponentActivity() {
    @Inject
    lateinit var navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeBaseTheme {
                AppNavigation(
                    navigator = navigator,
                    signInScreen = { SignInScreen() },
                    detailScreensWithGraph = DetailScreens(
                        detailMain = { Text(text ="Detail main") },
                        detailSearch = { Text(text = "Detail search") }
                    )
                )
            }
        }
    }
}
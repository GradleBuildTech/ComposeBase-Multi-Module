package com.example.auth.presentation.signIn

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.auth.presentation.signIn.controller.SignInStateData
import com.example.auth.presentation.signIn.controller.SignInViewModel

@Composable
fun SignInScreen(
    signInViewModel: SignInViewModel = hiltViewModel()
) {
    val uiState by signInViewModel.uiState.collectAsState()

    LaunchedEffect(uiState) {
        when (uiState.state) {
            SignInStateData.SUCCESS -> {
                // navigate to home
            }
            SignInStateData.ERROR -> {
                // show error message
            }
            else -> {
                // do nothing
            }
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        // SignInContent()
    }
}
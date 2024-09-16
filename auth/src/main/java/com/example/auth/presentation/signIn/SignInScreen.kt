package com.example.auth.presentation.signIn

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.auth.presentation.signIn.controller.SignInViewModel

@Composable
fun SignInScreen(
    signInViewModel: SignInViewModel = hiltViewModel()
) {
    Box(modifier = Modifier.fillMaxSize()) {
        // SignInContent()
    }
}
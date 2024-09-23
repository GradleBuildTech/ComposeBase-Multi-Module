package com.example.auth.presentation.signIn

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.auth.presentation.signIn.controller.SignInStateData
import com.example.auth.presentation.signIn.controller.SignInUiEvent
import com.example.auth.presentation.signIn.controller.SignInViewModel

@Composable
fun SignInScreen(
    signInViewModel: SignInViewModel = hiltViewModel()
) {
    val uiState by signInViewModel.uiState.collectAsState()

    LaunchedEffect(uiState) {
        when (uiState.state) {
            SignInStateData.SUCCESS -> {
                Log.d("SignInScreen", "Sign in success");
            }
            SignInStateData.ERROR -> {
                Log.d("SignInScreen", "Sign in error ${uiState.errorMessage}");
            }
            else -> {
                // do nothing
            }
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column {
            Button(onClick = {
                signInViewModel.onEvent(
                    SignInUiEvent.SignIn(
                        username = "phhai@ymail.com",
                        password = "123456"
                    )
                )
            }) {
                Text(text = "Test sign in")
            }
            if (uiState.state == SignInStateData.LOADING) {
                Text(
                    text = "Loading...",
                    modifier = Modifier.fillMaxSize(),
                    style = TextStyle(color = Color.Red )
                )
            }
        }
    }
}
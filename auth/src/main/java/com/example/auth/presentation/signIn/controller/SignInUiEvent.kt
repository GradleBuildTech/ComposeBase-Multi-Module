package com.example.auth.presentation.signIn.controller

sealed class SignInUiEvent {
    data class SignIn(val username: String, val password: String) : SignInUiEvent()
}
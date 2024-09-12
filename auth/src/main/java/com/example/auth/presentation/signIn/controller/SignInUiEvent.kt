package com.example.auth.presentation.signIn.controller

sealed  class SignInUiEvent {
    data object SignIn : SignInUiEvent()
}
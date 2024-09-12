package com.example.auth.presentation.signIn.controller

import javax.annotation.concurrent.Immutable

@Immutable
data class SigInUiState(
    val state: SignInStateData
)
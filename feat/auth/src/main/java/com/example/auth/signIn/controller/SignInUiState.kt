package com.example.auth.signIn.controller
import javax.annotation.concurrent.Immutable

@Immutable
data class SigInUiState(
    val state: SignInStateData,
    val errorMessage: String = ""
)
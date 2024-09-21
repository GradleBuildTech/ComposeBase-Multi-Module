package com.example.guard.auth

data class AuthGuardState(
    val state: AuthStateData = AuthStateData.UN_AUTH,
)
package com.example.guard.auth

/// AuthGuardAction is a sealed class that represents the possible actions that can be taken by the AuthGuard feature.
/// Auth: Represents the action to authenticate the user.
/// UnAuth: Represents the action to unauthenticate the user.
/// CheckAuth: Represents the action to check the authentication status of the user.

sealed class  AuthGuardAction {
    data object Auth : AuthGuardAction()
    data object UnAuth : AuthGuardAction()
    data object CheckAuth : AuthGuardAction()
}
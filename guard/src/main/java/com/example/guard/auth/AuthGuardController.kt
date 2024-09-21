package com.example.guard.auth

import androidx.lifecycle.viewModelScope
import com.example.core.presentation.StateAndEventViewModel
import com.example.local.dataStore.TokenLocalService
import kotlinx.coroutines.launch
import javax.inject.Inject

///✨===============================================
///[AuthGuardController] is a class that extends [StateAndEventViewModel].
///This class is used to handle the state and events in the view model.
///[AuthGuardState] is the state of the view model.
///[AuthGuardAction] is the event of the view model.
///[tokenLocalService] is a service to access the token.
///✨===============================================

/// How to use?
/// 1. Create a new instance of [AuthGuardController].
/// 2. Call [onEvent] to handle the event.
/// 3. Observe the [uiState] to get the state.

class AuthGuardController @Inject constructor(
    private val tokenLocalService: TokenLocalService
) : StateAndEventViewModel<AuthGuardState, AuthGuardAction>(
    AuthGuardState()
){

    override suspend fun handleEvent(event: AuthGuardAction) {
        when(event) {
            AuthGuardAction.CheckAuth -> checkAuth()
            AuthGuardAction.Auth -> auth()
            AuthGuardAction.UnAuth -> unAuth()
        }
    }

    private fun checkAuth() {
        setUiState { copy(state = AuthStateData.BLOCK_CHANGE_STATE) }
        viewModelScope.launch {
            val token = tokenLocalService.getAccessToken()
            if (token?.isEmpty() == true) {
                setUiState { copy(state = AuthStateData.UN_AUTH) }
            } else {
                setUiState { copy(state = AuthStateData.AUTH) }
            }
        }
    }


    private fun auth() {
        setUiState { copy(state = AuthStateData.BLOCK_CHANGE_STATE) }
        setUiState { copy(state = AuthStateData.AUTH) }
    }

    private fun unAuth() {
        setUiState { copy(state = AuthStateData.BLOCK_CHANGE_STATE) }
        setUiState { copy(state = AuthStateData.UN_AUTH) }
    }
}
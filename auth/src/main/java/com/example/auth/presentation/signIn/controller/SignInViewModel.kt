package com.example.auth.presentation.signIn.controller

import androidx.lifecycle.viewModelScope
import com.example.core.navigation.NavigationService
import com.example.core.presentation.StateAndEventViewModel
import com.example.domain.model.Token
import com.example.domain.usecase.auth.SignInUseCase
import com.example.local.dataStore.TokenLocalService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase,
    private val tokenLocalService: TokenLocalService,
    private val navigationService: NavigationService
) : StateAndEventViewModel<SigInUiState, SignInUiEvent>(SigInUiState(state = SignInStateData.IDLE)) {


    override suspend fun handleEvent(event: SignInUiEvent) {
        when (event) {
            is SignInUiEvent.SignIn -> handleLogin(event.username, event.password)
        }
    }

    private fun handleLogin(username: String, password: String) {
        viewModelScope.launch {
            setUiState { copy(state = SignInStateData.LOADING) }
            signInUseCase.signIn(username, password)
                .collect { either ->
                    if (either.isRight()) {
                        val token = either.rightValue<Token>()
                        tokenLocalService.setToken(
                            accessToken = token?.accessToken ?: "",
                            refreshToken = token?.refreshToken ?: ""
                        )
                        setUiState { copy(state = SignInStateData.SUCCESS) }
                    } else if (either.isLeft()) {
                        setUiState {
                            copy(
                                state = SignInStateData.ERROR,
                                errorMessage = errorMessage
                            )
                        }
                    }
                }
        }
    }
}
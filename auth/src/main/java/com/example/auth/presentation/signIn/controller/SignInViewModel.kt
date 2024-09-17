package com.example.auth.presentation.signIn.controller

import androidx.lifecycle.viewModelScope
import com.example.auth.domain.usecase.SignInUseCase
import com.example.core.presentation.StateAndEventViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase
) : StateAndEventViewModel<SigInUiState, SignInUiEvent>(SigInUiState(state = SignInStateData.IDLE)) {
    override suspend fun handleEvent(event: SignInUiEvent) {

    }

    private fun handLogin(username: String, password: String) {
        viewModelScope.launch {
            setUiState { copy(state = SignInStateData.LOADING) }
            signInUseCase.signIn(username, password)
                .collect { either ->
                    if (either.isRight()) {
                        setUiState { copy(state = SignInStateData.SUCCESS) }
                    } else if (either.isLeft()) {
                        setUiState { copy(state = SignInStateData.ERROR) }
                    }
                }
        }

    }
}
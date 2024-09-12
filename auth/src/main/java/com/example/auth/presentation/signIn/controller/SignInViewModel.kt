package com.example.auth.presentation.signIn.controller

import com.example.auth.domain.usecase.SignInUseCase
import com.example.core.presentation.StateAndEventViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase
) : StateAndEventViewModel<SigInUiState, SignInUiEvent>(SigInUiState(state = SignInStateData.IDLE)) {
    override suspend fun handleEvent(event: SignInUiEvent) {
        ///TODO: handle function for each event
    }
}
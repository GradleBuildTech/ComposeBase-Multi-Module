package com.example.feat.tutorDetail.controller

import androidx.lifecycle.viewModelScope
import com.example.core.navigation.NavigationService
import com.example.core.presentation.StateAndEventViewModel
import com.example.domain.usecase.tutorDetail.TutorDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TutorDetailViewModel @Inject constructor(
    private val tutorDetailUseCase: TutorDetailUseCase,
    private val navigationService: NavigationService
) : StateAndEventViewModel<TutorDetailUiState, TutorDetailUiEvent>(
    TutorDetailUiState()
) {
    override suspend fun handleEvent(event: TutorDetailUiEvent) {
        when (event) {
            is TutorDetailUiEvent.FetchTutorDetail -> fetchTutorDetail(event.tutorId)
            is TutorDetailUiEvent.OnBackPreviousScreen -> onBackPreviousScreen()
        }
    }

    private fun fetchTutorDetail(tutorId: String) {
        if(uiState.value.isLoading) return
        if(uiState.value.tutorDetail != null) return
        setUiState { copy(isLoading = true) }
        viewModelScope.launch {
            tutorDetailUseCase.getTutorDetail(tutorId).collect { either ->
                if (either.isRight()) {
                    setUiState { copy(tutorDetail = either.rightValue()) }
                } else {
                    setUiState { copy(errorMessage = either.leftValue()?.errorMessage) }
                }
                setUiState { copy(isLoading = false) }
            }
        }
    }

    private fun onBackPreviousScreen() {
        navigationService.goBack()
    }
}
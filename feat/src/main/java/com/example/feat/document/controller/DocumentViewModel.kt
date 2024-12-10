package com.example.feat.document.controller

import androidx.lifecycle.viewModelScope
import com.example.core.lib.constants.Constants.TUTOR_LIMIT_ITEM
import com.example.core.models.pagination.PaginationRequest
import com.example.core.models.stateData.Either
import com.example.core.models.stateData.ExceptionState
import com.example.core.presentation.StateAndEventViewModel
import com.example.domain.entity.BookingInfoEntity
import com.example.domain.entity.TutorFavorites
import com.example.domain.usecase.document.DocumentUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class DocumentViewModel @Inject constructor(
    private val documentUseCase: DocumentUseCase,
) : StateAndEventViewModel<DocumentUiState, DocumentUiEvent>(initialState = DocumentUiState()) {
    init {
        loadInitialData(
            initialLoadState = { copy(isLoading = true) },
            errorLoadState = { _ -> copy(isLoading = false) },
            listRequest = listOf(
                documentUseCase.fetchUpComing(time = Date().time),
                documentUseCase.fetchTotalTime(),
                documentUseCase.fetchTutors(
                    paginationRequest = PaginationRequest(
                        page = 1,
                        pageSize = TUTOR_LIMIT_ITEM,
                    )
                )
            )
        ) { response, responseIndex ->
            when (responseIndex) {
                0 -> updateStateWithUpComing(response)
                1 -> updateStateWithTotalTime(response)
                2 -> updateStateWithTutors(response)
            }
        }
    }

    override suspend fun handleEvent(event: DocumentUiEvent) {
        when (event) {
            is DocumentUiEvent.AddFavoriteTutor -> addFavoriteTutor(event.tutorId)
            else -> {}
        }
    }

    private fun addFavoriteTutor(tutorId: String) {
        viewModelScope.launch {
            val oldTutors = uiState.value.tutors
            val newTutors = oldTutors.map {
                if (it.id == tutorId) {
                    it.copy(isFavorite = !(it.isFavorite ?: false))
                } else {
                    it
                }
            }
            setUiState { copy(tutors = newTutors) }

            documentUseCase.addFavoriteTutor(tutorId)
                .collect { either ->
                    if(either.isLeft()) {
                        setUiState { copy(tutors = oldTutors) }
                    }
                }
        }
    }

    private fun updateStateWithUpComing(either: Either<ExceptionState, Any>) {
        if (either.isRight()) {
            setUiState {
                copy(
                    bookingInfo = either.rightValue() as BookingInfoEntity,
                )
            }
        }
    }

    private fun updateStateWithTotalTime(either: Either<ExceptionState, Any>) {
        if (either.isRight()) {
            setUiState {
                copy(
                    totalTime = either.rightValue() as Int
                )
            }
        }
    }

    private fun updateStateWithTutors(either: Either<ExceptionState, Any>) {
        if (either.isRight()) {
            val tutors = (either.rightValue() as TutorFavorites).tutors
            val favoriteTutors = (either.rightValue() as TutorFavorites).favoriteTutors

            for (tutor in tutors) {
                tutor.isFavorite = favoriteTutors.any { it.secondId == tutor.id }
            }

            setUiState {
                copy(
                    tutors = tutors,
                    favoriteTutors = favoriteTutors,
                    isLoading = false,
                )
            }
        } else {
            setUiState { copy(isLoading = false) }
        }
    }
}
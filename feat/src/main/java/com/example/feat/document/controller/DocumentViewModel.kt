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
import kotlinx.coroutines.Delay
import kotlinx.coroutines.launch
import java.util.Date
import java.util.concurrent.Future
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
            is DocumentUiEvent.FetchTutors -> pageFetchTutorial()
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
            val tutorResponse = (either.rightValue() as TutorFavorites)
            val tutors = tutorResponse.tutors
            val favoriteTutors = tutorResponse.favoriteTutors

            for (tutor in tutors) {
                tutor.isFavorite = favoriteTutors.any { it.secondId == tutor.id }
            }

            setUiState {
                copy(
                    tutors = tutors,
                    favoriteTutors = favoriteTutors,
                    isLoading = false,
                    totalPage = tutorResponse.count
                )
            }
        } else {
            setUiState { copy(isLoading = false) }
        }
    }

    private fun pageFetchTutorial() {
        if(uiState.value.isLoading) return
        val currentPage = uiState.value.currentPage
        val totalPage = uiState.value.totalPage
        if((currentPage * TUTOR_LIMIT_ITEM) >= totalPage) return
        viewModelScope.launch {
            setUiState { copy(isLoading = true) }
            if (currentPage < totalPage) {
                documentUseCase.fetchTutors(
                    paginationRequest = PaginationRequest(
                        page = currentPage + 1,
                        pageSize = TUTOR_LIMIT_ITEM,
                    )
                ).collect { either ->
                    if (either.isRight()) {
                        val tutors = (either.rightValue() as TutorFavorites).tutors
                        val favoriteTutors = (either.rightValue() as TutorFavorites).favoriteTutors

                        setUiState {
                            copy(
                                tutors = listOf(uiState.value.tutors, tutors).flatten(),
                                favoriteTutors = favoriteTutors,
                                currentPage = currentPage + 1,
                                isLoading = false,
                                totalPage = totalPage
                            )
                        }
                    }
                }
            }
        }
    }
}
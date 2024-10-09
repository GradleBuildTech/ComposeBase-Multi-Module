package com.example.feat.home.controller

import androidx.lifecycle.viewModelScope
import com.example.core.lib.constants.Constants
import com.example.core.models.pagination.PaginationRequest
import com.example.core.presentation.StateAndEventViewModel
import com.example.domain.entity.CourseEntity
import com.example.domain.entity.EBookEntity
import com.example.domain.entity.TutorEntity
import com.example.domain.usecase.home.HomeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeUseCase: HomeUseCase,
) : StateAndEventViewModel<HomeState, HomeEvent>(initialState = HomeState()){
    override suspend fun handleEvent(event: HomeEvent) {
        when(event){
            is HomeEvent.LoadTutors -> loadTutors(paginationRequest = event.paginationRequest)
            is HomeEvent.LoadRecommendedCourses -> loadRecommendedCourses(paginationRequest = event.paginationRequest)
            is HomeEvent.LoadEBooks -> loadEBooks(paginationRequest = event.paginationRequest)
        }
    }

    init {
        onEvent(
            HomeEvent.LoadTutors(
                paginationRequest = PaginationRequest(
                    page = 1,
                    pageSize = Constants.TUTOR_LIMIT_ITEM
                )
            )
        )
        onEvent(
            HomeEvent.LoadRecommendedCourses(
                paginationRequest = PaginationRequest(
                    page = 1,
                    pageSize = Constants.COURSE_LIMIT_ITEM
                )
            )
        )
        onEvent(
            HomeEvent.LoadEBooks(
                paginationRequest = PaginationRequest(
                    page = 1,
                    pageSize = Constants.EBOOK_LIMIT_ITEM
                )
            )
        )
    }

    private fun loadTutors(paginationRequest: PaginationRequest) {
        if (uiState.value.isLoadingTutors) return

        viewModelScope.launch {
            setUiState { copy(isLoadingTutors = true) }
            homeUseCase.fetchTutors(paginationRequest = paginationRequest)
                .collect { either ->
                    if (either.isRight()) {
                        val tutors = either.rightValue()
                        setUiState { copy(isLoadingTutors = false, tutors = tutors as List<TutorEntity>) }
                    } else if (either.isLeft()) {
                        setUiState { copy(isLoadingTutors = false) }
                    }
                }
        }
    }

    private fun loadRecommendedCourses(paginationRequest: PaginationRequest) {
        if (uiState.value.isLoadingRecommendedCourses) return

        viewModelScope.launch {
            setUiState { copy(isLoadingRecommendedCourses = true) }
            homeUseCase.fetchRecommendedCourses(paginationRequest = paginationRequest)
                .collect { either ->
                    if (either.isRight()) {
                        val recommendedCourses = either.rightValue()
                        setUiState { copy(isLoadingRecommendedCourses = false, recommendedCourses = recommendedCourses as List<CourseEntity>) }
                    } else if (either.isLeft()) {
                        setUiState { copy(isLoadingRecommendedCourses = false) }
                    }
                }
        }
    }

    private fun loadEBooks(paginationRequest: PaginationRequest) {
        if (uiState.value.isLoadingEBooks) return

        viewModelScope.launch {
            setUiState { copy(isLoadingEBooks = true) }
            homeUseCase.fetchEBooks(paginationRequest = paginationRequest)
                .collect { either ->
                    if (either.isRight()) {
                        val eBooks = either.rightValue()
                        setUiState { copy(isLoadingEBooks = false, eBooks = eBooks as List<EBookEntity>) }
                    } else if (either.isLeft()) {
                        setUiState { copy(isLoadingEBooks = false) }
                    }
                }
        }
    }
}
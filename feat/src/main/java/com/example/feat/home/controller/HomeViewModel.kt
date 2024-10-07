package com.example.feat.home.controller

import androidx.lifecycle.viewModelScope
import com.example.core.lib.constants.Constants
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
            is HomeEvent.LoadTutors -> loadTutors(page=event.page, pageSize=event.pageSize)
            is HomeEvent.LoadRecommendedCourses -> loadRecommendedCourses(page=event.page, pageSize=event.pageSize)
            is HomeEvent.LoadEBooks -> loadEBooks(page=event.page, pageSize=event.pageSize)
        }
    }

    init {
        onEvent(HomeEvent.LoadTutors(page = 1, pageSize = Constants.TUTOR_LIMIT_ITEM))
        onEvent(HomeEvent.LoadRecommendedCourses(page = 1, pageSize = Constants.COURSE_LIMIT_ITEM))
        onEvent(HomeEvent.LoadEBooks(page = 1, pageSize = Constants.EBOOK_LIMIT_ITEM))
    }

    private fun loadTutors(page: Int, pageSize: Int) {
        viewModelScope.launch {
            setUiState { copy(isLoadingTutors = true) }
            homeUseCase.fetchTutors(page, pageSize)
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

    private fun loadRecommendedCourses(page: Int, pageSize: Int) {
        viewModelScope.launch {
            setUiState { copy(isLoadingRecommendedCourses = true) }
            homeUseCase.fetchRecommendedCourses(page, pageSize)
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

    private fun loadEBooks(page: Int, pageSize: Int) {
        viewModelScope.launch {
            setUiState { copy(isLoadingEBooks = true) }
            homeUseCase.fetchEBooks(page, pageSize)
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
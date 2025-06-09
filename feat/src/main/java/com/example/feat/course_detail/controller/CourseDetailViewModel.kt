package com.example.feat.course_detail.controller

import androidx.lifecycle.viewModelScope
import com.example.core.navigation.NavigationService
import com.example.core.presentation.StateAndEventViewModel
import com.example.domain.usecase.course_detail.CourseDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CourseDetailViewModel @Inject constructor(
    private val courseDetailUseCase: CourseDetailUseCase,
    private val navigationService: NavigationService
) : StateAndEventViewModel<CourseDetailUiState, CourseDetailUiEvent>(initialState = CourseDetailUiState()) {
    override suspend fun handleEvent(event: CourseDetailUiEvent) {
        when (event) {
            is CourseDetailUiEvent.FetchCourseDetail -> fetchCourseDetail(event.id)
            is CourseDetailUiEvent.OnBackPreviousScreen -> onBackPreviousScreen()
        }
    }

    private fun onBackPreviousScreen() {
        navigationService.goBack()
    }

    private fun fetchCourseDetail(id: String) {
        setUiState { copy(isLoading = true) }
        viewModelScope.launch {
            courseDetailUseCase.fetchCourseDetail(id).collect { either ->
                if (either.isRight()) {
                    setUiState { copy(courseDetail = either.rightValue()) }
                }
                setUiState { copy(isLoading = false) }
            }
        }
    }

}
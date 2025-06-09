package com.example.feat.home.controller

import androidx.lifecycle.viewModelScope
import com.example.core.extensions.checkItemsAre
import com.example.core.lib.constants.Constants
import com.example.core.models.pagination.PaginationRequest
import com.example.core.models.stateData.Either
import com.example.core.models.stateData.ExceptionState
import com.example.core.network.WhileSubscribedOrRetained
import com.example.core.presentation.StateAndEventViewModel
import com.example.domain.entity.EBookEntity
import com.example.domain.entity.course.CourseEntity
import com.example.domain.entity.tutor.TutorEntity
import com.example.domain.usecase.home.HomeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class HomeViewModel @Inject constructor(
    homeUseCase: HomeUseCase,
) : StateAndEventViewModel<HomeState, HomeEvent>(initialState = HomeState()) {

    private val recommendedCoursesFlow = homeUseCase.fetchRecommendedCourses(createPaginationRequest(Constants.COURSE_LIMIT_ITEM))
        .stateIn(
            scope = viewModelScope,
            started = WhileSubscribedOrRetained,
            initialValue = Either.Left(ExceptionState)
        )

    private val tutorsFlow = homeUseCase.fetchTutors(createPaginationRequest(Constants.TUTOR_LIMIT_ITEM))
        .stateIn(
            scope = viewModelScope,
            started = WhileSubscribedOrRetained,
            initialValue = Either.Left(ExceptionState)
        )

    private val eBooksFlow = homeUseCase.fetchEBooks(createPaginationRequest(Constants.EBOOK_LIMIT_ITEM))
        .stateIn(
            scope = viewModelScope,
            started = WhileSubscribedOrRetained,
            initialValue = Either.Left(ExceptionState)
        )

    init {
        // Combine 3 flows để update UI state
        viewModelScope.launch {
            combine(
                recommendedCoursesFlow,
                tutorsFlow,
                eBooksFlow
            ) { courses, tutors, ebooks ->
                setUiState {
                    copy(
                        recommendedCourses = courses.rightValue()?.checkItemsAre<CourseEntity>() ?: emptyList(),
                        tutors = tutors.rightValue()?.checkItemsAre<TutorEntity>() ?: emptyList(),
                        eBooks = ebooks.rightValue()?.checkItemsAre<EBookEntity>() ?: emptyList(),
                        isLoading = false
                    )
                }
            }.collect {
                // Collect the combined state and update UI state
            }
        }
    }

    override suspend fun handleEvent(event: HomeEvent) {
        // handle events as needed
    }

    private fun createPaginationRequest(limit: Int) = PaginationRequest(page = 1, pageSize = limit)
}

package com.example.feat.home.controller

import com.example.core.extensions.checkItemsAre
import com.example.core.extensions.toListEither
import com.example.core.lib.constants.Constants
import com.example.core.models.pagination.PaginationRequest
import com.example.core.models.stateData.Either
import com.example.core.models.stateData.ExceptionState
import com.example.core.presentation.StateAndEventViewModel
import com.example.domain.usecase.home.HomeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    homeUseCase: HomeUseCase,
) : StateAndEventViewModel<HomeState, HomeEvent>(initialState = HomeState()) {

    init {
        loadInitialData(
            initialLoadState = { copy(isLoading = true) },
            errorLoadState = { _ -> copy(isLoading = false) },
            listRequest = listOf(
                homeUseCase.fetchRecommendedCourses(createPaginationRequest(Constants.COURSE_LIMIT_ITEM)),
                homeUseCase.fetchTutors(createPaginationRequest(Constants.TUTOR_LIMIT_ITEM)),
                homeUseCase.fetchEBooks(createPaginationRequest(Constants.EBOOK_LIMIT_ITEM))
            )
        ) { response, responseIndex ->
            when (responseIndex) {
                0 -> updateStateWithCourses(response.toListEither())
                1 -> updateStateWithTutors(response.toListEither())
                2 -> updateStateWithEBooks(response.toListEither())
            }
        }
    }

    override suspend fun handleEvent(event: HomeEvent) {
    }

    private fun createPaginationRequest(limit: Int) = PaginationRequest(page = 1, pageSize = limit)


    private fun updateStateWithCourses(either: Either<ExceptionState, List<Any>>) {
        if (either.isRight()) {
            setUiState {
                copy(
                    recommendedCourses = either.rightValue()?.checkItemsAre() ?: emptyList()
                )
            }
        }
    }

    private fun updateStateWithEBooks(either: Either<ExceptionState, List<Any>>) {
        if (either.isRight()) {
            setUiState {
                copy(
                    eBooks = either.rightValue()?.checkItemsAre() ?: emptyList(),
                    isLoading = false
                )
            }
        } else {
            setUiState { copy(isLoading = false) }
        }
    }

    private fun updateStateWithTutors(either: Either<ExceptionState, List<Any>>) {
        if (either.isRight()) {
            setUiState { copy(tutors = either.rightValue()?.checkItemsAre() ?: emptyList()) }
        }
    }

}

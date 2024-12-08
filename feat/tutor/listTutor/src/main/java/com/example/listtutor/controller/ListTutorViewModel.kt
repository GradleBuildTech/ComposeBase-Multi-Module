package com.example.listtutor.controller

import com.example.core.extensions.checkItemsAre
import com.example.core.extensions.toListEither
import com.example.core.models.pagination.PaginationRequest
import com.example.core.models.stateData.Either
import com.example.core.models.stateData.ExceptionState
import com.example.core.presentation.StateAndEventViewModel
import com.example.domain.usecase.tutor.ListTutorUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ListTutorViewModel @Inject constructor(
    listTutorUseCase: ListTutorUseCase,
) : StateAndEventViewModel<ListTutorState, ListTutorEvent>(
    initialState = ListTutorState()
) {
    init {
        loadInitialData(
            initialLoadState = { copy(isLoading = true) },
            errorLoadState = { _ -> copy(isLoading = false) },
            listRequest = listOf(
                listTutorUseCase.fetchTutors(paginationRequest = PaginationRequest(
                    page = 1,
                    pageSize = 10
                ))
            )
        ) { response, responseIndex ->
            when (responseIndex) {
                0 -> updateStateWithTutors(response.toListEither())
            }
        }
    }

    override suspend fun handleEvent(event: ListTutorEvent) {
    }

    private fun updateStateWithTutors(either: Either<ExceptionState, List<Any>>) {
        if (either.isRight()) {
            setUiState { copy(tutors = either.rightValue()?.checkItemsAre() ?: emptyList()) }
        }
    }
}
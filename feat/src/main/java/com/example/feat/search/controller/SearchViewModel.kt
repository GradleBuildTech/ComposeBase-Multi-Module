package com.example.feat.search.controller

import androidx.lifecycle.viewModelScope
import com.example.core.extensions.checkItemsAre
import com.example.core.extensions.toListEither
import com.example.core.lib.constants.Constants.COURSE_LIMIT_ITEM
import com.example.core.models.pagination.PaginationRequest
import com.example.core.models.stateData.Either
import com.example.core.models.stateData.ExceptionState
import com.example.core.presentation.StateAndEventViewModel
import com.example.domain.usecase.search.SearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchUseCase: SearchUseCase,
) : StateAndEventViewModel<SearchUiState, SearchUiEvent>(SearchUiState()) {
    init {
        loadInitialData(
            initialLoadState = { copy(isLoading = true) },
            errorLoadState = { _ -> copy(isLoading = false) },
            listRequest = listOf(
                searchUseCase.fetchCourses(
                    paginationRequest = PaginationRequest(
                        page = 1,
                        pageSize = COURSE_LIMIT_ITEM,
                    ),
                ),
                searchUseCase.fetchContentCategories()
            )
        ) { response, responseIndex ->
            when (responseIndex) {
                0 -> updateStateWithCourses(response.toListEither())
                1 -> updateStateWithContentCategories(response.toListEither())
            }
        }
    }

    private fun updateStateWithContentCategories(toListEither: Either<ExceptionState, List<Any>>) {
        if (toListEither.isRight()) {
            setUiState {
                copy(
                    contentCategories = toListEither.rightValue()?.checkItemsAre() ?: emptyList(),
                    isLoading = false
                )
            }
        } else {
            setUiState { copy(isLoading = false) }
        }
    }

    private fun updateStateWithCourses(either: Either<ExceptionState, List<Any>>) {
        if (either.isRight()) {
            setUiState {
                copy(
                    courses = either.rightValue()?.checkItemsAre() ?: emptyList(),
                )
            }
        }
    }

    override suspend fun handleEvent(event: SearchUiEvent) {
        when (event) {
            is SearchUiEvent.OnSelectedContentCategory -> onSelectedContentCategory(event)
            is SearchUiEvent.OnApplyFilter -> onApplyFilter()
            is SearchUiEvent.OnSearchSubmitted -> onSearchSubmitted(event)
            is SearchUiEvent.OnRefresh -> fetchCourse()
        }
    }

    private fun onSearchSubmitted(event: SearchUiEvent.OnSearchSubmitted) {
        viewModelScope.launch {
            setUiState { copy(searchText = event.searchText) }
            fetchCourse()
        }
    }

    private fun onApplyFilter() {
        viewModelScope.launch {
            fetchCourse()
        }
    }

    private suspend fun fetchCourse() {
        setUiState { copy(isLoading = true) }

        val contentCategoryId = uiState.value.selectedContentCategory?.id
        val searchText = uiState.value.searchText
        val paginationRequest = PaginationRequest(
            page = 1,
            pageSize = COURSE_LIMIT_ITEM,
        )

        searchUseCase.fetchCourses(
            paginationRequest = paginationRequest,
            searchQuery = searchText,
            contentCategoryId = contentCategoryId,
        ).collect { either ->
            if (either.isRight()) {
                setUiState {
                    copy(
                        courses = either.rightValue()?.checkItemsAre() ?: emptyList(),
                        isLoading = false
                    )
                }
            } else {
                setUiState { copy(isLoading = false) }
            }
        }
    }

    private fun onSelectedContentCategory(event: SearchUiEvent.OnSelectedContentCategory) {
        val contentCategories = uiState.value.contentCategories.toMutableList()
        val selectedContentCategory = uiState.value.selectedContentCategory

        contentCategories.forEach {
            if (it == selectedContentCategory) {
                it.selected = false
            } else {
                it.selected = it == event.contentCategory
            }

        }

        setUiState {
            copy(
                contentCategories = contentCategories,
                selectedContentCategory = if (selectedContentCategory == event.contentCategory) null else event.contentCategory
            )
        }
    }
}
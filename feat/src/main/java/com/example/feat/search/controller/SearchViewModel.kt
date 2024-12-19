package com.example.feat.search.controller

import androidx.lifecycle.viewModelScope
import com.example.core.extensions.checkItemsAre
import com.example.core.extensions.toListEither
import com.example.core.lib.constants.Constants.COURSE_LIMIT_ITEM
import com.example.core.models.pagination.PaginationRequest
import com.example.core.models.stateData.Either
import com.example.core.models.stateData.ExceptionState
import com.example.core.navigation.AppDecorator
import com.example.core.navigation.NavigationService
import com.example.core.presentation.StateAndEventViewModel
import com.example.domain.usecase.search.SearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.example.core.extensions.debounce as debounce

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchUseCase: SearchUseCase,
    private val navigationService: NavigationService
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
            is SearchUiEvent.OnBottomSheetDismissed -> onBottomSheetDismissed()
            is SearchUiEvent.OnClickCourseItem -> onClickCourseItem()
        }
    }

    private fun onClickCourseItem() {
        navigationService.navigateTo(AppDecorator.COURSE_DETAIL)
    }

    private fun onBottomSheetDismissed() {
        if(uiState.value.currentContentCategory != uiState.value.selectedContentCategory) {
            setUiState { copy(selectedContentCategory = uiState.value.currentContentCategory) }
            viewModelScope.launch {
                fetchCourse()
            }
        }
    }

    private val debouncedOnSearchSubmitted: (SearchUiEvent.OnSearchSubmitted) -> Unit =
        debounce(
            coroutineScope = viewModelScope
        ) { event ->
            // This block will only execute after the debounce delay
            viewModelScope.launch {
                setUiState { copy(searchText = event.searchText) }
                fetchCourse()
            }
        }

    // Function to handle the search submission
    private fun onSearchSubmitted(event: SearchUiEvent.OnSearchSubmitted) {
        debouncedOnSearchSubmitted(event)
    }

    private fun onApplyFilter() {
        setUiState { copy(currentContentCategory = selectedContentCategory) }

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
        val selectedContentCategory = uiState.value.selectedContentCategory

        if (event.contentCategory == selectedContentCategory) {
            setUiState {
                copy(selectedContentCategory = null)
            }
            return
        }

        setUiState {
            copy(
                selectedContentCategory = event.contentCategory,
            )
        }
    }
}
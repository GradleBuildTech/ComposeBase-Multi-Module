package com.example.core.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.models.stateData.Either
import com.example.core.models.stateData.ExceptionState
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

///✨===============================================
///[StateAndEventViewModel] is an abstract class that extends [ViewModel].
///This class is used to handle the state and events in the view model.
///[UiState] is the state of the view model.
///[Event] is the event of the view model.
///[initialState] is the initial state of the view model.
///✨===============================================

abstract class StateAndEventViewModel<UiState, Event>(initialState: UiState) : ViewModel() {
    private val events = MutableSharedFlow<Event>(replay = 0)
    private val _uiState: MutableStateFlow<UiState> = MutableStateFlow(initialState)
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            events.collect { event -> handleEvent(event) }
        }
    }

    protected abstract suspend fun handleEvent(event: Event)

    protected fun setUiState(update: UiState.() -> UiState) {
        _uiState.update { _uiState.value.update() }
    }

    fun onEvent(event: Event) {
        viewModelScope.launch { events.emit(event) }
    }

    ///✨===============================================
    ///[loadInitialData] is a suspend function that loads the initial data.
    ///[initialLoadState] is a lambda function that returns the initial state of the view model.
    ///[errorLoadState] is a lambda function that returns the error state of the view model.
    ///[listRequest] is a list of flow of either exception state or any.

    ///[listResponse] is a lambda function that takes in the response and response index.
    ///* (response, responseIndex) -> Unit
    ///* response is the response of the request.
    ///* responseIndex is the index of the response.

    ///Example:
    ///```kotlin
    ///loadInitialData(
    ///    initialLoadState = { copy(isLoading = true) },
    ///    errorLoadState = { _ -> copy(isLoading = false) },
    ///    listRequest = listOf(
    ///        homeUseCase.fetchRecommendedCourses(createPaginationRequest(Constants.COURSE_LIMIT_ITEM)),
    ///        homeUseCase.fetchTutors(createPaginationRequest(Constants.TUTOR_LIMIT_ITEM)),
    ///        homeUseCase.fetchEBooks(createPaginationRequest(Constants.EBOOK_LIMIT_ITEM))
    ///    )
    ///) { response, responseIndex ->
    ///    when (responseIndex) {
    ///        0 -> updateStateWithCourses(response.toListEither())
    ///        1 -> updateStateWithTutors(response.toListEither())
    ///        2 -> updateStateWithEBooks(response.toListEither())
    ///    }
    fun loadInitialData(
        initialLoadState: UiState.() -> UiState,
        errorLoadState: UiState.(String) -> UiState,
        listRequest: List<Flow<Either<ExceptionState, Any>>>,

        listResponse: (Either<ExceptionState, Any>, Int) -> Unit
    ) {
        setUiState { initialLoadState() }
        val request = listRequest.map { req ->
            viewModelScope.async {
                req.catch { e ->
                    emit(Either.Left(ExceptionState(errorMessage = e.message ?: "")))
                }
            }
        }

        viewModelScope.launch {
            try {
                ///✨===============================================
                ///[awaitAll] is a suspend function that waits for all the given deferred values to complete.
                val results = awaitAll(*request.toTypedArray())
//                results.forEachIndexed { index, rp -> launch { rp.collect { listResponse(it, index) } }  }
                results.forEachIndexed { index, rp -> rp.collect { listResponse(it, index) } }
            } catch (e: Exception) {
                Log.e("StateAndEventViewModel", "Error loading initial data", e)
                setUiState { _uiState.value.errorLoadState(e.message ?: "") }
            }
        }
    }

    ///✨===============================================
    ///[retry] is a suspend function that takes in a block and retries the block if it fails.
    private suspend fun <T> retry(
        times: Int,
        initialDelayMillis: Long = 100,
        maxDelayMillis: Long = 1000,
        factor: Double = 2.0,
        block: suspend () -> T
    ): T {
        var currentDelay = initialDelayMillis
        repeat(times) {
            try {
                return block()
            } catch (exception: Exception) {
                exception.printStackTrace()
            }
            delay(currentDelay)
            currentDelay = (currentDelay * factor).toLong().coerceAtMost(maxDelayMillis)
        }
        return block() // last attempt
    }
}
package com.example.core.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
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

    protected fun setEvent(event: Event) {
        viewModelScope.launch { events.emit(event) }
    }
}
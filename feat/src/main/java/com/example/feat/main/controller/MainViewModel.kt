package com.example.feat.main.controller

import com.example.core.presentation.StateAndEventViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : StateAndEventViewModel<MainState, MainEvent>(
    initialState = MainState()
) {
    override suspend fun handleEvent(event: MainEvent) {
        when (event) {
            is MainEvent.TabSelected -> changeTab(newIndex = event.index)
        }
    }

    private fun changeTab(newIndex: Int) {
        if(uiState.value.currentIndex == newIndex) return

        setUiState { copy(currentIndex = newIndex) }
    }
}
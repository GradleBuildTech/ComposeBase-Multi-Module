package com.example.feat.main.controller

import androidx.navigation.NavHostController
import com.example.core.presentation.StateAndEventViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : StateAndEventViewModel<MainState, MainEvent>(
    initialState = MainState(

    )
) {
    override suspend fun handleEvent(event: MainEvent) {
        when (event) {
            is MainEvent.TabSelected -> changeTab(newIndex = event.index)
            is MainEvent.SetNavController -> setNavController(event.navController)
        }
    }

    private fun changeTab(newIndex: Int) {
        if(uiState.value.currentIndex == newIndex) return

        setUiState { copy(currentIndex = newIndex) }
    }

    private fun setNavController(navController: NavHostController) {
        setUiState { copy(navController = navController) }
    }
}

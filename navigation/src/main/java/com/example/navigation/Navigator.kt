package com.example.navigation

import androidx.navigation.NavOptionsBuilder
import com.example.core.navigation.NavigationService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject
import javax.inject.Singleton
///✨ ================================================
///[Navigator] is a class that provides navigation functionality.
///This is used to navigate to a destination and go back.
///[navigateTo] is a function that navigates to a destination.
///[goBack] is a function that goes back.

@Singleton
class Navigator @Inject constructor(): NavigationService {
    /// _actions is a MutableSharedFlow that represents the actions.
    //// replay is 0 and extraBufferCapacity is 10. -> MutableSharedFlow<Action>(replay = 0, extraBufferCapacity = 10)
    private val _actions = MutableSharedFlow<NavigationActions>(
        replay = 0,
        extraBufferCapacity = 10
        )

    val actions: Flow<NavigationActions> = _actions.asSharedFlow()

    override fun navigateTo(destination: String, navOptions: NavOptionsBuilder.() -> Unit) {
        _actions.tryEmit(NavigationActions.Navigate(destination, navOptions))
    }

    override fun goBack() {
        _actions.tryEmit(NavigationActions.Back)
    }

    sealed class NavigationActions {
        data class Navigate(
            val destination: String,
            val navOptions: NavOptionsBuilder.() -> Unit
        ) : NavigationActions()

        data object Back: NavigationActions()
    }
}
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.core.lib.utils.navigation.bottomNavigationRoute
import com.example.core.lib.utils.navigation.bottomNavigationTabs
import com.example.navigation.Destination
import com.example.navigation.bottomAppNavigation.BottomNavigationWrapper

/// BottomAppNavigation is a composable that wraps the BottomNavigationWrapper composable and NavHost composable.
/// It is responsible for handling the navigation actions and updating the current destination.
/// It takes a Navigator, homeScreen, documentScreen, and searchScreen as parameters.
/// It collects the actions from the Navigator and navigates to the specified destination.
/// It updates the current destination based on the selected tab.
/// It uses the BottomNavigationWrapper to display the bottom navigation bar.
/// It uses the NavHost composable to navigate between different destinations.


@Composable
fun BottomAppNavigation(
    ///Bottom navigation composable function that takes a
    initialRoute: String? = null,
    ///Define the homeScreen, documentScreen, and searchScreen composable functions as parameters.
    homeScreen: @Composable () -> Unit,
    documentScreen: @Composable () -> Unit,
    searchScreen: @Composable () -> Unit
) {

    val rememberDestination = remember { mutableStateOf(initialRoute ?: Destination.home.route) }

    val state = rememberPagerState(initialPage = 0, pageCount = { bottomNavigationTabs.size })

    BottomNavigationWrapper(
        currentDestination = bottomNavigationRoute[state.currentPage],
        body = {
            HorizontalPager(state = state) { page ->
                when (bottomNavigationRoute[page]) {
                    Destination.home.route -> homeScreen()
                    Destination.document.route -> documentScreen()
                    Destination.search.route -> searchScreen()
                }
            }
        },
        onChangeTab = { route, pageIndex ->
            if (state.currentPage != pageIndex) {
                rememberDestination.value = route
                state.requestScrollToPage(pageIndex)
            }
        }

    )
}
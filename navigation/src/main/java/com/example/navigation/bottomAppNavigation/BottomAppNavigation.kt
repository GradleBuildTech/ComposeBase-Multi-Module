import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.core.lib.utils.navigation.bottomNavigationTabs
import com.example.feat.course_detail.CourseDetailScreen
import com.example.navigation.Destination
import com.example.navigation.Navigator
import com.example.navigation.bottomAppNavigation.BottomNavigationWrapper
import kotlinx.coroutines.flow.collectLatest

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

    ///Define the homeScreen, documentScreen, and searchScreen composable functions as parameters.
    homeScreen: @Composable () -> Unit,
    documentScreen: @Composable () -> Unit,
    searchScreen: @Composable () -> Unit
) {

    val rememberDestination = remember { mutableStateOf(Destination.home.route) }

    val state = rememberPagerState(initialPage = 0, pageCount = { bottomNavigationTabs.size })

    BottomNavigationWrapper(
        currentDestination = rememberDestination.value,
        body = {
            HorizontalPager(state = state) { page ->
                when (page) {
                    0 -> homeScreen()
                    1 -> documentScreen()
                    2 -> searchScreen()
                }
            }
        },
        onChangeTab = { route, pageIndex ->
            rememberDestination.value = route
            state.requestScrollToPage(pageIndex)
        }

    )
}
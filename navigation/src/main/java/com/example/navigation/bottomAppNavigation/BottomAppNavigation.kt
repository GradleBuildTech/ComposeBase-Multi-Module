import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
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
    navigator: Navigator,

    ///Define the homeScreen, documentScreen, and searchScreen composable functions as parameters.
    homeScreen: @Composable () -> Unit,
    documentScreen: @Composable () -> Unit,
    searchScreen: @Composable () -> Unit
) {
    val navController = rememberNavController()

    ///Define a rememberDestination mutableState variable to store the current destination.
    val rememberDestination = remember { mutableStateOf(Destination.home.route) }

    LaunchedEffect(Unit) {
        navigator.actions.collectLatest {
            when (it) {
                is Navigator.NavigationActions.Navigate -> {
                    navController.navigate(it.destination, builder = it.navOptions)
                }

                is Navigator.NavigationActions.Back -> {
                    navController.popBackStack()
                }
            }
        }
    }

    BottomNavigationWrapper(
        currentDestination = rememberDestination.value,
        body = {
            NavHost(
                navController = navController,
                startDestination = Destination.home.route
            ) {
                composable(Destination.home.route) { homeScreen() }
                composable(Destination.document.route) { documentScreen() }
                composable(Destination.search.route) { searchScreen() }
            }
        },
        onChangeTab = { destination ->
            rememberDestination.value = destination
            navController.navigate(destination)
        }

    )
}
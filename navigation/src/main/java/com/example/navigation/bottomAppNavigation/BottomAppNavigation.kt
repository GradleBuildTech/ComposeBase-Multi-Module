import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.navigation.Destination
import com.example.navigation.Navigator
import com.example.navigation.bottomAppNavigation.BottomNavigationWrapper
import kotlinx.coroutines.flow.collectLatest

@Composable
fun BottomAppNavigation(
    navigator: Navigator,
    homeScreen: @Composable () -> Unit,
    documentScreen: @Composable () -> Unit,
    searchScreen: @Composable () -> Unit
) {
    val navController = rememberNavController()

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
        currentDestination = navController.currentDestination?.route ?: Destination.home.route,
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
            navController.navigate(destination)
        }

    )
}
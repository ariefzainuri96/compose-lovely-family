package lovely.family.android.Pages.Navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import lovely.family.android.Pages.Dashboard.DashboardView
import lovely.family.android.Pages.Login.LoginView

@Composable
fun AppNavigation(viewModel: AppNavigationVM) {
    val navController = rememberNavController()
    val isLoggedIn by viewModel.isLoggedIn.collectAsState()

    LaunchedEffect(isLoggedIn) {
        if (isLoggedIn) {
            navController.navigate("home") {
                popUpTo("login") { inclusive = true }
            }
        } else {
            navController.navigate("login") {
                popUpTo("home") { inclusive = true }
            }
        }
    }

    NavHost(navController, startDestination = if (isLoggedIn) "home" else "login") {
        composable("login") { LoginView(navController) }
        composable("home") { DashboardView(navController) }
    }
}
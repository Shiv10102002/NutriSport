package org.shiv.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.shiv.admin_panel.AdminPanelScreen
import org.shiv.auth.AuthScreen
import org.shiv.home.HomeGraphScreen
import org.shiv.profile.ProfileScreen
import org.shiv.shared.navigation.Screen

@Composable
fun SetUpNavGraph(startDestination: Screen = Screen.Auth){
    val navController = rememberNavController()
    NavHost(
        navController =navController,
        startDestination = startDestination
    ){
        composable<Screen.Auth>{
            AuthScreen{
                navController.navigate(Screen.HomeGraph){
                    popUpTo<Screen.Auth> { inclusive = true }
                }
            }
        }
        composable<Screen.HomeGraph> {
            HomeGraphScreen(
                navigateToAuth = {
                    navController.navigate(Screen.Auth) {
                        popUpTo<Screen.HomeGraph> { inclusive = true }
                    }
                },
                navigateToProfile = {
                    navController.navigate(Screen.Profile)
                },
                navigateToAdminPanel = {
                    navController.navigate(Screen.AdminPanel)
                },
            )
        }

        composable<Screen.Profile> {
            ProfileScreen(
                navigateBack = {
                    navController.navigateUp()
                }
            )
        }

        composable<Screen.AdminPanel> {
            AdminPanelScreen (
                navigateBack = {
                    navController.navigateUp()
                }
            )
        }

    }
}
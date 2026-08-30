package com.urbana.app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.lifecycle.viewmodel.compose.viewModel
import com.urbana.app.ui.screens.*
import com.urbana.app.viewmodel.AuthViewModel
import com.urbana.app.viewmodel.ExpressaoViewModel

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val authViewModel: AuthViewModel = viewModel()
    val expressaoViewModel: ExpressaoViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = "splash"
    ) {
        composable("splash") {
            SplashScreen(
                isAuthenticated = authViewModel.isAuthenticated.value,
                onNavigate = { destination ->
                    navController.navigate(destination) {
                        popUpTo("splash") { inclusive = true }
                    }
                }
            )
        }

        composable("login") {
            LoginScreen(
                authViewModel = authViewModel,
                onLoginSuccess = {
                    navController.navigate("home") {
                        popUpTo("login") { inclusive = true }
                    }
                },
                onNavigateToRegister = {
                    navController.navigate("cadastro")
                }
            )
        }

        composable("cadastro") {
            CadastroScreen(
                authViewModel = authViewModel,
                onRegisterSuccess = {
                    navController.navigate("home") {
                        popUpTo("cadastro") { inclusive = true }
                    }
                },
                onNavigateToLogin = {
                    navController.popBackStack()
                }
            )
        }

        composable("home") {
            HomeScreen(
                userName = authViewModel.userName.value,
                onNavigateToCatalogo = {
                    navController.navigate("catalogo")
                },
                onLogout = {
                    authViewModel.logout()
                    navController.navigate("login") {
                        popUpTo("home") { inclusive = true }
                    }
                }
            )
        }

        composable("catalogo") {
            CatalogoScreen(
                expressaoViewModel = expressaoViewModel,
                onItemClick = { id ->
                    navController.navigate("detalhes/$id")
                },
                onBack = {
                    navController.popBackStack()
                }
            )
        }

        composable("detalhes/{id}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")?.toIntOrNull() ?: 0
            DetalhesScreen(
                expressaoId = id,
                expressaoViewModel = expressaoViewModel,
                onBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}
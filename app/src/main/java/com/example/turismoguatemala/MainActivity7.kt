package com.example.turismoguatemala

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.turismoguatemala.Screen.Screen
import com.example.turismoguatemala.Screen.SignUpScreen
import com.example.turismoguatemala.Screen.LoginScreen
import com.example.turismoguatemala.ui.theme.TurismoGuatemalaTheme
import com.example.turismoguatemala.view.AuthViewModel

class MainActivity7 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val authViewModel: AuthViewModel = viewModel()

            TurismoGuatemalaTheme {
                NavigationGraph(navController = navController, authViewModel = authViewModel)
            }
        }
    }
}

@Composable
fun NavigationGraph(
    navController: NavHostController,
    authViewModel: AuthViewModel
) {
    NavHost(
        navController = navController,
        startDestination = Screen.SignupScreen.route // O la pantalla de login, dependiendo de lo que prefieras
    ) {
        // Pantalla de Registro (SignUp)
        composable(Screen.SignupScreen.route) {
            SignUpScreen(
                authViewModel = authViewModel,
                onNavigateToLogin = { navController.navigate(Screen.LoginScreen.route) },
                onSignUpSuccess = {
                    // Redirigir a la pantalla principal después del registro exitoso
                    navController.navigate("main") {
                        popUpTo(Screen.SignupScreen.route) { inclusive = true }
                    }
                }
            )
        }

        // Pantalla de Inicio de Sesión (Login)
        composable(Screen.LoginScreen.route) {
            LoginScreen(
                authViewModel = authViewModel,
                onNavigateToSignUp = { navController.navigate(Screen.SignupScreen.route) },
                onSignInSuccess = {
                    // Redirigir a la pantalla principal después de un inicio de sesión exitoso
                    navController.navigate("main") {
                        popUpTo(Screen.LoginScreen.route) { inclusive = true }
                    }
                }
            )
        }

        // Pantalla principal (Descubrimiento de destinos) después de iniciar sesión o registrarse
        composable("main") {
            // Aquí rediriges a la pantalla con la barra de navegación inferior (Pantalla de descubrimiento de destinos)
            PantallaDescubrimientoDestinosApp(navController)
        }
    }
}

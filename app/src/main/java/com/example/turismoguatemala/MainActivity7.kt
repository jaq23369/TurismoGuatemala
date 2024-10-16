package com.example.turismoguatemala

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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

class MainActivity7: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            val authViewModel: AuthViewModel = viewModel()
            TurismoGuatemalaTheme {
// A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavigationGraph(navController = navController, authViewModel = authViewModel)
                }
            }
        }
    }
}
@Composable
fun NavigationGraph(
    navController: NavHostController
    , authViewModel: AuthViewModel
) {
    NavHost(
        navController = navController,
        startDestination = Screen.SignupScreen.route // O la pantalla que quieras que sea el inicio
    ) {
        // Pantalla de Registro (SignUp)
        composable(Screen.SignupScreen.route) {
            SignUpScreen(
                authViewModel = authViewModel,
                onNavigateToLogin = { navController.navigate(Screen.LoginScreen.route) },
                onSignUpSuccess = { navController.navigate(Screen.ChatRoomsScreen.route) } // Define la ruta de destino en caso de éxito
            )
        }

        // Pantalla de Inicio de Sesión (Login)
        composable(Screen.LoginScreen.route) {
            LoginScreen(
                authViewModel = authViewModel,
                onNavigateToSignUp = { navController.navigate(Screen.SignupScreen.route) },
                onSignInSuccess = {
                    // Aquí defines la acción al iniciar sesión correctamente
                    navController.navigate(Screen.ChatRoomsScreen.route) {
                        popUpTo(Screen.LoginScreen.route) { inclusive = true } // Elimina la pantalla de login de la pila
                    }
                }
            )
        }

        // Pantalla principal después de iniciar sesión o registrarse
        composable(Screen.ChatRoomsScreen.route) {
            // Aquí iría la pantalla principal que sigue después del login/registro exitoso
        }

        // Si tienes más pantallas que navegan desde la principal, las defines aquí
        composable("${Screen.ChatScreen.route}/{roomId}") {
            // Aquí iría la lógica para una pantalla específica, en este caso un chat con roomId
        }
    }
}


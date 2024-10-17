package com.example.turismoguatemala

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.turismoguatemala.Screen.LoginScreen
import com.example.turismoguatemala.Screen.SignUpScreen
import com.example.turismoguatemala.view.AuthViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AppNavGraph(
    navController: NavHostController,
    startDestination: String,
    authViewModel: AuthViewModel
) {
    // Mantenemos el Scaffold aquí, pero la BottomNavBar solo estará en ciertas pantallas
    Scaffold(
        bottomBar = {
            // Mostrar BottomNavBar solo en las pantallas principales
            val currentRoute = navController.currentBackStackEntry?.destination?.route
            if (currentRoute != "login" && currentRoute != "signUp") {
                BottomNavBar(navController)
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = startDestination,
            modifier = Modifier.padding(paddingValues) // Ajustamos el padding
        ) {
            composable("login") {
                // Pantalla de Login sin BottomNavBar
                LoginScreen(
                    authViewModel = authViewModel,
                    onNavigateToSignUp = { navController.navigate("signUp") },
                    onSignInSuccess = {
                        navController.navigate("main") {
                            popUpTo("login") { inclusive = true }
                        }
                    }
                )
            }
            composable("signUp") {
                // Pantalla de SignUp sin BottomNavBar
                SignUpScreen(
                    authViewModel = authViewModel,
                    onNavigateToLogin = { navController.navigate("login") },
                    onSignUpSuccess = {
                        navController.navigate("main") {
                            popUpTo("signUp") { inclusive = true }
                        }
                    }
                )
            }
            composable("main") {
                // Pantalla principal con BottomNavBar
                PantallaDescubrimientoDestinosApp(navController)
            }
            // Otras pantallas principales también tendrán BottomNavBar
            composable("detalles") {
                val destino = Destino(
                    nombre = "Cimarron",
                    descripcion = "Un hermoso monumento natural en medio del desierto.",
                    imagenResId = R.drawable.cimarron,
                    comoLlegar = "Puedes llegar en autobús...",
                    queLlevar = "Lleva ropa cómoda...",
                    queEsperar = "Naturaleza increíble..."
                )
                PantallaInformacionDetallada(navController, destino)
            }
            composable("interaccion_comunitaria") {
                PantallaInteraccionComunitaria(navController)
            }
            composable("planificacion") {
                PantallaPlanificacionViajes(navController)
            }
            composable("apoyo_comunidad") {
                PantallaApoyoComunidadLocal(navController)
            }
            composable("notificaciones") {
                PantallaNotificacionesYAlertas(navController)
            }
        }
    }
}






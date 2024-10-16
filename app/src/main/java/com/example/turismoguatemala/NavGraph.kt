package com.example.turismoguatemala

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.compose.material3.Scaffold
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
    Scaffold(
        bottomBar = { BottomNavBar(navController) }  // Añadimos el BottomNavigation
    ) {

        // Aquí usamos el startDestination
        NavHost(
            navController = navController,
            startDestination = startDestination
        ) {


            composable("login") {
                LoginScreen(
                    authViewModel = authViewModel,
                    onNavigateToSignUp = { navController.navigate("signUp") },
                    onSignInSuccess = { navController.navigate("main") { popUpTo("login") { inclusive = true } } }
                )
            }
            composable("signUp") {
                SignUpScreen(
                    authViewModel = authViewModel,
                    onNavigateToLogin = { navController.navigate("login") },
                    onSignUpSuccess = { navController.navigate("main") { popUpTo("signUp") { inclusive = true } } }
                )
            }

            // Pantalla 1: Descubrimiento de destinos (pantalla principal)
            composable("main") {
                PantallaDescubrimientoDestinosApp(navController)
            }

            // Pantalla 2: Información detallada
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

            // Pantalla 3: Interacción Comunitaria
            composable("interaccion_comunitaria") {
                PantallaInteraccionComunitaria(navController)
            }

            // Pantalla 4: Planificación de Viajes
            composable("planificacion") {
                PantallaPlanificacionViajes(navController)
            }

            // Pantalla 5: Apoyo a la Comunidad Local
            composable("apoyo_comunidad") {
                PantallaApoyoComunidadLocal(navController)
            }

            // Pantalla 6: Notificaciones y Alertas
            composable("notificaciones") {
                PantallaNotificacionesYAlertas(navController)
            }
        }
    }
}



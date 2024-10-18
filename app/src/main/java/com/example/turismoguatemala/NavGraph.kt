package com.example.turismoguatemala

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.compose.material3.Scaffold
import com.example.turismoguatemala.repository.DestinoRepository

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AppNavGraph(navController: NavHostController, destinoRepository: DestinoRepository) {
    Scaffold(
        bottomBar = { BottomNavBar(navController) }  // Añadimos el BottomNavigation
    ) {
        NavHost(navController = navController, startDestination = "main") {
            // Pantalla 1: Descubrimiento de destinos
            composable("main") {
                PantallaDescubrimientoDestinosApp(
                    navController = navController,
                    destinoRepository = destinoRepository // Pasar el destinoRepository
                )
            }

            // Pantalla 2: Información detallada
            composable("detalles") {
                // Simula un destino en esta pantalla
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

            composable("mapa") {
                PantallaMapa() // Aquí llamas a la función que has definido para mostrar el mapa
            }

        }
    }
}


package com.example.turismoguatemala

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.turismoguatemala.ui.theme.TurismoGuatemalaTheme
import androidx.compose.ui.tooling.preview.Preview

class MainActivity6 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TurismoGuatemalaTheme {
                // Pantalla principal de Notificaciones y Alertas
                PantallaNotificacionesYAlertas()
            }
        }
    }
}

// Pantalla de Notificaciones y Alertas
@Composable
fun PantallaNotificacionesYAlertas() {
    // Lista de destinos con sus condiciones actuales
    val destinos = listOf(
        DestinoConNotificacion(
            nombre = "Semuc Champey",
            clima = "Lluvia ligera",
            accesibilidad = "Accesible",
            notificacionesHabilitadas = false
        ),
        DestinoConNotificacion(
            nombre = "Tikal",
            clima = "Soleado",
            accesibilidad = "Accesible",
            notificacionesHabilitadas = true
        ),
        DestinoConNotificacion(
            nombre = "Lago Atitlán",
            clima = "Nublado",
            accesibilidad = "Cerrado por mantenimiento",
            notificacionesHabilitadas = false
        )
    )

    var destinosNotificados by remember { mutableStateOf(destinos) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Título
        Text(
            text = "Notificaciones y Alertas",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Lista de destinos con alertas
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(destinosNotificados) { destino ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(text = destino.nombre, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(text = "Clima: ${destino.clima}", fontSize = 14.sp)
                        Text(text = "Accesibilidad: ${destino.accesibilidad}", fontSize = 14.sp)

                        // Interruptor para habilitar o deshabilitar notificaciones
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(text = "Notificaciones", modifier = Modifier.weight(1f))

                            Switch(
                                checked = destino.notificacionesHabilitadas,
                                onCheckedChange = {
                                    // Actualizar el estado de las notificaciones
                                    val index = destinosNotificados.indexOf(destino)
                                    destinosNotificados = destinosNotificados.toMutableList().apply {
                                        this[index] = this[index].copy(notificacionesHabilitadas = it)
                                    }
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

// Modelo de datos para un destino con notificación
data class DestinoConNotificacion(
    val nombre: String,
    val clima: String,
    val accesibilidad: String,
    val notificacionesHabilitadas: Boolean
)

@Preview(showBackground = true)
@Composable
fun PreviewPantallaNotificacionesYAlertas() {
    TurismoGuatemalaTheme {
        PantallaNotificacionesYAlertas()
    }
}
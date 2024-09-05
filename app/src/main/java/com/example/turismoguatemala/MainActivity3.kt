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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.turismoguatemala.ui.theme.TurismoGuatemalaTheme

class MainActivity3 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TurismoGuatemalaTheme {
                // Pantalla principal de Interacción Comunitaria
                PantallaInteraccionComunitaria()
            }
        }
    }
}

// Pantalla de Interacción Comunitaria
@Composable
fun PantallaInteraccionComunitaria() {
    var nombreUsuario by remember { mutableStateOf("") }
    var comentario by remember { mutableStateOf("") }
    val reseñas = remember { mutableStateListOf<Reseña>() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Título de la pantalla
        Text(
            text = "Reseñas de la comunidad",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Lista de reseñas
        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            items(reseñas) { reseña ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(text = reseña.nombre, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(text = reseña.comentario)
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Formulario para agregar reseña
        Text(
            text = "Agrega tu reseña",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Campo para nombre del usuario
        OutlinedTextField(
            value = nombreUsuario,
            onValueChange = { nombreUsuario = it },
            label = { Text("Tu nombre") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Campo para comentario de la reseña
        OutlinedTextField(
            value = comentario,
            onValueChange = { comentario = it },
            label = { Text("Tu comentario") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Botón para enviar la reseña
        Button(
            onClick = {
                if (nombreUsuario.isNotEmpty() && comentario.isNotEmpty()) {
                    reseñas.add(Reseña(nombreUsuario, comentario))
                    nombreUsuario = ""  // Resetear el campo de nombre
                    comentario = ""     // Resetear el campo de comentario
                }
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Enviar reseña")
        }
    }
}

// Modelo de datos para una reseña
data class Reseña(
    val nombre: String,
    val comentario: String
)

@Preview(showBackground = true)
@Composable
fun PreviewPantallaInteraccionComunitaria() {
    TurismoGuatemalaTheme {
        PantallaInteraccionComunitaria()
    }
}


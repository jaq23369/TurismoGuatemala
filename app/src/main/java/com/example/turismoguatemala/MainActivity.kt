package com.example.turismoguatemala

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.turismoguatemala.ui.theme.TurismoGuatemalaTheme
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TurismoGuatemalaTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    // Pantalla principal de Descubrimiento de Destinos
                    PantallaDescubrimientoDestinosApp(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

// Función principal que muestra la pantalla de "Descubrimiento de Destinos"
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaDescubrimientoDestinosApp(modifier: Modifier = Modifier) {
    val destinos = remember {
        mutableStateListOf(
            Destino("Semuc Champey", "Un hermoso monumento natural en medio de la jungla.", android.R.drawable.ic_menu_camera),
            Destino("Tikal", "Un sitio arqueológico con ruinas mayas rodeadas de naturaleza.", android.R.drawable.ic_menu_compass),
            Destino("Rio Dulce", "Un destino de aventuras con impresionantes paisajes acuáticos.", android.R.drawable.ic_menu_gallery)
        )
    }

    // Scaffold para la barra superior y el contenido principal
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Descubre Guatemala") }
            )
        },
        content = {
            PantallaDescubrimientoDestinos(
                destinos = destinos,
                onRefresh = {
                    // Acción simulada para agregar más destinos
                    destinos.add(
                        Destino(
                            "Laguna Brava",
                            "Un lago escondido en las montañas, perfecto para los amantes de la naturaleza.",
                            android.R.drawable.ic_menu_slideshow
                        )
                    )
                },
                modifier = modifier.padding(it)
            )
        }
    )
}

// Componente de la pantalla de descubrimiento de destinos
@Composable
fun PantallaDescubrimientoDestinos(
    destinos: List<Destino>,
    onRefresh: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize().padding(16.dp)
    ) {
        // Título
        Text(
            text = "Descubre destinos únicos",
            fontSize = 24.sp,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        // Lista de destinos
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(destinos) { destino ->
                CardDestino(destino)
                Spacer(modifier = Modifier.height(8.dp))
            }
        }

        // Botón de refresco para obtener más recomendaciones
        Button(
            onClick = { onRefresh() },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Icon(Icons.Default.Refresh, contentDescription = "Recargar")
            Spacer(modifier = Modifier.width(8.dp))
            Text("Ver más recomendaciones")
        }
    }
}

// Modelo de datos para los destinos
data class Destino(
    val nombre: String,
    val descripcion: String,
    val imagenResId: Int
)

// Componente para mostrar cada destino en una tarjeta
@Composable
fun CardDestino(destino: Destino) {
    Card(
        elevation = CardDefaults.cardElevation(4.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            // Imagen del destino
            Image(
                painter = painterResource(id = destino.imagenResId),
                contentDescription = destino.nombre,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Nombre del destino
            Text(text = destino.nombre, fontSize = 20.sp, fontWeight = FontWeight.Bold)

            // Descripción del destino
            Text(text = destino.descripcion, fontSize = 14.sp)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewPantallaDescubrimientoDestinos() {
    TurismoGuatemalaTheme {
        PantallaDescubrimientoDestinosApp()
    }
}
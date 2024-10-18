package com.example.turismoguatemala

import android.annotation.SuppressLint
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
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.rememberNavController
import com.example.turismoguatemala.repository.DestinoRepository
import com.google.android.gms.maps.MapsInitializer
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TurismoGuatemalaTheme {
                // Inicializa los servicios de Google Maps
                MapsInitializer.initialize(applicationContext)

                // Creamos el NavController para manejar la navegación
                val navController = rememberNavController()

                // Crea una instancia de DestinoRepository
                val destinoRepository = DestinoRepository(applicationContext)

                // Usamos Scaffold para incluir la barra de navegación inferior
                Scaffold(
                    bottomBar = { BottomNavBar(navController) } // Agrega la barra de navegación inferior
                ) {
                    // Muestra el NavGraph que maneja la navegación entre las pantallas
                    AppNavGraph(
                        navController = navController,
                        destinoRepository = destinoRepository // Pasa el destinoRepository aquí
                    )
                }
            }
        }
    }
}

// Función principal que muestra la pantalla de "Descubrimiento de Destinos"
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaDescubrimientoDestinosApp(
    navController: NavHostController,
    destinoRepository: DestinoRepository,
    modifier: Modifier = Modifier
) {
    var destinos by remember { mutableStateOf<List<Destino>>(emptyList()) }
    val coroutineScope = rememberCoroutineScope() // Necesitamos este scope para lanzar corutinas
    val context = LocalContext.current

    // Usamos un efecto de lanzamiento para cargar los destinos de manera asíncrona
    LaunchedEffect(Unit) {
        destinos = destinoRepository.getDestinos() // Cargar destinos desde el repositorio
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
                    coroutineScope.launch {
                        destinos = destinoRepository.getDestinos() // Llamar dentro de una corutina
                    }
                },
                onDestinoClick = { destino ->
                    // Aquí navegas a la pantalla de detalles del destino seleccionado
                    navController.navigate("detalles/${destino.nombre}")
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
    onRefresh: () -> Unit, // Esto es importante, debe ser un lambda que no retorne nada
    onDestinoClick: (Destino) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Descubre destinos únicos",
            fontSize = 24.sp,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        LazyColumn(modifier = Modifier.weight(1f)) {
            items(destinos) { destino ->
                CardDestino(destino, onClick = { onDestinoClick(destino) })
                Spacer(modifier = Modifier.height(8.dp))
            }
        }

        Button(
            onClick = onRefresh, // Aquí llamamos directamente a la función `onRefresh`
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Icon(Icons.Default.Refresh, contentDescription = "Recargar")
            Spacer(modifier = Modifier.width(8.dp))
            Text("Ver más recomendaciones")
        }
    }
}

// Componente para mostrar cada destino en una tarjeta
@Composable
fun CardDestino(destino: Destino, onClick: () -> Unit) {
    Card(
        elevation = CardDefaults.cardElevation(4.dp),
        modifier = Modifier.fillMaxWidth().clickable { onClick() } // Añadimos onClick
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



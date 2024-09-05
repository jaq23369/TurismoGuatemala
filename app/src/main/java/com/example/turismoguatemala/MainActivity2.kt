package com.example.turismoguatemala

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.turismoguatemala.ui.theme.TurismoGuatemalaTheme
import com.example.turismoguatemala.Destino


class MainActivity2 : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TurismoGuatemalaTheme {
                // Pantalla de Información Detallada para un destino específico
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    val destino = Destino(
                        nombre = "Semuc Champey",
                        descripcion = "Un hermoso monumento natural en medio de la jungla.",
                        imagenResId = R.drawable.cimarron,  // Reemplaza con tu recurso real
                        comoLlegar = "Puedes llegar en autobús desde Ciudad de Guatemala hasta Cobán, luego tomar un 4x4 hacia Lanquín.",
                        queLlevar = "Lleva ropa cómoda, traje de baño, protector solar, y suficiente agua.",
                        queEsperar = "Naturaleza increíble, senderos de trekking y piscinas naturales de agua cristalina."
                    )
                    // Mostrar la pantalla con la información del destino
                    PantallaInformacionDetallada(destino = destino, onBack = {})
                }
            }
        }
    }
}


@Composable
fun PantallaInformacionDetallada(
    destino: Destino,  // El destino seleccionado con su información
    onBack: () -> Unit // Acción para volver a la pantalla anterior
) {
    // Scrollable column to allow vertical scrolling
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        // Imagen principal del destino
        Image(
            painter = painterResource(id = destino.imagenResId),
            contentDescription = destino.nombre,
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Título del destino
        Text(
            text = destino.nombre,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Sección: Cómo llegar
        InformacionSeccion(titulo = "Cómo llegar", descripcion = destino.comoLlegar)

        Spacer(modifier = Modifier.height(16.dp))

        // Sección: Qué llevar
        InformacionSeccion(titulo = "Qué llevar", descripcion = destino.queLlevar)

        Spacer(modifier = Modifier.height(16.dp))

        // Sección: Qué esperar
        InformacionSeccion(titulo = "Qué esperar", descripcion = destino.queEsperar)

        Spacer(modifier = Modifier.height(24.dp))

        // Botón para regresar o realizar otra acción
        Button(
            onClick = { onBack() },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Volver a la lista")
        }
    }
}

// Componente para mostrar una sección de información con un título y descripción
@Composable
fun InformacionSeccion(titulo: String, descripcion: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            // Título de la sección
            Text(
                text = titulo,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Descripción de la sección
            Text(
                text = descripcion,
                fontSize = 16.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewPantallaInformacionDetallada() {
    val destino = Destino(
        "Semuc Champey",
        "Un hermoso monumento natural en medio de la jungla.",
        R.drawable.cimarron,  // Asegúrate de tener esta imagen en res/drawable
        comoLlegar = "Puedes llegar en autobús desde Ciudad de Guatemala hasta Cobán, luego tomar un 4x4 hacia Lanquín.",
        queLlevar = "Lleva ropa cómoda, traje de baño, protector solar, y suficiente agua.",
        queEsperar = "Naturaleza increíble, senderos de trekking y piscinas naturales de agua cristalina."
    )
    PantallaInformacionDetallada(destino = destino, onBack = {})
}
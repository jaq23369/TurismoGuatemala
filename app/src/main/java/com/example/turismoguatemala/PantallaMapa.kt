package com.example.turismoguatemala

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun PantallaMapa() {
    // Coordenadas de Guatemala
    val guatemala = LatLng(15.7835, -90.2308)

    // Definir la posición inicial de la cámara
    val cameraPositionState = rememberCameraPositionState {
        position = com.google.android.gms.maps.model.CameraPosition.fromLatLngZoom(guatemala, 10f)
    }

    // Mostrar el mapa con un marcador en Guatemala
    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState
    ) {
        // Agregar un marcador en Guatemala
        Marker(
            state = com.google.maps.android.compose.MarkerState(position = guatemala),
            title = "Guatemala",
            snippet = "Aquí está Guatemala"
        )
    }
}
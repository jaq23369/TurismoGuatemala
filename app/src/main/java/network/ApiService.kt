package com.example.turismoguatemala.data.network

import com.example.turismoguatemala.Destino
import retrofit2.http.GET

interface ApiService {
    @GET("destinos")
    suspend fun getDestinos(): List<Destino>
}
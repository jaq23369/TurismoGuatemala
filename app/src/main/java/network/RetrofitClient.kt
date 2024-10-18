package com.example.turismoguatemala.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import com.example.turismoguatemala.Destino

// Definir la interfaz de Retrofit para la API
interface ApiService {
    @GET("destinos")
    suspend fun getDestinos(): List<Destino>
}

object RetrofitClient {
    private const val BASE_URL = "https://tudominio.com/api/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService: ApiService = retrofit.create(ApiService::class.java)
}




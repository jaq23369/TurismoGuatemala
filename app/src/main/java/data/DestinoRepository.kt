package com.example.turismoguatemala.repository

import retrofit2.HttpException
import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresExtension
import com.example.turismoguatemala.Destino
import com.example.turismoguatemala.network.RetrofitClient
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class DestinoRepository(private val context: Context) {

    // Solo con Retrofit
    suspend fun getDestinos(): List<Destino> {
        return try {
            // Llama a la API y obtiene los destinos
            val destinosFromApi = RetrofitClient.apiService.getDestinos()
            destinosFromApi
        } catch (e: Exception) {
            // Manejo de error, quizás mostrar un mensaje o retornar una lista vacía
            emptyList()
        }
    }
}
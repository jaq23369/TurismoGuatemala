package com.example.turismoguatemala.data

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class UserRepository(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore
) {
    suspend fun signUp(email: String, password: String, firstName: String, lastName: String): Result<Boolean> = try {
        // Registro en Firebase Authentication
        auth.createUserWithEmailAndPassword(email, password).await()

        // Obtenemos el UID del usuario autenticado
        val uid = auth.currentUser?.uid ?: throw Exception("User UID not found")

        // Creamos el objeto User con la información adicional
        val user = User(firstName, lastName, email)

        // Guardamos la información del usuario en Firestore (usamos UID como ID)
        saveUserToFirestore(uid, user)

        Result.Success(true)
    } catch (e: Exception) {
        Result.Error(e)
    }

    suspend fun login(email: String, password: String): Result<Boolean> = try {
        auth.signInWithEmailAndPassword(email, password).await()
        Result.Success(true)
    } catch (e: Exception) {
        Result.Error(e)
    }

    private suspend fun saveUserToFirestore(uid: String, user: User) {
        // Guardamos la información del usuario en Firestore usando el UID
        firestore.collection("users").document(uid).set(user).await()
    }

    suspend fun getCurrentUser(): Result<User> {
        return try {
            // Obtenemos el UID del usuario autenticado
            val uid = auth.currentUser?.uid ?: return Result.Error(Exception("User not authenticated"))

            // Buscamos el documento del usuario en Firestore
            val userDocument = firestore.collection("users").document(uid).get().await()
            val user = userDocument.toObject(User::class.java)

            if (user != null) {
                Result.Success(user)
            } else {
                Result.Error(Exception("User data not found"))
            }
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}
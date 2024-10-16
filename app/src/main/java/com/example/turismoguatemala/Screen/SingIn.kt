package com.example.turismoguatemala.Screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.example.turismoguatemala.view.AuthViewModel
import com.example.turismoguatemala.data.Result

@Composable
fun LoginScreen(
    authViewModel: AuthViewModel,
    onNavigateToSignUp: () -> Unit,  // Callback para navegar a la pantalla de registro
    onSignInSuccess: () -> Unit      // Callback para navegar a la pantalla principal después del login
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    // Observar el resultado de la autenticación
    val authResult by authViewModel.authResult.observeAsState()

    // Verificar si el login fue exitoso
    authResult?.let {
        when (it) {
            is Result.Success -> {
                if (it.data) onSignInSuccess()  // Navegar a la pantalla principal
            }
            is Result.Error -> {
                // Mostrar un mensaje de error, si lo deseas
            }
            else -> { /* No hacemos nada */ }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            visualTransformation = PasswordVisualTransformation()
        )
        Button(
            onClick = {
                authViewModel.login(email, password)
                onSignInSuccess()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text("Login")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text("Don't have an account? Sign up.",
            modifier = Modifier.clickable { onNavigateToSignUp() }
        )
    }
}


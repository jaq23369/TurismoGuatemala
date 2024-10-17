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
fun SignUpScreen(
    authViewModel: AuthViewModel,
    onNavigateToLogin: () -> Unit,  // Callback para navegar a la pantalla de login
    onSignUpSuccess: () -> Unit     // Callback para navegar a la pantalla principal después del registro
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }

    // Estado para mostrar mensajes de error
    var errorMessage by remember { mutableStateOf("") }

    // Observar el resultado de la autenticación
    val authResult by authViewModel.authResult.observeAsState()

    // Verificar si el registro fue exitoso
    authResult?.let {
        when (it) {
            is Result.Success -> {
                if (it.data) {
                    onSignUpSuccess()  // Navegar a la pantalla principal solo si el registro fue exitoso
                }
            }
            is Result.Error -> {
                errorMessage = "Error: ${it.exception.message}"  // Mostrar mensaje de error
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
            modifier = Modifier.fillMaxWidth().padding(8.dp)
        )
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            visualTransformation = PasswordVisualTransformation()
        )
        OutlinedTextField(
            value = firstName,
            onValueChange = { firstName = it },
            label = { Text("First Name") },
            modifier = Modifier.fillMaxWidth().padding(8.dp)
        )
        OutlinedTextField(
            value = lastName,
            onValueChange = { lastName = it },
            label = { Text("Last Name") },
            modifier = Modifier.fillMaxWidth().padding(8.dp)
        )

        // Mostrar mensaje de error si existe
        if (errorMessage.isNotEmpty()) {
            Text(text = errorMessage, color = androidx.compose.ui.graphics.Color.Red)
        }

        Button(
            onClick = {
                authViewModel.signUp(email, password, firstName, lastName)
                // No llamamos a onSignUpSuccess aquí, ya que esperamos el resultado de Firebase primero
            },
            modifier = Modifier.fillMaxWidth().padding(8.dp)
        ) {
            Text("Sign Up")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text("Already have an account? Sign in.",
            modifier = Modifier.clickable { onNavigateToLogin() }
        )
    }
}
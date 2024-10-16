package com.example.turismoguatemala.Screen

sealed class Screen(val route: String) {
    object LoginScreen : Screen("loginscreen")
    object SignupScreen : Screen("signupscreen") // Cambiado a "signupscreen"
    object ChatRoomsScreen : Screen("chatroomsscreen") // Cambiado a "chatroomsscreen"
    object ChatScreen : Screen("chatscreen") // Cambiado a "chatscreen"
}


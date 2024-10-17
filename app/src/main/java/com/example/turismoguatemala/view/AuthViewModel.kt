package com.example.turismoguatemala.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.turismoguatemala.Injection
import com.example.turismoguatemala.data.Result
import com.google.firebase.auth.FirebaseAuth
import com.example.turismoguatemala.data.UserRepository
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {
    private val userRepository: UserRepository = UserRepository(
        FirebaseAuth.getInstance(),
        Injection.instance()
    )
    private val _authResult = MutableLiveData<Result<Boolean>>()
    val authResult: LiveData<Result<Boolean>> get() = _authResult

    fun signUp(email: String, password: String, firstName: String, lastName: String) {
        viewModelScope.launch {
            try {
                val result = userRepository.signUp(email, password, firstName, lastName)
                _authResult.value = result
            } catch (e: Exception) {
                _authResult.value = Result.Error(e)  // Refleja el error en authResult
            }
        }
    }

    fun checkIfUserIsLoggedIn() {
        val user = FirebaseAuth.getInstance().currentUser
        if (user != null) {
            _authResult.value = Result.Success(true)  // Usuario ya autenticado
        } else {
            _authResult.value = Result.Success(false)  // Usuario no autenticado
        }
    }


    fun login(email: String, password: String) {
        viewModelScope.launch {
            try {
                val result = userRepository.login(email, password)
                _authResult.value = result
            } catch (e: Exception) {
                _authResult.value = Result.Error(e)  // Refleja el error en authResult
            }
        }
    }
}


package com.urbana.app.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AuthViewModel : ViewModel() {
    private val auth = FirebaseAuth.getInstance()

    private val _isAuthenticated = MutableStateFlow(auth.currentUser != null)
    val isAuthenticated: StateFlow<Boolean> = _isAuthenticated

    private val _userName = MutableStateFlow(auth.currentUser?.displayName ?: "")
    val userName: StateFlow<String> = _userName

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    fun login(email: String, password: String, onSuccess: () -> Unit) {
        _isLoading.value = true
        _error.value = null

        Log.d("URBANA_AUTH", "Tentando login: $email")

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                _isLoading.value = false
                if (task.isSuccessful) {
                    Log.d("URBANA_AUTH", "Login com sucesso")
                    _isAuthenticated.value = true
                    _userName.value = auth.currentUser?.displayName ?: email
                    onSuccess()
                } else {
                    val exception = task.exception
                    Log.e("URBANA_AUTH", "Erro no login", exception)

                    _error.value = when {
                        exception?.message?.contains("no user record", ignoreCase = true) == true ->
                            "Nenhuma conta encontrada com este e-mail. Crie uma conta primeiro."
                        exception?.message?.contains("password is invalid", ignoreCase = true) == true ||
                                exception?.message?.contains("wrong password", ignoreCase = true) == true ->
                            "Senha incorreta. Tente novamente."
                        exception?.message?.contains("invalid email", ignoreCase = true) == true ->
                            "E-mail inválido. Verifique e tente novamente."
                        exception?.message?.contains("network", ignoreCase = true) == true ->
                            "Erro de conexão. Verifique sua internet."
                        else -> "Erro ao fazer login: ${exception?.message ?: "Tente novamente"}"
                    }
                }
            }
    }

    fun register(name: String, email: String, password: String, onSuccess: () -> Unit) {
        _isLoading.value = true
        _error.value = null

        Log.d("URBANA_AUTH", "Tentando cadastro: $email")

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                _isLoading.value = false
                if (task.isSuccessful) {
                    Log.d("URBANA_AUTH", "Cadastro com sucesso")
                    auth.currentUser?.updateProfile(
                        com.google.firebase.auth.UserProfileChangeRequest.Builder()
                            .setDisplayName(name)
                            .build()
                    )?.addOnCompleteListener { updateTask ->
                        _isAuthenticated.value = true
                        _userName.value = name
                        onSuccess()
                    }
                } else {
                    val exception = task.exception
                    Log.e("URBANA_AUTH", "Erro no cadastro", exception)

                    _error.value = when {
                        exception?.message?.contains("already in use", ignoreCase = true) == true ->
                            "Este e-mail já está cadastrado. Faça login ou use outro e-mail."
                        exception?.message?.contains("invalid email", ignoreCase = true) == true ->
                            "E-mail inválido. Verifique e tente novamente."
                        exception?.message?.contains("weak password", ignoreCase = true) == true ->
                            "Senha muito fraca. Use pelo menos 6 caracteres."
                        exception?.message?.contains("network", ignoreCase = true) == true ->
                            "Erro de conexão. Verifique sua internet."
                        else -> "Erro ao criar conta: ${exception?.message ?: "Tente novamente"}"
                    }
                }
            }
    }

    fun clearError() {
        _error.value = null
    }

    fun logout() {
        auth.signOut()
        _isAuthenticated.value = false
        _userName.value = ""
    }
}
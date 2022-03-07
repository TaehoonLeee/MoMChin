package com.example.momchin.presentation.login

interface MoMChinLogin {

    fun onLogin()

    sealed interface Output {
        object LoginResult: Output
    }
}
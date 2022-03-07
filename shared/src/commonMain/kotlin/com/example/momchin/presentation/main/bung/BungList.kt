package com.example.momchin.presentation.main.bung

interface BungList {

    fun onItemClicked()

    sealed interface Output {
        object Selected : Output
    }
}
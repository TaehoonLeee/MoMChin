package com.example.momchin.presentation.main.bung.store

import com.arkivanov.mvikotlin.core.store.Store
import com.example.momchin.presentation.main.bung.BungList

interface BungListStore : Store<BungListStore.Intent, BungList.Model, Nothing> {

    sealed interface Intent {

    }
}
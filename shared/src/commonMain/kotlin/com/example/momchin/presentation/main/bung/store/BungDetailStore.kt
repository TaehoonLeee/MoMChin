package com.example.momchin.presentation.main.bung.store

import com.arkivanov.mvikotlin.core.store.Store
import com.example.momchin.presentation.main.bung.BungDetail
import com.example.momchin.presentation.main.bung.store.BungDetailStore.Intent

interface BungDetailStore : Store<Intent, BungDetail.BungDetailModel, Nothing> {

    sealed interface Intent {

    }
}
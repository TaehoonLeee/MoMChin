package com.example.momchin.presentation.main.community.store

import com.arkivanov.mvikotlin.core.store.Store
import com.example.momchin.presentation.main.community.store.CommunityDetailStore.Intent
import com.example.momchin.presentation.main.community.CommunityDetail.Model

interface CommunityDetailStore : Store<Intent, Model, Nothing> {

    sealed interface Intent {
        object Comment : Intent
    }
}
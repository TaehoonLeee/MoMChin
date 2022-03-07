package com.example.momchin.presentation.main.community.store

import com.arkivanov.mvikotlin.core.store.Store
import com.example.momchin.presentation.main.community.CommunityList

interface CommunityListStore : Store<CommunityListStore.Intent, CommunityList.CommunityListModel, Nothing> {

    sealed interface Intent {

    }
}
package com.example.momchin.presentation.main.community

import com.arkivanov.decompose.value.Value

interface CommunityList {

    val model: Value<CommunityListModel>

    data class CommunityListModel(
        val categories: List<String>,
        val items: List<CommunityItem>
    )

    data class CommunityItem(
        val user: String,
        val title: String,
        val content: String,
        val profileImage: String = ""
    )

    fun onItemClicked()

    sealed interface Output {
        object Selected : Output
    }
}
package com.example.momchin.presentation.main.community

import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize

interface CommunityList {

    val model: Value<CommunityListModel>

    data class CommunityListModel(
        val categories: List<String>,
        val items: List<CommunityItem>
    )

    @Parcelize
    data class CommunityItem(
        val user: String,
        val title: String,
        val content: String,
        val profileImage: String = ""
    ) : Parcelable

    fun onItemClicked(item: CommunityItem, category: String)

    sealed interface Output {
        data class Selected(
            val item: CommunityItem,
            val category: String
        ) : Output
    }
}
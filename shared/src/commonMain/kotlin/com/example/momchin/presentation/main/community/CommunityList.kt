package com.example.momchin.presentation.main.community

import com.arkivanov.decompose.value.Value
import com.example.momchin.domain.model.CommunityItem

interface CommunityList {

    val model: Value<Model>

    data class Model(
        val categories: List<String>,
        val items: List<CommunityItem>
    )

    fun onItemClicked(item: CommunityItem, category: String)

    sealed interface Output {
        data class Selected(
            val item: CommunityItem,
            val category: String
        ) : Output
    }
}
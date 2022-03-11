package com.example.momchin.presentation.main.community

import com.arkivanov.decompose.value.Value
import com.example.momchin.domain.model.CommunityItem

interface CommunityDetail {

    val model: Value<CommunityDetailModel>

    data class CommunityDetailModel(
        val category: String,
        val detail: CommunityItem,
        val comments: List<Comment>
    )

    data class Comment(
        val writer: String,
        val content: String
    )

    fun onComment()

    fun onBackButtonClick()

    sealed interface Output {
        object Finished : Output
    }
}
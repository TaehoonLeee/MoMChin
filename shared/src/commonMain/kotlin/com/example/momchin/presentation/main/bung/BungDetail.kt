package com.example.momchin.presentation.main.bung

import com.arkivanov.decompose.value.Value

interface BungDetail {

    val model: Value<BungDetailModel>

    data class BungDetailModel(
        val detail: BungList.Bung,
        val comments: List<Comment>
    )

    data class Comment(
        val writer: String,
        val content: String
    )

    fun onBackButtonClick()

    sealed interface Output {
        object Finished : Output
    }

}
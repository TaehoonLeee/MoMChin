package com.example.momchin.presentation.main.bung

import com.arkivanov.decompose.value.Value
import com.example.momchin.domain.model.Bung

interface BungDetail {

    val model: Value<Model>

    data class Model(
        val detail: Bung,
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
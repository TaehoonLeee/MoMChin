package com.example.momchin.presentation.main.bung

import com.arkivanov.decompose.value.Value
import com.example.momchin.domain.model.Bung

interface BungList {

    val model: Value<Model>

    fun onItemClicked(item: Bung)

    data class Model(
        val items: List<Bung>
    )

    sealed interface Output {
        data class Selected(val item: Bung) : Output
    }
}
package com.example.momchin.presentation.main.bung

import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import com.example.momchin.domain.model.Bung

interface BungList {

    val model: Value<BungListModel>

    fun onItemClicked(item: Bung)

    data class BungListModel(
        val items: List<Bung>
    )

    sealed interface Output {
        data class Selected(val item: Bung) : Output
    }
}
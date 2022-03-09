package com.example.momchin.presentation.main.bung

import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize

interface BungList {

    val model: Value<BungListModel>

    fun onItemClicked(item: Bung)

    data class BungListModel(
        val items: List<Bung>
    )

    @Parcelize
    data class Bung(
        val title: String,
        val place: String
    ) : Parcelable

    sealed interface Output {
        data class Selected(val item: Bung) : Output
    }
}
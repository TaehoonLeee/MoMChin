package com.example.momchin.presentation.main.bung

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.Value
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.badoo.reaktive.base.Consumer
import com.badoo.reaktive.base.invoke
import com.example.momchin.domain.model.Bung
import com.example.momchin.presentation.main.bung.store.BungListStoreProvider
import com.example.momchin.util.extension.asValue

internal class BungListComponent(
    componentContext: ComponentContext,
    private val output: Consumer<BungList.Output>
) : BungList, ComponentContext by componentContext {

    private val store = instanceKeeper.getStore {
        BungListStoreProvider().create()
    }

    override val model: Value<BungList.Model> = store.asValue()

    override fun onItemClicked(item: Bung) {
        output(BungList.Output.Selected(item))
    }
}
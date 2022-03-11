package com.example.momchin.presentation.main.bung

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.Value
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.badoo.reaktive.base.Consumer
import com.badoo.reaktive.base.invoke
import com.example.momchin.domain.model.Bung
import com.example.momchin.presentation.main.bung.store.BungDetailStoreProvider
import com.example.momchin.util.extension.asValue

internal class BungDetailComponent(
    componentContext: ComponentContext,
    private val item: Bung,
    private val output: Consumer<BungDetail.Output>
) : BungDetail, ComponentContext by componentContext {

    private val store = instanceKeeper.getStore {
        BungDetailStoreProvider(item).create()
    }

    override val model: Value<BungDetail.Model> = store.asValue()

    override fun onBackButtonClick() {
        output(BungDetail.Output.Finished)
    }
}
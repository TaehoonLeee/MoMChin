package com.example.momchin.presentation.main.community

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.Value
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.badoo.reaktive.base.Consumer
import com.badoo.reaktive.base.invoke
import com.example.momchin.presentation.main.community.store.CommunityListStoreProvider
import com.example.momchin.util.extension.asValue

internal class CommunityListComponent(
    componentContext: ComponentContext,
    private val output: Consumer<CommunityList.Output>
) : CommunityList, ComponentContext by componentContext {

    private val store = instanceKeeper.getStore {
        CommunityListStoreProvider().create()
    }

    override val model: Value<CommunityList.CommunityListModel> = store.asValue()

    override fun onItemClicked(item: CommunityList.CommunityItem, category: String) {
        output(CommunityList.Output.Selected(item, category))
    }
}
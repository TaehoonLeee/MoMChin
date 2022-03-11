package com.example.momchin.presentation.main.community

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.Value
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.badoo.reaktive.base.Consumer
import com.badoo.reaktive.base.invoke
import com.example.momchin.domain.model.CommunityItem
import com.example.momchin.presentation.main.community.store.CommunityDetailStore
import com.example.momchin.presentation.main.community.store.CommunityDetailStoreProvider
import com.example.momchin.util.extension.asValue

internal class CommunityDetailComponent(
    componentContext: ComponentContext,
    private val item: CommunityItem,
    private val category: String,
    private val output: Consumer<CommunityDetail.Output>
) : CommunityDetail, ComponentContext by componentContext{

    private val store = instanceKeeper.getStore {
        CommunityDetailStoreProvider(category, item).create()
    }

    override val model: Value<CommunityDetail.CommunityDetailModel> = store.asValue()

    override fun onComment() {
        store.accept(CommunityDetailStore.Intent.Comment)
    }

    override fun onBackButtonClick() {
        output(CommunityDetail.Output.Finished)
    }
}
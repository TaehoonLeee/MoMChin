package com.example.momchin.presentation.main.community.store

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.SimpleBootstrapper
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import com.example.momchin.presentation.main.community.CommunityList.CommunityItem
import com.example.momchin.presentation.main.community.CommunityList.CommunityListModel
import com.example.momchin.presentation.main.community.store.CommunityListStore.Intent

internal class CommunityListStoreProvider(
    private val storeFactory: StoreFactory = DefaultStoreFactory()
) {

    fun create(): CommunityListStore = object : CommunityListStore, Store<Intent, CommunityListModel, Nothing> by storeFactory.create(
        name = this::class.simpleName,
        initialState = CommunityListModel(listOf(), listOf()),
        bootstrapper = SimpleBootstrapper(Action.FetchCommunityList),
        executorFactory = ::ExecutorImpl,
        reducer = ReducerImpl()
    ) {}

    private sealed interface Action {
        object FetchCommunityList : Action
    }

    private sealed interface Message {
        data class Fetched(val model: CommunityListModel) : Message
    }

    private class ExecutorImpl : CoroutineExecutor<Intent, Action, CommunityListModel, Message, Nothing>() {

        override fun executeAction(action: Action, getState: () -> CommunityListModel) {
            when (action) {
                is Action.FetchCommunityList -> dispatch(
                    Message.Fetched(
                        CommunityListModel(
                            listOf("임산부 맘친", "육아 맘친", "익명 맘친", "중고 거래"),
                            listOf(
                                CommunityItem("1", "1", "1"),
                                CommunityItem("1", "1", "1"),
                                CommunityItem("1", "1", "1"),
                                CommunityItem("1", "1", "1"),
                                CommunityItem("1", "1", "1"),
                                CommunityItem("1", "1", "1"),
                                CommunityItem("1", "1", "1"),
                                CommunityItem("1", "1", "1"),
                                CommunityItem("1", "1", "1"),
                                CommunityItem("1", "1", "1"),
                                CommunityItem("1", "1", "1"),
                                CommunityItem("1", "1", "1"),
                                CommunityItem("1", "1", "1"),
                                CommunityItem("1", "1", "1"),
                                CommunityItem("1", "1", "1")
                            )
                        )
                    )
                )
            }
        }
    }

    private class ReducerImpl : Reducer<CommunityListModel, Message> {
        override fun CommunityListModel.reduce(msg: Message): CommunityListModel = when (msg) {
            is Message.Fetched -> copy(categories = msg.model.categories, items = msg.model.items)
        }
    }
}
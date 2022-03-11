package com.example.momchin.presentation.main.community.store

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.SimpleBootstrapper
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import com.example.momchin.domain.model.CommunityItem
import com.example.momchin.presentation.main.community.CommunityList.Model
import com.example.momchin.presentation.main.community.store.CommunityListStore.Intent

internal class CommunityListStoreProvider(
    private val storeFactory: StoreFactory = DefaultStoreFactory()
) {

    fun create(): CommunityListStore = object : CommunityListStore, Store<Intent, Model, Nothing> by storeFactory.create(
        name = this::class.simpleName,
        initialState = Model(listOf(), listOf()),
        bootstrapper = SimpleBootstrapper(Action.FetchCommunityList),
        executorFactory = ::ExecutorImpl,
        reducer = ReducerImpl()
    ) {}

    private sealed interface Action {
        object FetchCommunityList : Action
    }

    private sealed interface Message {
        data class Fetched(val model: Model) : Message
    }

    private class ExecutorImpl : CoroutineExecutor<Intent, Action, Model, Message, Nothing>() {

        override fun executeAction(action: Action, getState: () -> Model) {
            when (action) {
                is Action.FetchCommunityList -> dispatch(
                    Message.Fetched(
                        Model(
                            listOf("임산부 맘친", "육아 맘친", "익명 맘친", "중고 거래"),
                            listOf(
                                CommunityItem("1", "1", "1"),
                                CommunityItem("2", "2", "2"),
                                CommunityItem("3", "3", "3"),
                                CommunityItem("4", "4", "4"),
                                CommunityItem("5", "5", "5"),
                                CommunityItem("6", "6", "6"),
                                CommunityItem("7", "7", "7"),
                                CommunityItem("8", "8", "8"),
                                CommunityItem("9", "9", "9"),
                                CommunityItem("10", "10", "10"),
                                CommunityItem("11", "11", "11"),
                                CommunityItem("12", "12", "12"),
                                CommunityItem("13", "13", "13"),
                                CommunityItem("14", "14", "14"),
                                CommunityItem("15", "15", "15")
                            )
                        )
                    )
                )
            }
        }
    }

    private class ReducerImpl : Reducer<Model, Message> {
        override fun Model.reduce(msg: Message): Model = when (msg) {
            is Message.Fetched -> copy(categories = msg.model.categories, items = msg.model.items)
        }
    }
}
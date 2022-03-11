package com.example.momchin.presentation.main.bung.store

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.SimpleBootstrapper
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import com.example.momchin.domain.model.Bung
import com.example.momchin.presentation.main.bung.BungList.BungListModel
import com.example.momchin.presentation.main.bung.store.BungListStore.Intent

internal class BungListStoreProvider(
    private val storeFactory: StoreFactory = DefaultStoreFactory()
) {

    fun create(): BungListStore = object : BungListStore, Store<Intent, BungListModel, Nothing> by storeFactory.create(
        name = this::class.simpleName,
        initialState = BungListModel(listOf()),
        bootstrapper = SimpleBootstrapper(Action.FetchBungList),
        executorFactory = ::ExecutorImpl,
        reducer = ReducerImpl()
    ) {}

    private sealed interface Action {
        object FetchBungList : Action
    }

    private sealed interface Message {
        data class Fetched(val model: BungListModel) : Message
    }

    private class ExecutorImpl : CoroutineExecutor<Intent, Action, BungListModel, Message, Nothing>() {
        override fun executeAction(action: Action, getState: () -> BungListModel) {
            when (action) {
                is Action.FetchBungList -> dispatch(
                    Message.Fetched(
                        BungListModel(
                            listOf(
                                Bung("1", "1"),
                                Bung("1", "1"),
                                Bung("1", "1"),
                                Bung("1", "1"),
                                Bung("1", "1"),
                                Bung("1", "1"),
                                Bung("1", "1"),
                                Bung("1", "1"),
                                Bung("1", "1"),
                                Bung("1", "1"),
                                Bung("1", "1"),
                                Bung("1", "1"),
                                Bung("1", "1")
                            )
                        )
                    )
                )
            }
        }
    }

    private class ReducerImpl : Reducer<BungListModel, Message> {
        override fun BungListModel.reduce(msg: Message): BungListModel = when (msg) {
            is Message.Fetched -> copy(items = msg.model.items)
        }
    }
}
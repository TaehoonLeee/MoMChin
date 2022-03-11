package com.example.momchin.presentation.main.bung.store

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.SimpleBootstrapper
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import com.example.momchin.domain.model.Bung
import com.example.momchin.presentation.main.bung.BungList.Model
import com.example.momchin.presentation.main.bung.store.BungListStore.Intent

internal class BungListStoreProvider(
    private val storeFactory: StoreFactory = DefaultStoreFactory()
) {

    fun create(): BungListStore = object : BungListStore, Store<Intent, Model, Nothing> by storeFactory.create(
        name = this::class.simpleName,
        initialState = Model(listOf()),
        bootstrapper = SimpleBootstrapper(Action.FetchBungList),
        executorFactory = ::ExecutorImpl,
        reducer = ReducerImpl()
    ) {}

    private sealed interface Action {
        object FetchBungList : Action
    }

    private sealed interface Message {
        data class Fetched(val model: Model) : Message
    }

    private class ExecutorImpl : CoroutineExecutor<Intent, Action, Model, Message, Nothing>() {
        override fun executeAction(action: Action, getState: () -> Model) {
            when (action) {
                is Action.FetchBungList -> dispatch(
                    Message.Fetched(
                        Model(
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

    private class ReducerImpl : Reducer<Model, Message> {
        override fun Model.reduce(msg: Message): Model = when (msg) {
            is Message.Fetched -> copy(items = msg.model.items)
        }
    }
}
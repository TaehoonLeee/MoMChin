package com.example.momchin.presentation.main.bung.store

import com.arkivanov.mvikotlin.core.store.*
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import com.example.momchin.domain.model.Bung
import com.example.momchin.presentation.main.bung.store.BungDetailStore.Intent
import com.example.momchin.presentation.main.bung.BungDetail.BungDetailModel

internal class BungDetailStoreProvider(
    private val item: Bung,
    private val storeFactory: StoreFactory = DefaultStoreFactory()
) {

    fun create(): BungDetailStore = object : BungDetailStore, Store<Intent, BungDetailModel, Nothing> by storeFactory.create(
        name = this::class.simpleName,
        initialState = BungDetailModel(item, listOf()),
        bootstrapper = SimpleBootstrapper(Action.FetchBungDetailComment),
        executorFactory = ::ExecutorImpl,
        reducer = ReducerImpl()
    ) {}

    private sealed interface Action {
        object FetchBungDetailComment : Action
    }

    private sealed interface Message {
        data class Fetched(val model: BungDetailModel) : Message
    }

    private inner class ExecutorImpl : CoroutineExecutor<Intent, Action, BungDetailModel, Message, Nothing>() {

    }

    private class ReducerImpl : Reducer<BungDetailModel, Message> {
        override fun BungDetailModel.reduce(msg: Message): BungDetailModel = when (msg) {
            is Message.Fetched -> copy(detail = msg.model.detail, comments = msg.model.comments)
        }

    }
}
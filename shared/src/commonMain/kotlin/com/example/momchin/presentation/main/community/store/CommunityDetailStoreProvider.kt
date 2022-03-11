package com.example.momchin.presentation.main.community.store

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.SimpleBootstrapper
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import com.example.momchin.domain.model.CommunityItem
import com.example.momchin.presentation.main.community.CommunityDetail.Model
import com.example.momchin.presentation.main.community.CommunityDetail.Comment
import com.example.momchin.presentation.main.community.store.CommunityDetailStore.Intent

internal class CommunityDetailStoreProvider(
    private val category: String,
    private val item: CommunityItem,
    private val storeFactory: StoreFactory = DefaultStoreFactory()
) {

    fun create(): CommunityDetailStore = object : CommunityDetailStore, Store<Intent, Model, Nothing> by storeFactory.create(
        name = this::class.simpleName,
        initialState = Model(category, item, listOf()),
        bootstrapper = SimpleBootstrapper(Action.FetchDetailContent),
        executorFactory = ::ExecutorImpl,
        reducer = ReducerImpl()
    ) {}

    private sealed interface Action {
        object FetchDetailContent: Action
    }

    private sealed interface Message {
        data class Fetched(val data: Model): Message
    }

    private inner class ExecutorImpl : CoroutineExecutor<Intent, Action, Model, Message, Nothing>() {
        override fun executeAction(action: Action, getState: () -> Model) = when (action) {
            is Action.FetchDetailContent -> dispatch(
                Message.Fetched(
                    Model(
                        category,
                        item,
                        listOf(
                            Comment("1", "1"),
                            Comment("1", "1"),
                            Comment("1", "1"),
                            Comment("1", "1"),
                            Comment("1", "1"),
                            Comment("1", "1"),
                            Comment("1", "1")
                        )
                    )
                )
            )
        }

        override fun executeIntent(intent: Intent, getState: () -> Model) {
            when (intent) {
                is Intent.Comment -> {}
            }
        }
    }

    private class ReducerImpl : Reducer<Model, Message> {
        override fun Model.reduce(msg: Message): Model = when (msg) {
            is Message.Fetched -> copy(detail = msg.data.detail, comments = msg.data.comments)
        }

    }
}
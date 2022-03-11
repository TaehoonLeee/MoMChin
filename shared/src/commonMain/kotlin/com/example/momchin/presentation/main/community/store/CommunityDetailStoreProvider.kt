package com.example.momchin.presentation.main.community.store

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.SimpleBootstrapper
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import com.example.momchin.domain.model.CommunityItem
import com.example.momchin.presentation.main.community.CommunityDetail.CommunityDetailModel
import com.example.momchin.presentation.main.community.CommunityDetail.Comment
import com.example.momchin.presentation.main.community.CommunityList
import com.example.momchin.presentation.main.community.store.CommunityDetailStore.Intent

internal class CommunityDetailStoreProvider(
    private val category: String,
    private val item: CommunityItem,
    private val storeFactory: StoreFactory = DefaultStoreFactory()
) {

    fun create(): CommunityDetailStore = object : CommunityDetailStore, Store<Intent, CommunityDetailModel, Nothing> by storeFactory.create(
        name = this::class.simpleName,
        initialState = CommunityDetailModel(category, item, listOf()),
        bootstrapper = SimpleBootstrapper(Action.FetchDetailContent),
        executorFactory = ::ExecutorImpl,
        reducer = ReducerImpl()
    ) {}

    private sealed interface Action {
        object FetchDetailContent: Action
    }

    private sealed interface Message {
        data class Fetched(val data: CommunityDetailModel): Message
    }

    private inner class ExecutorImpl : CoroutineExecutor<Intent, Action, CommunityDetailModel, Message, Nothing>() {
        override fun executeAction(action: Action, getState: () -> CommunityDetailModel) = when (action) {
            is Action.FetchDetailContent -> dispatch(
                Message.Fetched(
                    CommunityDetailModel(
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

        override fun executeIntent(intent: Intent, getState: () -> CommunityDetailModel) {
            when (intent) {
                is Intent.Comment -> {}
            }
        }
    }

    private class ReducerImpl : Reducer<CommunityDetailModel, Message> {
        override fun CommunityDetailModel.reduce(msg: Message): CommunityDetailModel = when (msg) {
            is Message.Fetched -> copy(detail = msg.data.detail, comments = msg.data.comments)
        }

    }
}
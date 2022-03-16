package com.example.momchin.presentation.main

import com.arkivanov.decompose.router.RouterState
import com.arkivanov.decompose.value.Value
import com.example.momchin.presentation.main.bung.BungDetail
import com.example.momchin.presentation.main.bung.BungList
import com.example.momchin.presentation.main.community.CommunityDetail
import com.example.momchin.presentation.main.community.CommunityList
import com.example.momchin.presentation.main.message.MessageDetail
import com.example.momchin.presentation.main.message.MessageList
import com.example.momchin.util.AnimatedChild

interface MoMChinMain {

    val communityRouterState: Value<RouterState<*, CommunityChild>>
    val animatedCRouterState: Value<List<AnimatedChild<CommunityChild>>>

    val bungRouterState: Value<RouterState<*, BungChild>>
    val messageRouterState: Value<RouterState<*, MessageChild>>

    sealed interface CommunityChild {
        data class List(val component: CommunityList) : CommunityChild
        data class Detail(val component: CommunityDetail) : CommunityChild
    }

    sealed interface BungChild {
        data class List(val component: BungList) : BungChild
        data class Detail(val component: BungDetail) : BungChild
    }

    sealed interface MessageChild {
        data class List(val component: MessageList) : MessageChild
        data class Detail(val component: MessageDetail) : MessageChild
    }
}
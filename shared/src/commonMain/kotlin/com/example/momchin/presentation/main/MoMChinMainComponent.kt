package com.example.momchin.presentation.main

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.childContext
import com.arkivanov.decompose.router.RouterState
import com.arkivanov.decompose.value.Value
import com.example.momchin.presentation.main.bung.BungRootComponent
import com.example.momchin.presentation.main.community.CommunityRootComponent
import com.example.momchin.presentation.main.message.MessageRootComponent
import com.example.momchin.util.AnimatedChild
import com.example.momchin.util.asValue

internal class MoMChinMainComponent(
    componentContext: ComponentContext
) : MoMChinMain, ComponentContext by componentContext {

    private val communityRoot = CommunityRootComponent(childContext("Community"))
    private val bungRoot = BungRootComponent(childContext("Bung"))
    private val messageRoot = MessageRootComponent(childContext("Message"))

    override val communityRouterState: Value<RouterState<*, MoMChinMain.CommunityChild>>
        = communityRoot.routerState

    override val animatedCRouterState: Value<List<AnimatedChild<MoMChinMain.CommunityChild>>>
        = communityRouterState.asValue(lifecycle)

    override val bungRouterState: Value<RouterState<*, MoMChinMain.BungChild>>
        = bungRoot.routerState

    override val messageRouterState: Value<RouterState<*, MoMChinMain.MessageChild>>
        = messageRoot.routerState

}
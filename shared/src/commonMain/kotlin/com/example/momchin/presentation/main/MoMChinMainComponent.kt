package com.example.momchin.presentation.main

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.childContext
import com.arkivanov.decompose.router.RouterState
import com.arkivanov.decompose.value.Value
import com.example.momchin.presentation.main.bung.BungRootComponent
import com.example.momchin.presentation.main.community.CommunityRootComponent

internal class MoMChinMainComponent(
    componentContext: ComponentContext
) : MoMChinMain, ComponentContext by componentContext {

    private val communityRoot = CommunityRootComponent(childContext("Community"))
    private val bungRoot = BungRootComponent(childContext("Bung"))

    override val communityRouterState: Value<RouterState<*, MoMChinMain.CommunityChild>>
        = communityRoot.routerState

    override val bungRouterState: Value<RouterState<*, MoMChinMain.BungChild>>
        = bungRoot.routerState

}
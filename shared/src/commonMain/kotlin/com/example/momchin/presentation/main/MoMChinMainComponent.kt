package com.example.momchin.presentation.main

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.Router
import com.arkivanov.decompose.router.RouterState
import com.arkivanov.decompose.router.push
import com.arkivanov.decompose.router.router
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import com.example.momchin.presentation.main.bung.BungDetailComponent
import com.example.momchin.presentation.main.bung.BungList
import com.example.momchin.presentation.main.bung.BungListComponent
import com.example.momchin.presentation.main.community.CommunityDetailComponent
import com.example.momchin.presentation.main.community.CommunityList
import com.example.momchin.presentation.main.community.CommunityListComponent
import com.example.momchin.util.extension.Consumer

internal class MoMChinMainComponent(
    componentContext: ComponentContext
) : MoMChinMain, ComponentContext by componentContext {

    private val communityRouter: Router<Configuration, MoMChinMain.CommunityChild> = router(
        key = "Community Router",
        initialConfiguration = Configuration.List,
        handleBackButton = true,
        childFactory = ::resolveCommunityChild
    )

    private val bungRouter: Router<Configuration, MoMChinMain.BungChild> = router(
        key = "Bung Router",
        initialConfiguration = Configuration.List,
        handleBackButton = true,
        childFactory = ::resolveBungChild
    )

    override val communityRouterState: Value<RouterState<*, MoMChinMain.CommunityChild>>
        = communityRouter.state

    override val bungRouterState: Value<RouterState<*, MoMChinMain.BungChild>>
        = bungRouter.state

    private fun resolveCommunityChild(
        configuration: Configuration,
        componentContext: ComponentContext
    ) = when (configuration) {
        is Configuration.List -> MoMChinMain.CommunityChild.List(CommunityListComponent(componentContext, Consumer(::onCommunityOutput)))
        is Configuration.Detail -> MoMChinMain.CommunityChild.Detail(CommunityDetailComponent(componentContext))
    }

    private fun resolveBungChild(
        configuration: Configuration,
        componentContext: ComponentContext
    ) = when (configuration) {
        is Configuration.List -> MoMChinMain.BungChild.List(BungListComponent(componentContext, Consumer(::onBungOutput)))
        is Configuration.Detail -> MoMChinMain.BungChild.Detail(BungDetailComponent(componentContext))
    }

    private fun onCommunityOutput(output: CommunityList.Output) = when (output) {
        is CommunityList.Output.Selected -> communityRouter.push(Configuration.Detail)
    }

    private fun onBungOutput(output: BungList.Output) = when (output) {
        is BungList.Output.Selected -> bungRouter.push(Configuration.Detail)
    }

    private sealed interface Configuration : Parcelable {
        @Parcelize
        object List : Configuration

        @Parcelize
        object Detail : Configuration
    }
}
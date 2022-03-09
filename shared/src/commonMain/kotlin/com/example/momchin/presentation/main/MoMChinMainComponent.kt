package com.example.momchin.presentation.main

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.*
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import com.example.momchin.presentation.main.bung.BungDetail
import com.example.momchin.presentation.main.bung.BungDetailComponent
import com.example.momchin.presentation.main.bung.BungList
import com.example.momchin.presentation.main.bung.BungListComponent
import com.example.momchin.presentation.main.community.CommunityDetail
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
        is Configuration.List -> MoMChinMain.CommunityChild.List(CommunityListComponent(componentContext, Consumer(::onCommunityListOutput)))
        is Configuration.Detail<*> -> MoMChinMain.CommunityChild.Detail(CommunityDetailComponent(
            componentContext,
            configuration.item as CommunityList.CommunityItem,
            configuration.category?: "",
            Consumer(::onCommunityDetailOutput)
        ))
    }

    private fun resolveBungChild(
        configuration: Configuration,
        componentContext: ComponentContext
    ) = when (configuration) {
        is Configuration.List -> MoMChinMain.BungChild.List(BungListComponent(componentContext, Consumer(::onBungListOutput)))
        is Configuration.Detail<*> -> MoMChinMain.BungChild.Detail(BungDetailComponent(
            componentContext,
            configuration.item as BungList.Bung,
            Consumer(::onBungDetailOutput)
        ))
    }

    private fun onCommunityListOutput(output: CommunityList.Output) = when (output) {
        is CommunityList.Output.Selected -> communityRouter.push(Configuration.Detail(output.item, output.category))
    }

    private fun onCommunityDetailOutput(output: CommunityDetail.Output) = when (output) {
        is CommunityDetail.Output.Finished -> communityRouter.pop()
    }

    private fun onBungListOutput(output: BungList.Output) = when (output) {
        is BungList.Output.Selected -> bungRouter.push(Configuration.Detail(output.item))
    }

    private fun onBungDetailOutput(output: BungDetail.Output) = when (output) {
        is BungDetail.Output.Finished -> bungRouter.pop()
    }

    private sealed interface Configuration : Parcelable {
        @Parcelize
        object List : Configuration

        @Parcelize
        data class Detail<Item : Parcelable>(val item: Item, val category: String? = null) : Configuration
    }
}
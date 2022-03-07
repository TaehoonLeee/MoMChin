package com.example.momchin.presentation.main

import com.arkivanov.decompose.router.RouterState
import com.arkivanov.decompose.value.Value
import com.example.momchin.presentation.main.bung.BungDetail
import com.example.momchin.presentation.main.bung.BungList
import com.example.momchin.presentation.main.community.CommunityDetail
import com.example.momchin.presentation.main.community.CommunityList

interface MoMChinMain {

    val communityRouterState: Value<RouterState<*, CommunityChild>>
    val bungRouterState: Value<RouterState<*, BungChild>>

    sealed interface CommunityChild {
        data class List(val component: CommunityList) : CommunityChild
        data class Detail(val component: CommunityDetail) : CommunityChild
    }

    sealed interface BungChild {
        data class List(val component: BungList) : BungChild
        data class Detail(val component: BungDetail) : BungChild
    }
}
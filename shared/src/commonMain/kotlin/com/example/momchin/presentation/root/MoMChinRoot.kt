package com.example.momchin.presentation.root

import com.arkivanov.decompose.router.RouterState
import com.arkivanov.decompose.value.Value
import com.example.momchin.presentation.login.MoMChinLogin
import com.example.momchin.presentation.main.MoMChinMain

interface MoMChinRoot {

    val routerState: Value<RouterState<*, Child>>

    sealed interface Child {
        data class Login(val component: MoMChinLogin) : Child
        data class Main(val component: MoMChinMain): Child
    }
}
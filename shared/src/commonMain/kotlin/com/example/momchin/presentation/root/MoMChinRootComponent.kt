package com.example.momchin.presentation.root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.*
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import com.badoo.reaktive.base.Consumer
import com.example.momchin.presentation.login.MoMChinLogin
import com.example.momchin.presentation.login.MoMChinLoginComponent
import com.example.momchin.presentation.main.MoMChinMain
import com.example.momchin.presentation.main.MoMChinMainComponent
import com.example.momchin.util.extension.Consumer

class MoMChinRootComponent(
    componentContext: ComponentContext
) : MoMChinRoot, ComponentContext by componentContext {

    private val router: Router<Configuration, MoMChinRoot.Child> = router(
        initialConfiguration = Configuration.Login,
        handleBackButton = true,
        childFactory = ::resolveChild
    )

    override val routerState: Value<RouterState<*, MoMChinRoot.Child>> = router.state

    private fun resolveChild(
        configuration: Configuration,
        componentContext: ComponentContext
    ) = when (configuration) {
        is Configuration.Login -> MoMChinRoot.Child.Login(MoMChinLoginComponent(componentContext, Consumer(::onLoginOutput)))
        is Configuration.Main -> MoMChinRoot.Child.Main(MoMChinMainComponent(componentContext))
    }

    private fun onLoginOutput(output: MoMChinLogin.Output) = when (output) {
        is MoMChinLogin.Output.LoginResult -> router.replaceCurrent(Configuration.Main)
    }

    private sealed interface Configuration : Parcelable {
        @Parcelize
        object Login : Configuration

        @Parcelize
        object Main : Configuration
    }
}
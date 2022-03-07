package com.example.momchin.presentation.login

import com.arkivanov.decompose.ComponentContext
import com.badoo.reaktive.base.Consumer
import com.badoo.reaktive.base.invoke

internal class MoMChinLoginComponent(
    componentContext: ComponentContext,
    private val output: Consumer<MoMChinLogin.Output>
) : MoMChinLogin, ComponentContext by componentContext {

    override fun onLogin() {
        output(MoMChinLogin.Output.LoginResult)
    }
}
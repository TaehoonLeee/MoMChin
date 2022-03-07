package com.example.momchin.presentation.main.bung

import com.arkivanov.decompose.ComponentContext
import com.badoo.reaktive.base.Consumer
import com.badoo.reaktive.base.invoke

internal class BungListComponent(
    componentContext: ComponentContext,
    private val output: Consumer<BungList.Output>
) : BungList, ComponentContext by componentContext {

    override fun onItemClicked() {
        output(BungList.Output.Selected)
    }
}
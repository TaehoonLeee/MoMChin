package com.example.momchin.presentation.main.bung

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.*
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import com.example.momchin.domain.model.Bung
import com.example.momchin.presentation.main.MoMChinMain
import com.example.momchin.util.extension.Consumer

class BungRootComponent(
	componentContext: ComponentContext
) : ComponentContext by componentContext {

	private val router: Router<Configuration, MoMChinMain.BungChild> = router(
		initialConfiguration = Configuration.List,
		handleBackButton = true,
		childFactory = ::resolveBungChild
	)

	val routerState: Value<RouterState<*, MoMChinMain.BungChild>> = router.state

	private fun resolveBungChild(
		configuration: Configuration,
		componentContext: ComponentContext
	) = when (configuration) {
		is Configuration.List -> MoMChinMain.BungChild.List(BungListComponent(componentContext, Consumer(::onBungListOutput)))
		is Configuration.Detail -> MoMChinMain.BungChild.Detail(BungDetailComponent(
			componentContext,
			configuration.item,
			Consumer(::onBungDetailOutput)
		))
	}

	private fun onBungListOutput(output: BungList.Output) = when (output) {
		is BungList.Output.Selected -> router.push(Configuration.Detail(output.item))
	}

	private fun onBungDetailOutput(output: BungDetail.Output) = when (output) {
		is BungDetail.Output.Finished -> router.pop()
	}

	private sealed interface Configuration : Parcelable {
		@Parcelize
		object List : Configuration

		@Parcelize
		data class Detail(val item: Bung) : Configuration
	}
}
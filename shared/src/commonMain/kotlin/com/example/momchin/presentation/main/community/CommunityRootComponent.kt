package com.example.momchin.presentation.main.community

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.*
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import com.example.momchin.domain.model.CommunityItem
import com.example.momchin.presentation.main.MoMChinMain
import com.example.momchin.util.extension.Consumer

internal class CommunityRootComponent(
	componentContext: ComponentContext
) : ComponentContext by componentContext {

	private val router: Router<Configuration, MoMChinMain.CommunityChild> = router(
		initialConfiguration = Configuration.List,
		handleBackButton = true,
		childFactory = ::resolveCommunityChild
	)

	val routerState: Value<RouterState<*, MoMChinMain.CommunityChild>> = router.state

	private fun resolveCommunityChild(
		configuration: Configuration,
		componentContext: ComponentContext
	) = when (configuration) {
		is Configuration.List -> MoMChinMain.CommunityChild.List(CommunityListComponent(componentContext, Consumer(::onCommunityListOutput)))
		is Configuration.Detail -> MoMChinMain.CommunityChild.Detail(CommunityDetailComponent(
			componentContext,
			configuration.item,
			configuration.category,
			Consumer(::onCommunityDetailOutput)
		))
	}

	private fun onCommunityListOutput(output: CommunityList.Output) = when (output) {
		is CommunityList.Output.Selected -> router.push(Configuration.Detail(output.item, output.category))
	}

	private fun onCommunityDetailOutput(output: CommunityDetail.Output) = when (output) {
		is CommunityDetail.Output.Finished -> router.pop()
	}

	private sealed interface Configuration : Parcelable {
		@Parcelize
		object List : Configuration

		@Parcelize
		data class Detail(val item: CommunityItem, val category: String) : Configuration
	}
}
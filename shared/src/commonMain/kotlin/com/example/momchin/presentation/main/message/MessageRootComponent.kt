package com.example.momchin.presentation.main.message

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.Router
import com.arkivanov.decompose.router.RouterState
import com.arkivanov.decompose.router.router
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import com.example.momchin.domain.model.Message
import com.example.momchin.presentation.main.MoMChinMain

class MessageRootComponent(
	componentContext: ComponentContext
) : ComponentContext by componentContext {

	private val router: Router<Configuration, MoMChinMain.MessageChild> = router(
		initialConfiguration = Configuration.List,
		handleBackButton = true,
		childFactory = ::resolveMessageChild
	)

	val routerState: Value<RouterState<*, MoMChinMain.MessageChild>> = router.state

	private fun resolveMessageChild(
		configuration: Configuration,
		componentContext: ComponentContext
	) = when (configuration) {
		is Configuration.List -> MoMChinMain.MessageChild.List(MessageListComponent(componentContext))
		is Configuration.Detail -> MoMChinMain.MessageChild.Detail(MessageDetailComponent(componentContext))
	}

	private sealed interface Configuration : Parcelable {
		@Parcelize
		object List : Configuration

		@Parcelize
		object Detail : Configuration
	}
}
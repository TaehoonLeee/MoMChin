package com.example.momchin.presentation.main.message

import com.arkivanov.decompose.ComponentContext

class MessageDetailComponent(
	componentContext: ComponentContext
) : MessageDetail, ComponentContext by componentContext {
}
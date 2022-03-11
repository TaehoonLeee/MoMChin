package com.example.momchin.domain.model

import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize

@Parcelize
data class CommunityItem(
	val user: String,
	val title: String,
	val content: String,
	val profileImage: String = ""
) : Parcelable

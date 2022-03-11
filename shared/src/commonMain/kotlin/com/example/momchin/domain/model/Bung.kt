package com.example.momchin.domain.model

import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize

@Parcelize
data class Bung(
	val title: String,
	val place: String
) : Parcelable

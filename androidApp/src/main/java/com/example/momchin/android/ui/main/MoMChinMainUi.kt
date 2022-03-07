package com.example.momchin.android.ui.main

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.extensions.compose.jetpack.Children
import com.example.momchin.android.ui.bottomnav.BottomNavContent
import com.example.momchin.presentation.main.MoMChinMain

@Composable
fun MoMChinMainContent(component: MoMChinMain) {
    BottomNavContent(component)
}
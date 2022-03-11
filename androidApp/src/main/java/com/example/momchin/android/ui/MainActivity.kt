package com.example.momchin.android.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.arkivanov.decompose.defaultComponentContext
import com.arkivanov.decompose.extensions.compose.jetpack.Children
import com.example.momchin.Greeting
import com.example.momchin.android.ui.login.LoginContent
import com.example.momchin.android.ui.main.MainContent
import com.example.momchin.presentation.root.MoMChinRoot
import com.example.momchin.presentation.root.MoMChinRootComponent

fun greet(): String {
    return Greeting().greeting()
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val momChinRoot = MoMChinRootComponent(defaultComponentContext())

        setContent {
            Children(momChinRoot.routerState) {
                when (val child = it.instance) {
                    is MoMChinRoot.Child.Login -> LoginContent(child.component)
                    is MoMChinRoot.Child.Main -> MainContent(child.component)
                }
            }
        }
    }
}

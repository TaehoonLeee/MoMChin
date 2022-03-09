package com.example.momchin.android.ui.bung

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.jetpack.subscribeAsState
import com.example.momchin.presentation.main.bung.BungDetail

@Composable
fun BungDetailContent(component: BungDetail) {
    BackHandler(true) {
        component.onBackButtonClick()
    }

    val model by component.model.subscribeAsState()

    Column(Modifier.fillMaxSize()) {
        IconButton(onClick = component::onBackButtonClick) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = null
            )
        }


    }
}
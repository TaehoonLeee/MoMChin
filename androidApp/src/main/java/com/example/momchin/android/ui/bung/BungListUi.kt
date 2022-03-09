package com.example.momchin.android.ui.bung

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.jetpack.subscribeAsState
import com.example.momchin.presentation.main.bung.BungList

@Composable
fun BungListContent(component: BungList) {
    val model by component.model.subscribeAsState()

    LazyColumn {
        items(model.items) {
            Card(modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = {
                    component.onItemClicked(it)
                })
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(it.title)
                }
            }
        }
    }

}
package com.example.momchin.android.ui.community

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.jetpack.subscribeAsState
import com.example.momchin.presentation.main.community.CommunityList

@Composable
fun CommunityListContent(component: CommunityList) {
    val model by component.model.subscribeAsState()

    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            items(model.categories) {
                Text(text = it)
            }
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(horizontal = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(model.items) {
                Card(modifier = Modifier
                    .fillMaxWidth()
                    .clickable(onClick = component::onItemClicked)
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(it.user)
                        Text(it.title)
                        Text(it.content)
                    }
                }
            }
        }
    }
}
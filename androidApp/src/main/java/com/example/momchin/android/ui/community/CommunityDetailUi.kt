package com.example.momchin.android.ui.community

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.jetpack.subscribeAsState
import com.example.momchin.presentation.main.community.CommunityDetail

@Composable
fun CommunityDetailContent(component: CommunityDetail) {
    BackHandler(true) {
        component.onBackButtonClick()
    }

    val model by component.model.subscribeAsState()

    Column(Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            IconButton(onClick = component::onBackButtonClick) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = null
                )
            }

            Text(text = model.category)
        }

        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(12.dp)
            .background(Color.Black))

        Column {
            Text(text = model.detail.user)
            Text(text = model.detail.title)
            Text(text = model.detail.content)
        }

        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(12.dp)
            .background(Color.Black))

        LazyColumn {
            items(model.comments) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(text = it.writer)
                    Text(text = it.content)
                }
            }
        }
    }
}
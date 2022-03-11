package com.example.momchin.android.ui.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.momchin.presentation.login.MoMChinLogin

@Composable
fun LoginContent(component: MoMChinLogin) {
    Box(Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.align(Alignment.Center),
            verticalArrangement = Arrangement.spacedBy(27.dp)
        ) {
            repeat(3) {
                IconButton(onClick = component::onLogin) {
                    Image(imageVector = Icons.Default.Face, contentDescription = null)
                }
            }
        }
    }
}
@file:OptIn(ExperimentalMaterial3Api::class)

package org.michaelbel.mydearsubscribers.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainActivityContent(
    viewModel: MainViewModel = koinViewModel()
) {
    val entities by viewModel.entities.collectAsStateWithLifecycle()
    if (entities.isEmpty()) return

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF101411)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "${entities.first().type} – ${entities.first().followers} подписчиков",
            style = MaterialTheme.typography.bodyLarge.copy(fontSize = 32.sp),
            color = Color(0xFFFFFFFF)
        )
    }
}
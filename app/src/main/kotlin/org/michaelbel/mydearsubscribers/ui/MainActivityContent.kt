@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3AdaptiveApi::class)

package org.michaelbel.mydearsubscribers.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel
import org.michaelbel.mydearsubscribers.room.FollowersEntity

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
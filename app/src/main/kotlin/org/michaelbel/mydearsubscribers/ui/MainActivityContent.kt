@file:OptIn(ExperimentalMaterial3Api::class)

package org.michaelbel.mydearsubscribers.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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

    InnerContent(entities)
}

@Composable
private fun InnerContent(
    entities: List<FollowersEntity>
) {
    val github = entities.first()
    val youtube = entities.first()

    val bg = Brush.verticalGradient(
        colorStops = arrayOf(
            0.00F to Color(0xFF101411),
            0.15F to Color(0xFF131615),
            0.30F to Color(0xFF191313),
            0.45F to Color(0xFF241414),
            0.60F to Color(0xFF3A1515),
            0.75F to Color(0xFF6A0E0E),
            0.88F to Color(0xFFB01010),
            1.00F to Color(0xFFFF0000)
        )
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(bg)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .weight(1F)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "GitHub",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontSize = 72.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    color = Color(0xFFFFFFFF)
                )

                FollowersRow(
                    count = github.followers,
                    labelColor = Color(0xFFFFFFFF),
                    badgeTextColor = Color(0xFF101411),
                    modifier = Modifier.padding(top = 12.dp)
                )
            }

            Column(
                modifier = Modifier
                    .weight(1F)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "YouTube",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontSize = 72.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    color = Color(0xFFFFFFFF)
                )

                FollowersRow(
                    count = youtube.followers,
                    labelColor = Color(0xFFFFFFFF),
                    badgeTextColor = Color(0xFF101411),
                    modifier = Modifier.padding(top = 12.dp)
                )
            }
        }
    }
}

@Preview
@Composable
private fun InnerContentPreview() {
    AppTheme {
        InnerContent(
            entities = listOf(
                FollowersEntity.Empty.copy(followers = 300),
                FollowersEntity.Empty.copy(followers = 300)
            )
        )
    }
}

@Composable
private fun FollowersRow(
    count: Int,
    labelColor: Color,
    badgeTextColor: Color,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .size(64.dp)
                .background(Color.White, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = count.toString(),
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold
                ),
                color = badgeTextColor,
                textAlign = TextAlign.Center
            )
        }

        Spacer(
            modifier = Modifier.size(12.dp)
        )

        Text(
            text = "подписчиков",
            style = MaterialTheme.typography.bodyLarge.copy(
                fontSize = 28.sp,
                fontWeight = FontWeight.Medium
            ),
            color = labelColor
        )
    }
}
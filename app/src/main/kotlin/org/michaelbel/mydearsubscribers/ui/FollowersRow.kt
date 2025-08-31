package org.michaelbel.mydearsubscribers.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun FollowersRow(
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
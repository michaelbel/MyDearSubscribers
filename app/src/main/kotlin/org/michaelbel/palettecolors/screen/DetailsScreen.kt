@file:OptIn(ExperimentalMaterial3Api::class)

package org.michaelbel.palettecolors.screen

import android.graphics.BitmapFactory
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.plus
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScaffoldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.palette.graphics.Palette
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.michaelbel.palettecolors.boarList

@Composable
fun DetailsScreen(
    boarId: Int,
    onBack: () -> Unit
) {
    val boar = boarList.first { it.id == boarId }
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    val context = LocalContext.current

    var containerColor by remember { mutableStateOf<Color?>(null) }
    var onContainerColor by remember { mutableStateOf<Color?>(null) }

    LaunchedEffect(boar.drawableRes) {
        val palette = withContext(Dispatchers.Default) {
            val bitmap = BitmapFactory.decodeResource(context.resources, boar.drawableRes)
            Palette.from(bitmap).generate()
        }
        val swatch = palette.vibrantSwatch ?: palette.dominantSwatch
        swatch?.let {
            containerColor = Color(it.rgb)
            onContainerColor = Color(it.bodyTextColor)
        }
    }

    val defaultContainerColor = MaterialTheme.colorScheme.background
    val defaultOnContainerColor = MaterialTheme.colorScheme.onBackground

    val animatedContainerColor by animateColorAsState(
        targetValue = containerColor ?: defaultContainerColor,
        animationSpec = tween(durationMillis = 400, easing = LinearEasing),
        label = "containerColor"
    )
    val animatedOnContainerColor by animateColorAsState(
        targetValue = onContainerColor ?: defaultOnContainerColor,
        animationSpec = tween(durationMillis = 400, easing = LinearEasing),
        label = "onContainerColor"
    )

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = boar.name,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 2
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
                            contentDescription = null
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = animatedContainerColor,
                    scrolledContainerColor = animatedContainerColor,
                    titleContentColor = animatedOnContainerColor,
                    navigationIconContentColor = animatedOnContainerColor
                ),
                scrollBehavior = scrollBehavior
            )
        },
        containerColor = animatedContainerColor,
        contentWindowInsets = ScaffoldDefaults.contentWindowInsets.only(WindowInsetsSides.Horizontal)
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = innerPadding + PaddingValues(
                start = 16.dp,
                top = 16.dp,
                end = 16.dp,
                bottom = WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding()
            ),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Image(
                    painter = painterResource(boar.drawableRes),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(16F / 9F)
                        .clip(RoundedCornerShape(MaterialTheme.shapes.large.topStart)),
                    contentScale = ContentScale.Crop
                )
            }
            item {
                Text(
                    text = boar.description,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        color = animatedOnContainerColor
                    )
                )
            }
        }
    }
}

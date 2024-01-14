package com.github.savrov.gradle.sample.client.mobile.shared.system

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalMinimumInteractiveComponentEnforcement
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.rememberAsyncImagePainter

@Composable
fun SharedScreen() {
    SharedComponent()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun SharedComponent(
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(Icons.Filled.ArrowBack, null)
                    }
                },
                title = {
                    Text(
                        text = "Sample",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        lineHeight = 24.sp,
                        letterSpacing = 0.sp,
                        textAlign = TextAlign.Center,
                    )
                },
                actions = {
                    IconButton(onClick = {}) {
                        Image(
                            modifier = Modifier.size(32.dp).clip(CircleShape),
                            painter = rememberAsyncImagePainter(
                                "https://ui-avatars.com/api/?background=random&name=Pavel+Savrov&size=32",
                            ),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                        )
                    }
                }
            )
        },
        content = {
            Box(modifier = Modifier.padding(it)) {
                FeedComponent()
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(32.dp)
                        .background(
                            brush = Brush.verticalGradient(
                                startY = 0f,
                                endY = 64f,
                                colors = listOf(MaterialTheme.colorScheme.surface, Color.Transparent)
                            )
                        )
                )
            }
        },
    )
}

@Composable
private fun FeedComponent(
    modifier: Modifier = Modifier,
) {
    LazyColumn(modifier = modifier.background(MaterialTheme.colorScheme.surface)) {
        item {
            DateComponent("05 Oct 2021")
        }
        item {
            PostComponent(
                name = "Jim Sparks",
                summary = "marked task as complete",
                time = "03:17PM",
            ) {
                Column(modifier = Modifier.padding(it)) {
                    CheckboxComponent(
                        isChecked = true,
                        label = "Budget and contract",
                        onCheckedChange = {},
                    )
                    CheckboxComponent(
                        isChecked = true,
                        label = "Search for a UI kit",
                        onCheckedChange = {},
                    )
                }
            }
        }
        item {
            DateComponent("06 Oct 2021")
        }
        item {
            PostComponent(
                name = "Evelyn Grant",
                summary = "assgined new task",
                time = "10:55PM",
            ) {
                CheckboxComponent(
                    modifier = Modifier.padding(it),
                    isChecked = true,
                    label = "Design new dashboard",
                    isEnabled = false,
                    onCheckedChange = {},
                )
            }
        }
        item {
            Spacer(modifier = Modifier.height(16.dp))
        }
        item {
            PostComponent(
                name = "Martha Bradley",
                summary = "added 5 new attachments",
                time = "09:22AM",
            ) {
                GalleryComponent(
                    urls = listOf(
                        "https://picsum.photos/seed/1/200/300",
                        "https://picsum.photos/seed/2/200/300",
                        "https://picsum.photos/seed/3/200/300",
                        "https://picsum.photos/seed/4/200/300",
                        "https://picsum.photos/seed/5/200/300",
                    ),
                    modifier = Modifier.padding(it)
                )
            }
        }
        item {
            DateComponent("07 Oct 2021")
        }
        item {
            PostComponent(
                name = "Ivan Cannon",
                summary = "added new comment",
                time = "11:23AM",
            ) {
                CommentComponent(
                    title = "Budget and contract",
                    text = "Canada tries to turn its A.I. ideas into dollars inside the hotel industry’s plan to combat Airbnb",
                    modifier = Modifier.padding(it)
                )
            }
        }
        item {
            Spacer(modifier = Modifier.height(16.dp))
        }
        item {
            PostComponent(
                name = "Eleanor Summers",
                summary = "assgined new task",
                time = "11:04PM",
            ) {
                Column(modifier = Modifier.padding(it)) {
                    CheckboxComponent(
                        isChecked = false,
                        isEnabled = false,
                        label = "Fix issues",
                        onCheckedChange = {},
                    )
                    CheckboxComponent(
                        isChecked = false,
                        isEnabled = false,
                        label = "Prepare HTML & CSS",
                        onCheckedChange = {},
                    )
                    CheckboxComponent(
                        isChecked = false,
                        isEnabled = false,
                        label = "Design mobile UI kit",
                        onCheckedChange = {},
                    )
                }
            }
        }
        item {
            DateComponent("08 Oct 2021")
        }
        item {
            PostComponent(
                name = "Evelyn Grant",
                summary = "marked task as complete",
                time = "10:55PM",
            ) {
                CheckboxComponent(
                    modifier = Modifier.padding(it),
                    isChecked = true,
                    label = "Search for a UI kit for dashbaord",
                    isEnabled = true,
                    onCheckedChange = {},
                )
            }
        }
    }
}

@Composable
private fun DateComponent(
    date: String,
) {
    Text(
        modifier = Modifier.fillMaxWidth().padding(16.dp),
        text = date,
        fontSize = 12.sp,
        fontWeight = FontWeight.Bold,
        lineHeight = 18.sp,
        letterSpacing = 0.sp,
        textAlign = TextAlign.Center,
    )
}

@Composable
private fun PostComponent(
    name: String,
    summary: String,
    time: String,
    content: @Composable (PaddingValues) -> Unit,
) {
    Card(
        modifier = Modifier.padding(horizontal = 16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
        border = BorderStroke(
            width = 1.dp,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f),
        ),
    ) {
        Row(modifier = Modifier.padding(14.dp)) {
            Image(
                modifier = Modifier.size(32.dp).clip(CircleShape),
                painter = rememberAsyncImagePainter(
                    "https://ui-avatars.com/api/?background=random&name=${name.replace(" ", "+")}&size=32",
                ),
                contentDescription = null,
                contentScale = ContentScale.Crop,
            )
            Spacer(modifier = Modifier.size(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = name,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 18.sp,
                    letterSpacing = 0.sp,
                    textAlign = TextAlign.Center,
                )
                Text(
                    text = summary,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                    lineHeight = 18.sp,
                    letterSpacing = 0.sp,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                )
            }
            Text(
                text = time,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                lineHeight = 18.sp,
                letterSpacing = 0.sp,
                textAlign = TextAlign.Center,
            )
        }
        content(
            PaddingValues(start = 14.dp, top = 0.dp, end = 14.dp, bottom = 14.dp)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CheckboxComponent(
    isChecked: Boolean,
    label: String,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        CompositionLocalProvider(LocalMinimumInteractiveComponentEnforcement provides false) {
            Checkbox(
                checked = isChecked,
                enabled = isEnabled,
                onCheckedChange = onCheckedChange,
                colors = CheckboxDefaults.colors(
                    disabledUncheckedColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                )
            )
        }
        Text(
            text = label,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            lineHeight = 18.sp,
            letterSpacing = 0.sp,
            textAlign = TextAlign.Start,
            color = if (isEnabled) MaterialTheme.colorScheme.onSurface else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
            modifier = Modifier.weight(1f).padding(start = 10.dp),
        )
    }
}

@Composable
private fun GalleryComponent(
    urls: Collection<String>,
    modifier: Modifier = Modifier,
    imageSize: Dp = 80.dp,
) {
    Box(modifier = modifier) {
        LazyHorizontalGrid(
            modifier = Modifier.height(imageSize),
            rows = GridCells.Fixed(1),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            urls.forEach { url ->
                item {
                    Image(
                        modifier = Modifier.size(imageSize).clip(CardDefaults.shape),
                        painter = rememberAsyncImagePainter(url),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                    )
                }
            }
        }
        Spacer(
            modifier = Modifier
                .width(imageSize / 3)
                .height(imageSize)
                .background(
                    brush = Brush.horizontalGradient(
                        startX = 0f,
                        endX = 80f,
                        colors = listOf(MaterialTheme.colorScheme.surface, Color.Transparent)
                    )
                )
        )
        Spacer(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .width(imageSize / 3)
                .height(imageSize)
                .background(
                    brush = Brush.horizontalGradient(
                        startX = 0f,
                        endX = 80f,
                        colors = listOf(Color.Transparent, MaterialTheme.colorScheme.surface)
                    )
                )
        )
    }
}

@Composable
private fun CommentComponent(
    title: String,
    text: String,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Text(
            text = title,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            lineHeight = 18.sp,
            letterSpacing = 0.sp,
            textAlign = TextAlign.Start,
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = text,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            lineHeight = 18.sp,
            letterSpacing = 0.sp,
            textAlign = TextAlign.Start,
        )
    }
}
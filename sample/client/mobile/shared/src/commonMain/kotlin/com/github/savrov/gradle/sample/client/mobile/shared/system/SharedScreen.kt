package com.github.savrov.gradle.sample.client.mobile.shared.system

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arkivanov.decompose.ComponentContext
import org.koin.core.Koin
import org.koin.core.parameter.parametersOf

@Composable
fun SharedScreen(
    koin: Koin,
    componentContext: ComponentContext,
) {
    val navigation: SharedNavigationDelegate = koin.get<SharedNavigationDelegate>(
        parameters = { parametersOf(componentContext) }
    )
    SharedScreen()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun SharedScreen(
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {},
        content = {
            FeedComponent(modifier = Modifier.padding(it))
        },
    )
}

@Composable
private fun FeedComponent(
    modifier: Modifier = Modifier,
) {
    LazyColumn(modifier = modifier) {
        item {
            DateComponent("05 Oct 2021")
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
    time: String,
    content: @Composable () -> Unit,
) {
    Card {
        Row {
            Image()
            Column {
                Text()
                Text()
            }
            Text(
                text = time,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                lineHeight = 18.sp,
                letterSpacing = 0.sp,
                textAlign = TextAlign.Center,
            )
        }
        content()
    }
}
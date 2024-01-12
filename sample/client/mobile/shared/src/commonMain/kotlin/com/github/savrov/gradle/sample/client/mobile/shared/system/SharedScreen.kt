package com.github.savrov.gradle.sample.client.mobile.shared.system

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
    SharedScreen {
        navigation.content(modifier = it)
    }
}

@Composable
private fun SharedScreen(
    modifier: Modifier = Modifier,
    children: @Composable (Modifier) -> Unit = {},
) {
    Scaffold(
        modifier = modifier,
        content = {
            children(Modifier.padding(it))
        },
    )
}

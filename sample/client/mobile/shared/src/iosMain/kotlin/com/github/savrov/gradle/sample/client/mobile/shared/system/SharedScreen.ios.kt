package com.github.savrov.gradle.sample.client.mobile.shared.system

import androidx.compose.ui.window.ComposeUIViewController

@Suppress("unused")
fun viewController() = ComposeUIViewController {
    SampleTheme(useDarkTheme = true) {
        SharedScreen()
    }
}

@Suppress("unused")
fun koin() {}
package com.github.savrov.gradle.sample.client.mobile.shared.system

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity


fun ComponentActivity.viewController() = setContent {
    SampleTheme(useDarkTheme = true) {
        SharedScreen()
    }
}

@Suppress("unused")
fun AppCompatActivity.koin() {}
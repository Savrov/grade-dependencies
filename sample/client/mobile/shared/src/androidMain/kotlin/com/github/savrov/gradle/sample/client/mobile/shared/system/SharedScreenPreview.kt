package com.github.savrov.gradle.sample.client.mobile.shared.system

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
@Preview
internal fun ComposeComponentPreview() {
    SampleTheme(useDarkTheme = true) {
        SharedScreen()
    }
}
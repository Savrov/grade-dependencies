package com.github.savrov.gradle.sample.client.mobile.shared.system

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.github.savrov.gradle.sample.client.mobile.shared.model.EntryComponentState
import com.github.savrov.gradle.sample.client.mobile.shared.model.EntryLabel
import com.github.savrov.gradle.sample.client.mobile.shared.model.EntryState

@Composable
@Preview
internal fun EntryComponentPreview() {
    MaterialTheme {
        EntryComponent(
            state = EntryState(
                isBasicAuthEnabled = true,
            ),
            label = null,
        )
    }
}
package com.github.savrov.gradle.sample.client.mobile.shared.model

internal sealed interface EntryAction {
    data object DisableBasicAuth : EntryAction
}
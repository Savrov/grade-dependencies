package com.github.savrov.gradle.sample.client.mobile.shared.model

sealed class EntryLabel {
    data class UpdateBottomSheetState(val isExpanded: Boolean) : EntryLabel()
}
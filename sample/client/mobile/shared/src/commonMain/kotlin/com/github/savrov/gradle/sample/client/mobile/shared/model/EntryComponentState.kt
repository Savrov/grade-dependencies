package com.github.savrov.gradle.sample.client.mobile.shared.model

import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize

@Parcelize
data class EntryComponentState(
    val username: String = "",
    val password: String = "",
    val isBasicAuthEnabled: Boolean = true,
): Parcelable
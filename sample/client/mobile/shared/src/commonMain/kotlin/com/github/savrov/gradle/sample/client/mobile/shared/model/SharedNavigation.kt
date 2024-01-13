package com.github.savrov.gradle.sample.client.mobile.shared.model

import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize

@Parcelize
sealed interface SharedNavigation : Parcelable {
    data object Entry : SharedNavigation
    data object Projects : SharedNavigation

    data object BuildConfig : SharedNavigation
}
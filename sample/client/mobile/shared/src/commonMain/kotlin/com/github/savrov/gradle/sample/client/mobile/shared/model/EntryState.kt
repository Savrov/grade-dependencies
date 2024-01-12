package com.github.savrov.gradle.sample.client.mobile.shared.model

import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize

//@Parcelize
//internal sealed interface EntryState : Parcelable {
//
//    data object Initial : EntryState
//
//    data class Value(
//        val isSignedIn: Boolean,
//        val isBasicAuthEnabled: Boolean,
//        val errorMessage: String?,
//    ) : EntryState
//
//}

@Parcelize
internal data class EntryState(
    val isBasicAuthEnabled: Boolean = true,
    val errorMessage: String? = null,
) : Parcelable
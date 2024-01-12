package com.github.savrov.gradle.sample.client.mobile.shared.model

import com.github.savrov.core.library.cleanarch.model.error.Error

internal sealed interface EntryMessage {
    data object BasicAuthDisabled : EntryMessage
    data object SignInSucceeded : EntryMessage
    data class SignInFailed(val errors: Collection<Error>) : EntryMessage
}
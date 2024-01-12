package com.github.savrov.gradle.sample.client.mobile.shared.presentation

import com.arkivanov.mvikotlin.core.store.Reducer
import com.github.savrov.core.library.cleanarch.model.error.OperationError
import com.github.savrov.gradle.sample.client.mobile.shared.model.EntryMessage
import com.github.savrov.gradle.sample.client.mobile.shared.model.EntryState

internal class EntryReducer : Reducer<EntryState, EntryMessage> {
    override fun EntryState.reduce(msg: EntryMessage): EntryState {
        return when (msg) {
            EntryMessage.BasicAuthDisabled -> copy(isBasicAuthEnabled = false, errorMessage = "Basic auth is disabled")
            is EntryMessage.SignInFailed -> {
                val errorMessages = msg.errors.map {
                    when (val error = it as OperationError) {
                        OperationError.Aborted -> "Operation aborted"
                        is OperationError.Conflict -> "Conflict (${error.field}): ${error.title}. ${error.description}"
                        OperationError.Forbidden -> "Operation forbidden"
                        is OperationError.Internal -> "Internal error: ${error.ex.message}"
                        is OperationError.Invalid -> "Invalid (${error.field}): ${error.title}"
                        OperationError.NoData -> "No data"
                        is OperationError.NotFound -> "Not found: ${error.title}. ${error.description}"
                        OperationError.Unauthorized -> "Unauthorized"
                    }
                }
                val errorMessage = errorMessages.joinToString(separator = "\n") { it }
                copy(errorMessage = errorMessage)
            }

            EntryMessage.SignInSucceeded -> copy(errorMessage = null)
        }
    }

}
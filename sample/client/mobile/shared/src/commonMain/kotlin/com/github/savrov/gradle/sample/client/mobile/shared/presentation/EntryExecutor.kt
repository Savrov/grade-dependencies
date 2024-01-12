package com.github.savrov.gradle.sample.client.mobile.shared.presentation

import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.github.savrov.core.library.cleanarch.model.result.usecase.UseCaseResult
import com.github.savrov.gradle.sample.client.mobile.shared.domain.SharedNavigationUseCase
import com.github.savrov.gradle.sample.client.mobile.shared.domain.SignInWithGithubOAuthUseCase
import com.github.savrov.gradle.sample.client.mobile.shared.domain.UpsertTokensUseCase
import com.github.savrov.gradle.sample.client.mobile.shared.model.EntryAction
import com.github.savrov.gradle.sample.client.mobile.shared.model.EntryIntent
import com.github.savrov.gradle.sample.client.mobile.shared.model.EntryLabel
import com.github.savrov.gradle.sample.client.mobile.shared.model.EntryMessage
import com.github.savrov.gradle.sample.client.mobile.shared.model.EntryState
import kotlinx.coroutines.launch

internal class EntryExecutor(
    private val signInWithGithubOAuthUseCase: SignInWithGithubOAuthUseCase,
    private val upsertTokensUseCase: UpsertTokensUseCase,
    private val openProjectsUseCase: SharedNavigationUseCase.OpenProjects,
) : CoroutineExecutor<EntryIntent, EntryAction, EntryState, EntryMessage, EntryLabel>() {

    override fun executeIntent(intent: EntryIntent, getState: () -> EntryState) {
        when (intent) {
            is EntryIntent.SignInWithBasicAuth -> TODO()

            EntryIntent.SignInWithGithub -> scope.launch {
                publish(EntryLabel.UpdateBottomSheetState(isExpanded = false))
                when (val token = signInWithGithubOAuthUseCase()) {
                    is UseCaseResult.Failure -> {
                        dispatch(EntryMessage.SignInFailed(token.errors))
                        publish(EntryLabel.UpdateBottomSheetState(isExpanded = true))
                    }

                    is UseCaseResult.Running -> Unit
                    is UseCaseResult.Success -> {
                        dispatch(EntryMessage.SignInSucceeded)
                        publish(EntryLabel.UpdateBottomSheetState(isExpanded = false))
                        upsertTokensUseCase(token.data)
                        openProjectsUseCase()
                    }
                }
            }
        }
    }

    override fun executeAction(action: EntryAction, getState: () -> EntryState) {
        when(action) {
            EntryAction.DisableBasicAuth -> scope.launch {
                dispatch(EntryMessage.BasicAuthDisabled)
            }
        }
    }
}
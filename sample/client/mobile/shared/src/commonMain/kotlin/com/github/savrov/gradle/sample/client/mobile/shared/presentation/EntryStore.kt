package com.github.savrov.gradle.sample.client.mobile.shared.presentation

import com.arkivanov.essenty.statekeeper.StateKeeper
import com.arkivanov.mvikotlin.core.store.SimpleBootstrapper
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.github.savrov.gradle.sample.client.mobile.shared.domain.SharedNavigationUseCase
import com.github.savrov.gradle.sample.client.mobile.shared.domain.SignInWithGithubOAuthUseCase
import com.github.savrov.gradle.sample.client.mobile.shared.domain.UpsertTokensUseCase
import com.github.savrov.gradle.sample.client.mobile.shared.model.EntryAction
import com.github.savrov.gradle.sample.client.mobile.shared.model.EntryIntent
import com.github.savrov.gradle.sample.client.mobile.shared.model.EntryLabel
import com.github.savrov.gradle.sample.client.mobile.shared.model.EntryState

internal interface EntryStore : Store<EntryIntent, EntryState, EntryLabel> {

    class Factory(
        private val storeFactory: StoreFactory,
        private val signInWithGithubOAuthUseCase: SignInWithGithubOAuthUseCase,
        private val upsertTokensUseCase: UpsertTokensUseCase,
        private val openProjectsUseCase: SharedNavigationUseCase.OpenProjects,
    ) {

        private companion object {
            private const val STORE_NAME = "EntryStore"
        }

        fun create(stateKeeper: StateKeeper): EntryStore =
            object : EntryStore, Store<EntryIntent, EntryState, EntryLabel> by storeFactory.create(
                name = STORE_NAME,
                initialState = EntryState(),
                bootstrapper = SimpleBootstrapper(EntryAction.DisableBasicAuth),
                reducer = EntryReducer(),
                executorFactory = {
                    EntryExecutor(
                        signInWithGithubOAuthUseCase = signInWithGithubOAuthUseCase,
                        upsertTokensUseCase = upsertTokensUseCase,
                        openProjectsUseCase = openProjectsUseCase,
                    )
                },
            ) {}.also {
                stateKeeper.register(key = STORE_NAME) {
                    it.state
                }
            }
    }

}
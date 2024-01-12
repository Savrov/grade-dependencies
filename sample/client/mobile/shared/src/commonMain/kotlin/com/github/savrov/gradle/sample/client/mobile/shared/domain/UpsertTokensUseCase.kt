package com.github.savrov.gradle.sample.client.mobile.shared.domain

import com.github.savrov.core.library.cleanarch.domain.usecase.common.SuspendUseCase
import org.publicvalue.multiplatform.oidc.ExperimentalOpenIdConnect
import org.publicvalue.multiplatform.oidc.tokenstore.SettingsTokenStore
import org.publicvalue.multiplatform.oidc.types.remote.AccessTokenResponse

internal class UpsertTokensUseCase @OptIn(ExperimentalOpenIdConnect::class) constructor(
    private val settingsTokenStore: SettingsTokenStore,
): SuspendUseCase<AccessTokenResponse, Unit> {
    @OptIn(ExperimentalOpenIdConnect::class)
    override suspend fun invoke(input: AccessTokenResponse) {
        with(settingsTokenStore) {
            removeAccessToken()
            removeRefreshToken()
            removeIdToken()
            saveTokens(input.access_token, input.refresh_token, input.id_token)
        }
    }
}
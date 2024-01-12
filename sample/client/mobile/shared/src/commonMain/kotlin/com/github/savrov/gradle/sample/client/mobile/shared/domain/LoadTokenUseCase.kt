package com.github.savrov.gradle.sample.client.mobile.shared.domain

import com.github.savrov.core.library.cleanarch.domain.usecase.common.SuspendUnitResultUseCase
import com.github.savrov.core.library.cleanarch.model.error.OperationError
import com.github.savrov.core.library.cleanarch.model.result.usecase.UseCaseMeta
import com.github.savrov.core.library.cleanarch.model.result.usecase.UseCaseResult
import org.publicvalue.multiplatform.oidc.ExperimentalOpenIdConnect
import org.publicvalue.multiplatform.oidc.tokenstore.SettingsTokenStore

internal class LoadTokenUseCase @OptIn(ExperimentalOpenIdConnect::class) constructor(
    private val settingsTokenStore: SettingsTokenStore,
    override val meta: UseCaseMeta,
) : SuspendUnitResultUseCase<String> {
    @OptIn(ExperimentalOpenIdConnect::class)
    override suspend fun invoke(): UseCaseResult<String> {
        return when (val token = settingsTokenStore.getAccessToken()) {
            null -> UseCaseResult.Failure(null, listOf(OperationError.NotFound("Access token not found", "Token was not found in local data store")), meta)
            else -> UseCaseResult.Success(token)
        }
    }
}
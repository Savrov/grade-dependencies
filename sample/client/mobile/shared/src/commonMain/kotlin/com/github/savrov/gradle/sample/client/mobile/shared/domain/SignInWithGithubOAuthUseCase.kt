package com.github.savrov.gradle.sample.client.mobile.shared.domain

import com.github.savrov.core.library.cleanarch.domain.usecase.common.SuspendUnitResultUseCase
import com.github.savrov.core.library.cleanarch.domain.usecase.common.SuspendUnitUseCase
import com.github.savrov.core.library.cleanarch.model.error.OperationError
import com.github.savrov.core.library.cleanarch.model.result.usecase.UseCaseMeta
import com.github.savrov.core.library.cleanarch.model.result.usecase.UseCaseResult
import org.publicvalue.multiplatform.oidc.OpenIdConnectClient
import org.publicvalue.multiplatform.oidc.OpenIdConnectException
import org.publicvalue.multiplatform.oidc.appsupport.CodeAuthFlowFactory
import org.publicvalue.multiplatform.oidc.types.remote.AccessTokenResponse

internal class SignInWithGithubOAuthUseCase(
    private val codeAuthFlowFactory: CodeAuthFlowFactory,
    override val meta: UseCaseMeta,
) : SuspendUnitResultUseCase<AccessTokenResponse> {
    override suspend fun invoke(): UseCaseResult<AccessTokenResponse> {
        val client = OpenIdConnectClient {
            clientId = "03725fe4614ed4961f7a"
            clientSecret = "ac539a9b532b53447eb792a557dd67514283e806"
            redirectUri = "com.github.savrov.gradle.sample://callback"
            scope = "repo, user"
            endpoints {
                tokenEndpoint = "https://github.com/login/oauth/access_token"
                authorizationEndpoint = "https://github.com/login/oauth/authorize"
                userInfoEndpoint = null
                endSessionEndpoint = null
            }
        }
        return try {
            val token = codeAuthFlowFactory.createAuthFlow(client).getAccessToken()
            UseCaseResult.Success(token)
        } catch (e: OpenIdConnectException) {
            val error = when (e) {
                is OpenIdConnectException.AuthenticationFailure -> OperationError.Unauthorized
                is OpenIdConnectException.InvalidUrl -> OperationError.Invalid(e.message, "URL", e.url)
                is OpenIdConnectException.TechnicalFailure -> OperationError.Internal(e)
                is OpenIdConnectException.UnsuccessfulTokenRequest -> OperationError.Unauthorized
                is OpenIdConnectException.UnsupportedFormat -> OperationError.Forbidden
            }
            UseCaseResult.Failure(null, listOf(error), meta)
        }
    }
}
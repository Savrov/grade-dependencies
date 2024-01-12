package com.github.savrov.gradle.sample.client.mobile.shared.di

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import org.koin.dsl.module
import org.publicvalue.multiplatform.oidc.ExperimentalOpenIdConnect
import org.publicvalue.multiplatform.oidc.appsupport.CodeAuthFlowFactory
import org.publicvalue.multiplatform.oidc.appsupport.IosCodeAuthFlowFactory
import org.publicvalue.multiplatform.oidc.tokenstore.IosKeychainTokenStore
import org.publicvalue.multiplatform.oidc.tokenstore.SettingsTokenStore

@OptIn(ExperimentalOpenIdConnect::class)
val iosModule = module {
    single<CodeAuthFlowFactory> { IosCodeAuthFlowFactory() }
    single<SettingsTokenStore> { IosKeychainTokenStore() }
}
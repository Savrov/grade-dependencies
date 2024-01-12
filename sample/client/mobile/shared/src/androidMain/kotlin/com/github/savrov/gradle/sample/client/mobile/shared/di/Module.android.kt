package com.github.savrov.gradle.sample.client.mobile.shared.di

import androidx.appcompat.app.AppCompatActivity
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.createdAtStart
import org.koin.core.module.dsl.withOptions
import org.koin.dsl.module
import org.publicvalue.multiplatform.oidc.ExperimentalOpenIdConnect
import org.publicvalue.multiplatform.oidc.appsupport.AndroidCodeAuthFlowFactory
import org.publicvalue.multiplatform.oidc.appsupport.CodeAuthFlowFactory
import org.publicvalue.multiplatform.oidc.tokenstore.AndroidSettingsTokenStore
import org.publicvalue.multiplatform.oidc.tokenstore.SettingsTokenStore

@OptIn(ExperimentalOpenIdConnect::class)
fun androidModule(activity: AppCompatActivity) = module {
    single<CodeAuthFlowFactory> { AndroidCodeAuthFlowFactory(activity = activity) } withOptions {
        createdAtStart()
    }
    single<SettingsTokenStore> { AndroidSettingsTokenStore(androidContext()) }
}
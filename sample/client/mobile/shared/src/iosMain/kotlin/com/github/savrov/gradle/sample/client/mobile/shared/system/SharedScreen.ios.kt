package com.github.savrov.gradle.sample.client.mobile.shared.system

import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.window.ComposeUIViewController
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.github.savrov.gradle.sample.client.mobile.shared.di.entryModule
import com.github.savrov.gradle.sample.client.mobile.shared.di.iosModule
import com.github.savrov.gradle.sample.client.mobile.shared.di.sharedModule
import com.github.savrov.gradle.sample.client.mobile.shared.di.coreModule
import org.koin.core.Koin
import org.koin.core.component.KoinComponent
import org.koin.core.context.startKoin
import org.koin.dsl.koinApplication
import org.koin.mp.KoinPlatformTools

@Suppress("unused")
fun viewController() = ComposeUIViewController {
    MaterialTheme {
        val koin = KoinPlatformTools.defaultContext().get()
        val componentContext = DefaultComponentContext(
            lifecycle = LifecycleRegistry(),
            stateKeeper = koin.get(),
            instanceKeeper = koin.get(),
        )
        SharedScreen(
            koin = KoinPlatformTools.defaultContext().get(),
            componentContext = componentContext
        )
    }
}

@Suppress("unused")
fun koin() {
    startKoin {
        modules(coreModule + sharedModule + entryModule + iosModule)
    }
}
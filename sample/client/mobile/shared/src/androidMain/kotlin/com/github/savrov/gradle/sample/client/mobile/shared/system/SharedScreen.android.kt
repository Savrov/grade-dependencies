package com.github.savrov.gradle.sample.client.mobile.shared.system

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.MaterialTheme
import com.arkivanov.decompose.defaultComponentContext
import com.github.savrov.gradle.sample.client.mobile.shared.di.androidModule
import com.github.savrov.gradle.sample.client.mobile.shared.di.entryModule
import com.github.savrov.gradle.sample.client.mobile.shared.di.sharedModule
import com.github.savrov.gradle.sample.client.mobile.shared.di.coreModule
import org.koin.android.ext.koin.androidContext
import org.koin.compose.getKoin
import org.koin.core.context.startKoin


fun ComponentActivity.viewController() = setContent {
    MaterialTheme {
        val componentContext = defaultComponentContext()
        SharedScreen(
            koin = getKoin(),
            componentContext = componentContext
        )
    }
}

@Suppress("unused")
fun AppCompatActivity.koin() {
    startKoin {
        androidContext(this@koin)
        modules(coreModule + sharedModule + entryModule + androidModule(this@koin))
        createEagerInstances() // required for AndroidCodeAuthFlowFactory in androidModule
    }
}
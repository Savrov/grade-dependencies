package com.github.savrov.gradle.sample.client.mobile.shared.di

import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import com.arkivanov.essenty.instancekeeper.InstanceKeeperDispatcher
import com.arkivanov.essenty.statekeeper.StateKeeper
import com.arkivanov.essenty.statekeeper.StateKeeperDispatcher
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.logging.logger.DefaultLogFormatter
import com.arkivanov.mvikotlin.logging.store.LoggingStoreFactory
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import com.github.savrov.core.library.cleanarch.model.result.usecase.UseCaseMeta
import com.github.savrov.gradle.sample.client.mobile.shared.device.DefaultActiveChildController
import com.github.savrov.gradle.sample.client.mobile.shared.device.NavigationController
import com.github.savrov.gradle.sample.client.mobile.shared.domain.LoadTokenUseCase
import com.github.savrov.gradle.sample.client.mobile.shared.domain.SharedNavigationController
import com.github.savrov.gradle.sample.client.mobile.shared.domain.SharedNavigationUseCase
import com.github.savrov.gradle.sample.client.mobile.shared.domain.SignInWithGithubOAuthUseCase
import com.github.savrov.gradle.sample.client.mobile.shared.domain.UpsertTokensUseCase
import com.github.savrov.gradle.sample.client.mobile.shared.model.SharedNavigation
import com.github.savrov.gradle.sample.client.mobile.shared.presentation.EntryStore
import com.github.savrov.gradle.sample.client.mobile.shared.system.SharedNavigationDelegate
import org.koin.core.qualifier.named
import org.koin.dsl.bind
import org.koin.dsl.binds
import org.koin.dsl.module
import org.publicvalue.multiplatform.oidc.ExperimentalOpenIdConnect

@OptIn(ExperimentalOpenIdConnect::class)
val coreModule = module {

    single { UseCaseMeta("sample", "client", "1") } // TODO use BuildConfig

    single<InstanceKeeper> { InstanceKeeperDispatcher() }

    single<StateKeeper> { StateKeeperDispatcher() }

    factory<StoreFactory> {
        LoggingStoreFactory(
            delegate = DefaultStoreFactory(),
            logFormatter = DefaultLogFormatter(Int.MAX_VALUE)
        )
    }

    single {
        NavigationController(
            sharedNavigation = get(named<SharedNavigation>()),
        )
    } binds arrayOf(
        SharedNavigationController::class,
    )

    factory {
        SignInWithGithubOAuthUseCase(
            codeAuthFlowFactory = get(),
            meta = get(),
        )
    }

    factory {
        UpsertTokensUseCase(
            settingsTokenStore = get(),
        )
    }

    factory {
        LoadTokenUseCase(
            settingsTokenStore = get(),
            meta = get(),
        )
    }

}

val sharedModule = module {

    single(named<SharedNavigation>()) { StackNavigation<SharedNavigation>() }

    single(named<SharedNavigation>()) { DefaultActiveChildController<SharedNavigation>() }

    single {
        SharedNavigationDelegate(
            componentContext = it.get(),
            stackNavigation = get(named<SharedNavigation>()),
            activeChildController = get(named<SharedNavigation>()),
        )
    }

    factory {
        SharedNavigationUseCase.OpenEntry(
            sharedNavigationController = get(named<SharedNavigation>()),
        )
    }

    factory {
        SharedNavigationUseCase.OpenProjects(
            sharedNavigationController = get(),
        )
    }
}

val entryModule = module {

    factory {
        get<InstanceKeeper>().getStore {
            EntryStore.Factory(
                storeFactory = get(),
                signInWithGithubOAuthUseCase = get(),
                upsertTokensUseCase = get(),
                openProjectsUseCase = get(),
            ).create(
                stateKeeper = get()
            )
        }
    }
}


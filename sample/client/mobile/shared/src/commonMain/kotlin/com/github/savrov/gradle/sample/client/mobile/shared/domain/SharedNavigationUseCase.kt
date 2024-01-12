package com.github.savrov.gradle.sample.client.mobile.shared.domain

import com.github.savrov.core.library.cleanarch.domain.usecase.common.SynchronousUnitUseCase

internal object SharedNavigationUseCase {

    class OpenEntry(
        private val sharedNavigationController: SharedNavigationController,
    ): SynchronousUnitUseCase<Unit> {
        override fun invoke() {
            sharedNavigationController.openEntry()
        }
    }

    class OpenProjects(
        private val sharedNavigationController: SharedNavigationController,
    ): SynchronousUnitUseCase<Unit> {
        override fun invoke() {
            sharedNavigationController.openProjects()
        }
    }

    class OpenError(
        private val sharedNavigationController: SharedNavigationController,
    ): SynchronousUnitUseCase<Unit> {
        override fun invoke() {
            // TODO: implement
        }
    }

}
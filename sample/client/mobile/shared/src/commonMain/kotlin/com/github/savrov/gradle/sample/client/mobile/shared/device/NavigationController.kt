package com.github.savrov.gradle.sample.client.mobile.shared.device

import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.bringToFront
import com.github.savrov.gradle.sample.client.mobile.shared.domain.SharedNavigationController
import com.github.savrov.gradle.sample.client.mobile.shared.model.SharedNavigation

internal class NavigationController(
    private val sharedNavigation: StackNavigation<SharedNavigation>
): SharedNavigationController {
    override fun openEntry() {
        sharedNavigation.bringToFront(SharedNavigation.Entry)
    }

    override fun openProjects() {
        sharedNavigation.bringToFront(SharedNavigation.Projects)
    }
}
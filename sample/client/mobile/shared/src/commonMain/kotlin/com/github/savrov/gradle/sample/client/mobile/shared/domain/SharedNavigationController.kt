package com.github.savrov.gradle.sample.client.mobile.shared.domain

import com.arkivanov.decompose.ComponentContext

interface SharedNavigationController {
    fun openEntry()
    fun openProjects()

    sealed interface Child {
        data class Entry(val context: ComponentContext) : Child
        data class Projects(val context: ComponentContext) : Child
    }
}
package com.github.savrov.gradle.sample.client.mobile.shared.domain

import com.arkivanov.decompose.ComponentContext

interface SharedNavigationController {
    fun openEntry()
    fun openProjects()

    sealed interface Child {
        data class Entry(val context: ComponentContext) : Child
        data class Projects(val context: ComponentContext) : Child

        data class KtorClientComponent(val context: ComponentContext) : Child
        data class UuidComponent(val context: ComponentContext) : Child
        data class SqlDelightComponent(val context: ComponentContext) : Child
        data class SupabaseComponent(val context: ComponentContext) : Child
        data class OidcComponent(val context: ComponentContext) : Child
        data class ComposeComponent(val context: ComponentContext) : Child
        data class BuildConfigComponent(val context: ComponentContext) : Child
    }
}
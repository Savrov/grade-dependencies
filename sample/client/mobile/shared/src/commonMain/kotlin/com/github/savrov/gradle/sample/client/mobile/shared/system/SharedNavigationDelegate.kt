package com.github.savrov.gradle.sample.client.mobile.shared.system

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.Children
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.value.Value
import com.github.savrov.gradle.sample.client.mobile.shared.device.DefaultActiveChildController
import com.github.savrov.gradle.sample.client.mobile.shared.domain.SharedNavigationController
import com.github.savrov.gradle.sample.client.mobile.shared.model.SharedNavigation
import org.koin.core.component.KoinComponent

class SharedNavigationDelegate(
    activeChildController: DefaultActiveChildController<SharedNavigation>,
    stackNavigation: StackNavigation<SharedNavigation>,
    componentContext: ComponentContext,
) : NavigationDelegate<SharedNavigationController.Child>, KoinComponent, ComponentContext by componentContext {
    override val childStack: Value<ChildStack<*, SharedNavigationController.Child>> = childStack(
        source = stackNavigation,
        initialConfiguration = SharedNavigation.Entry,
        key = "SharedNavigationDelegate",
        handleBackButton = false,
        childFactory = { configuration: SharedNavigation, componentContext: ComponentContext ->
            when (configuration) {
                SharedNavigation.Entry -> SharedNavigationController.Child.Entry(componentContext)
                SharedNavigation.Projects -> SharedNavigationController.Child.Projects(componentContext)
            }
        }
    )

    override val childStackObserver: (ChildStack<*, SharedNavigationController.Child>) -> Unit = { stack ->
        val navigation = when(stack.active.instance) {
            is SharedNavigationController.Child.Entry -> SharedNavigation.Entry
            is SharedNavigationController.Child.Projects -> SharedNavigation.Projects
        }
        activeChildController.update(navigation)
    }

    @Composable
    override fun content(modifier: Modifier) {
        Children(
            stack = childStack,
            modifier = modifier,
        ) {
            when(it.instance) {
                is SharedNavigationController.Child.Entry -> EntryComponent(getKoin())
                is SharedNavigationController.Child.Projects -> ProjectsComponent(getKoin())
            }
        }
    }

}
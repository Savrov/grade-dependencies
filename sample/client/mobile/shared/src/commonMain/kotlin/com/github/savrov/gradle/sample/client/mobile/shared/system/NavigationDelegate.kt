package com.github.savrov.gradle.sample.client.mobile.shared.system

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value

interface NavigationDelegate<Child: Any> {

    val childStack: Value<ChildStack<*, Child>>

    val childStackObserver: (ChildStack<*, Child>) -> Unit

    @Composable
    fun content(modifier: Modifier)

}
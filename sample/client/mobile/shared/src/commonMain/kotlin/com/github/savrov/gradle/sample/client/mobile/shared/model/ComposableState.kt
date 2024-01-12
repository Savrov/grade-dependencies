package com.github.savrov.gradle.sample.client.mobile.shared.model

sealed class ComposableState<out T: Any> {

    data object Initial : ComposableState<Nothing>()
    data object Error : ComposableState<Nothing>()
    data object Loading: ComposableState<Nothing>()
    data class Success<out T: Any>(val data: T) : ComposableState<T>()
}
package com.github.savrov.gradle.sample.client.mobile.shared.device

import com.github.savrov.gradle.sample.client.mobile.shared.domain.ActiveChildController
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

class DefaultActiveChildController<Navigation> : ActiveChildController<Navigation> {

    private var child = MutableSharedFlow<Navigation>(replay = 1)

    override fun observe(): Flow<Navigation> {
        return child
    }

    fun update(value: Navigation) {
        child.tryEmit(value)
    }
}
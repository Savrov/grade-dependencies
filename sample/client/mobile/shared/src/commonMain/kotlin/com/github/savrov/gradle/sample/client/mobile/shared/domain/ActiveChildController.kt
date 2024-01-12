package com.github.savrov.gradle.sample.client.mobile.shared.domain

import kotlinx.coroutines.flow.Flow

interface ActiveChildController<T> {

    fun observe(): Flow<T>

}
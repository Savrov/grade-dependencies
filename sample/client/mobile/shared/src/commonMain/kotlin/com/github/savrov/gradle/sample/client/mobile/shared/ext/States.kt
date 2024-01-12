package com.github.savrov.gradle.sample.client.mobile.shared.ext

import com.github.savrov.gradle.sample.client.mobile.shared.model.EntryComponentState
import com.github.savrov.gradle.sample.client.mobile.shared.model.EntryState

//internal fun EntryState.toState(): EntryComponentState {
//    return when(this) {
//        is EntryState.Initial -> EntryComponentState()
//        is EntryState.Value -> EntryComponentState(
//            username = username,
//            password = password,
//            isBasicAuthEnabled = isBasicAuthEnabled
//        )
//    }
//}
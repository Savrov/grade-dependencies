package com.github.savrov.gradle.sample.client.mobile.shared.model

internal sealed interface EntryIntent {
    data class SignInWithBasicAuth(val username: String, val password: String) : EntryIntent
    data object SignInWithGithub : EntryIntent
}
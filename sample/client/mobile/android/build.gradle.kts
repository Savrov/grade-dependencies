@file:Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.jetbrains.compose)
}

dependencies {
    implementation(projects.sample.client.mobile.shared)
    implementation(libs.android.activity.compose)
    implementation(libs.android.appcompat)
    implementation(libs.android.core)
}

android {
    namespace = "com.github.savrov.gradle.sample.client.mobile.android"
    compileSdk = libs.versions.android.sdk.compile.get().toInt()

    sourceSets["main"].manifest.srcFile("src/main/AndroidManifest.xml")

    defaultConfig {
        applicationId = namespace
        minSdk = libs.versions.android.sdk.min.get().toInt()
        versionCode = 1
        versionName = version.toString()
        manifestPlaceholders["oidcRedirectScheme"] = "com.github.savrov.gradle.sample"
    }
    kotlin {
        jvmToolchain(libs.versions.java.major.get().toInt())
    }
}
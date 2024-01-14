@file:Suppress("DSL_SCOPE_VIOLATION")
group = "com.github.savrov.gradle.sample.client.mobile"

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.kotlin.cocoapods)
    alias(libs.plugins.kotlin.parcelize)
    alias(libs.plugins.jetbrains.compose)
    // If i enable this, i get the following error: org.gradle.api.internal.plugins.PluginApplicationException: Failed to apply plugin 'org.jetbrains.compose'.
    // alias(libs.plugins.buildConfig)
}

kotlin {
    androidTarget()
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    cocoapods {
        version = project.version.toString()
        summary = "Shared Application Module for Sample Mobile Clients"
        homepage = "https://github.com"
        ios.deploymentTarget = libs.versions.ios.deploymentTarget.get()
        podfile = project.file("../ios/Podfile")
        framework {
            baseName = "shared"
            linkerOpts.addAll(listOf("-ld64"))
            isStatic = true
            freeCompilerArgs += arrayOf("-linker-options")
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(compose.ui)
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material3)
                implementation(compose.materialIconsExtended)
                @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
                implementation(compose.components.resources)
                // Workaround as per https://youtrack.jetbrains.com/issue/KT-41821
                implementation("org.jetbrains.kotlinx:atomicfu:0.17.3")

                implementation(libs.bundles.coil)
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(libs.android.activity.compose)
                implementation(libs.android.appcompat)
                implementation(compose.uiTooling)
                implementation(compose.preview)
            }
        }
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
        }
    }
}

android {
    namespace = "com.github.savrov.gradle.sample.client.mobile.shared"
    compileSdk = libs.versions.android.sdk.compile.get().toInt()
    defaultConfig {
        minSdk = libs.versions.android.sdk.min.get().toInt()
        manifestPlaceholders["oidcRedirectScheme"] = "com.github.savrov.gradle.sample"
    }
    val javaVersion = libs.versions.java.major.get().toInt()
    compileOptions {
        sourceCompatibility = JavaVersion.toVersion(javaVersion)
        targetCompatibility = JavaVersion.toVersion(javaVersion)
    }
    kotlin {
        jvmToolchain(javaVersion)
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.kotlin.compiler.extension.get()
    }
}
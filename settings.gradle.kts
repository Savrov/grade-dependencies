pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
}

rootProject.name = "gradle-versions"
include("android")
include("arkivanov")
include("jetbrains")
include("koin")
include("sqldelight")
include("supabase")

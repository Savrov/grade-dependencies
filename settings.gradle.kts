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
include("other")
include("sqldelight")
include("supabase")
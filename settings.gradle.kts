pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
}

rootProject.name = "gradle-versions"
include("android")
include("arkivanov")
include("google")
include("jetbrains")
include("koin")
include("kotest")
include("ktor")
include("other")
include("sqldelight")
include("supabase")
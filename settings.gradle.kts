rootProject.name = extra["name"] as String

pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
}

include("android")
include("arkivanov")
include("google")
include("jetbrains")
include("koin")
include("kotest")
include("ktor")
include("other")
include("savrov")
include("sqldelight")
include("supabase")
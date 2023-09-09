pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
}

rootProject.name = "gradle-versions"
include("android")
include("arkivanov")
include("supabase")
include("jetbrains")

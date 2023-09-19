plugins {
    id("org.gradle.version-catalog")
    id("org.gradle.maven-publish")
}

publishing {
    publications {
        create<MavenPublication>("versionCatalog") {
            from(components["versionCatalog"])
            groupId = project.parent?.group?.toString()
            artifactId = "versions-android"
        }
    }
}

catalog {
    versionCatalog {
        version("version-android-gradle-plugin", "7.4.2")
        version("version-android-min-sdk", "24")
        version("version-android-compile-sdk", "33")
        version("version-androidx-activity", "1.7.1")
        version("version-androidx-appcompat", "1.6.1")
        version("version-androidx-core", "1.10.0")

        plugin("android-application", "com.android.application").versionRef("version-android-gradle-plugin")
        plugin("android-library", "com.android.library").versionRef("version-android-gradle-plugin")

        library("androidx-activity", "androidx.activity", "activity-compose").versionRef("version-androidx-activity")
        library("androidx-appcompat", "androidx.appcompat", "appcompat").versionRef("version-androidx-appcompat")
        library("androidx-core", "androidx.core", "core-ktx").versionRef("version-androidx-core")
    }
}
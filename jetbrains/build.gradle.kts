plugins {
    id("org.gradle.version-catalog")
    id("org.gradle.maven-publish")
}

publishing {
    publications {
        create<MavenPublication>("jetbrainsVersionCatalog") {
            from(components["versionCatalog"])
            groupId = project.parent?.group?.toString()
            artifactId = project.parent?.name + "-jetbrains"
        }
    }
}
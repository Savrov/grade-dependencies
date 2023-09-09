plugins {
    id("org.gradle.version-catalog")
    id("org.gradle.maven-publish")
}

publishing {
    publications {
        create<MavenPublication>("versionCatalog") {
            from(components["versionCatalog"])
            groupId = project.parent?.group?.toString()
            artifactId = "versions-supabase"
        }
    }
}
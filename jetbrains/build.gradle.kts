plugins {
    `version-catalog`
    `maven-publish`
}

repositories {
    mavenCentral()
}

catalog {
    versionCatalog {
        from(files("libs.versions.toml"))
    }
}

publishing {
    publications {
        create<MavenPublication>("versionCatalog") {
            from(components["versionCatalog"])
            groupId = project.parent?.group?.toString()
            artifactId = "versions-jetbrains"
        }
    }
}
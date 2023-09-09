plugins {
    `version-catalog`
    `maven-publish`
}

group = "team.credible.gradle"
version = "1.0-SNAPSHOT"

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
            artifactId = "versions-supabase"
        }
    }
}
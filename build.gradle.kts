import java.net.URI

plugins {
    id("org.gradle.version-catalog")
    id("org.gradle.maven-publish")
}

publishing {
    repositories {
        maven {
            name = "GitHubPackages"
            url = URI.create("https://maven.pkg.github.com/Savrov/grade-version-catalogs")
            credentials {
                username = System.getenv("GITHUB_ACTOR")
                password = System.getenv("GITHUB_TOKEN")
            }
        }
    }
    publications {
        create<MavenPublication>("versionCatalog") {
            from(components["versionCatalog"])
            groupId = project.group.toString()
            artifactId = project.name
        }
    }
}
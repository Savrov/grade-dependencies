import java.net.URI

group = extra["project-group"] as String

plugins {
    id("org.gradle.version-catalog")
    id("org.gradle.maven-publish")
}

subprojects {
    apply(plugin = "org.gradle.version-catalog")
    apply(plugin = "org.gradle.maven-publish")

    catalog {
        versionCatalog {
            from(files("libs.versions.toml"))
        }
    }

    publishing {
        repositories {
            maven {
                name = "GitHubPackages"
                url = URI.create("https://maven.pkg.github.com/Savrov/grade-dependencies")
                credentials {
                    username = System.getenv("GITHUB_ACTOR")
                    password = System.getenv("GITHUB_TOKEN")
                }
            }
        }
    }

}
import java.net.URI

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
                url = URI.create("https://maven.pkg.github.com/Savrov/grade-version-catalogs")
                credentials {
                    username = System.getenv("GITHUB_ACTOR")
                    password = System.getenv("GITHUB_TOKEN")
                }
            }
        }
    }

}
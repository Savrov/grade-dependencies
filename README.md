# Gradle Versions Catalog
SSOT of all dependencies that are required pro KMP projects.

## Compatibility

TODO

## Usage

1. Add to your local environment variables `GITHUB_ACTOR` and `GITHUB_TOKEN` with your GitHub username and
   [personal access token](https://docs.github.com/en/github/authenticating-to-github/creating-a-personal-access-token)
   with `read:packages` scope.

2. Add the following to your `settings.gradle.kts` file:

```
dependencyResolutionManagement {
    repositories {
        maven {
            url = uri("https://maven.pkg.github.com/Savrov/grade-version-catalogs")
            credentials {
                username = System.getenv("GITHUB_ACTOR")
                password = System.getenv("GITHUB_TOKEN")
            }
        }
    }
    versionCatalogs {
        create("libslibsVersionCatalog") {
            from("com.github.savrov.gradle:version-catalogs:${extra["gradle-versions"]}")
        }
        // and other required modules
    }
}
```

3. Sync project and use version catalog in your `build.gradle.kts` file:

```
plugins {
    alias(libsVersionCatalog.plugins.library)
    alias(libsVersionCatalog.plugins.kotlin.multiplatform)
    alias(libsVersionCatalog.plugins.compose)
}
```

```
dependencies {
    api(libsVersionCatalog.kotlin.dateTime)
}
```
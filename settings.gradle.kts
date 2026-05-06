pluginManagement {
    includeBuild("build-logic")
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Kindl"

include(":app")

// core
include(":core:designsystem")
include(":core:common")
include(":core:navigation")
include(":core:network")
include(":core:localstorage")

// data
include(":data")

// domain
include(":domain")

// presentation
include(":presentation:main")


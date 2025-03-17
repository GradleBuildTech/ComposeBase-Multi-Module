pluginManagement {
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

rootProject.name = "ComposeBase"
include(":app")
include(":core")
include(":navigation")
include(":network")
include(":guard")
include(":local")
include(":feat")
include(":gradleConfig")
include(":data")
include(":domain")
include(":feat:auth")
include(":room")

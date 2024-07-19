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

rootProject.name = "LPG Admin"
include(":app")
include(":core")
include(":entities")
include(":framework")
include(":analytics")
include(":home")
include(":addproducts")
include(":orders")
include(":productdetails")
include(":products")
include(":common")
include(":di")
include(":login")

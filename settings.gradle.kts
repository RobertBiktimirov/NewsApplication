pluginManagement {
    repositories {
        google()
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
rootProject.name = "NewsApplication"
include(":app")
include(":feature")
include(":core")
include(":feature:main")
include(":feature:details")
include(":core:ui")
include(":core:api")
include(":core:localStorage")
include(":core:di")
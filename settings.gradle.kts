pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }

//    resolutionStrategy {
//        eachPlugin {
//            if (requested.id.id.startsWith("com.android")) {
//                useModule(Dependencies.Android.androidGradlePlugin)
//            }
//            if (requested.id.id.startsWith("org.jetbrains.kotlin")) {
//                useVersion(Dependencies.Kotlin.kotlinVersion)
//            }
//        }
//    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "ZulipMessenger"
include(":app")
 
plugins {
    id(Dependencies.Android.androidPlugin) apply false
    id(Dependencies.Android.androidLibPlugin) apply false
    id(Dependencies.Kotlin.kotlinPlugin) apply false
    id(Dependencies.Kotlin.Ksp.plugin) apply false
    id(Dependencies.Navigation.navSafeArgsPlugin) version Dependencies.Versions.navigationSafeArgsVersion apply false
}

buildscript {
    dependencies {
        classpath("com.android.tools:r8:8.1.56")
        classpath("com.android.tools.build:gradle:8.1.0")
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}

true
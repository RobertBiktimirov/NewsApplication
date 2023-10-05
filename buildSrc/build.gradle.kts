plugins {
    `kotlin-dsl`
}

repositories {
    google()
    mavenLocal()
    mavenCentral()
}

dependencies {
    implementation(Dependencies.Kotlin.kotlinGradlePlugin)
    implementation(Dependencies.Android.androidGradlePlugin)
    implementation(Dependencies.Kotlin.Serialization.kotlinSerializationPlugin)
    implementation(Dependencies.Kotlin.Ksp.gradlePlugin)
}

kotlin {
    sourceSets.getByName("main").kotlin.srcDir("buildSrc/src/main/kotlin")
}
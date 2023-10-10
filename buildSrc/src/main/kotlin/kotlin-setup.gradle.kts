import gradle.kotlin.dsl.accessors._ebc6ebed417e37ababfcd1a44a1f1fb9.implementation

plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
}

java {
    sourceCompatibility = ProjectConfig.javaVersion
    targetCompatibility = ProjectConfig.javaVersion
}

dependencies {
    implementation(Dependencies.Coroutines.android)
}
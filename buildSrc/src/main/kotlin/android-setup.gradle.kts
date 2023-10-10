import Dependencies.Coroutines.android
import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties
import org.gradle.api.Action
import org.gradle.api.Project
import org.gradle.kotlin.dsl.DependencyHandlerScope


import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
    id("kotlinx-serialization")
    kotlin("kapt")
}

android {
    compileSdk = ProjectConfig.compileSdk
    namespace = ProjectConfig.namespace
    buildToolsVersion = ProjectConfig.buildToolsVersion
    defaultConfig {
        minSdk = ProjectConfig.minSdk
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    buildFeatures {
        viewBinding = true
    }

    compileOptions {
        sourceCompatibility = ProjectConfig.javaVersion
        targetCompatibility = ProjectConfig.javaVersion
    }
    kotlinOptions {
        jvmTarget = ProjectConfig.jvmTarget
    }
}


dependencies {
    implementation(Dependencies.Android.coreKtx)
    implementation(Dependencies.Android.appcompat)
    implementation(Dependencies.Android.material)
    implementation(Dependencies.Coroutines.android)
    implementation(Dependencies.Dagger.dependency)
    kapt(Dependencies.Dagger.compiler)
}

kapt {
    correctErrorTypes = true
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = ProjectConfig.jvmTarget
    }
}
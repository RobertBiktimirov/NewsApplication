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
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Dependencies.Compose.compilerVersion
    }

    compileOptions {
        sourceCompatibility = ProjectConfig.javaVersion
        targetCompatibility = ProjectConfig.javaVersion
    }
    kotlinOptions {
        jvmTarget = ProjectConfig.jvmTarget
    }
}

//afterEvaluate {
//    android.libraryVariants.forEach { variant ->
//        val variantCapped = variant.name.replaceFirstChar { it.uppercaseChar() }
//        val generateSafeArgsTask =
//            tasks.findByPath(":app:generateSafeArgs${variant.name.replaceFirstChar { it.uppercaseChar() }}")
//        val kotlinCompileTask =
//            tasks.findByName("compile${variantCapped}Kotlin") as org.jetbrains.kotlin.gradle.tasks.KotlinCompile
//        kotlinCompileTask.dependsOn(generateSafeArgsTask)
//    }
//}


dependencies {
    implementation(Dependencies.Android.coreKtx)
    implementation(Dependencies.Android.appcompat)
    implementation(Dependencies.Android.material)

    implementation(Dependencies.Coroutines.android)

    implementation(Dependencies.Dagger.dependency)
    kapt(Dependencies.Dagger.compiler)

    implementation(Dependencies.Compose.ui)
    implementation(Dependencies.Compose.ui_tooling)
    implementation(Dependencies.Compose.runtime)
    implementation(Dependencies.Compose.activity)
    implementation(Dependencies.Compose.preview)
    implementation(Dependencies.Compose.material)
    implementation(Dependencies.Compose.material3)
    implementation(Dependencies.Compose.compiler)
    implementation(Dependencies.Compose.statusbar)
    implementation(Dependencies.Android.lifecycleCompose)
    implementation(Dependencies.Compose.pager)
    implementation(Dependencies.Compose.pagerIndicator)

    testImplementation(Dependencies.Testing.junit)
    androidTestImplementation(Dependencies.Testing.testJunit)
}

kapt {
    correctErrorTypes = true
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = ProjectConfig.jvmTarget
    }
}
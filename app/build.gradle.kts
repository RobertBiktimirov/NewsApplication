plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
    id("kotlinx-serialization")
    kotlin("kapt")
}

android {
    compileSdk = ProjectConfig.compileSdk
    namespace = ProjectConfig.namespace

    defaultConfig {
        applicationId = "ru.sample.zulipmessenger"
        minSdk = ProjectConfig.minSdk
        targetSdk = ProjectConfig.targetSdk
        versionCode = ProjectConfig.versionCode
        versionName = ProjectConfig.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
    compileOptions {
        sourceCompatibility = ProjectConfig.javaVersion
        targetCompatibility = ProjectConfig.javaVersion
    }

    kotlinOptions {
        jvmTarget = ProjectConfig.jvmTarget
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Dependencies.Compose.compilerVersion
    }
    
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(project(":feature:auth"))

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
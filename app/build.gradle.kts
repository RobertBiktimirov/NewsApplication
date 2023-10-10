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
        applicationId = ProjectConfig.applicationId
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
        viewBinding = true
    }
    
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(project(":core:api"))
    implementation(project(":core:localStorage"))
    implementation(project(":core:di"))

    implementation(Dependencies.Android.coreKtx)
    implementation(Dependencies.Android.appcompat)
    implementation(Dependencies.Android.material)

    implementation(Dependencies.Coroutines.android)

    implementation(Dependencies.Dagger.dependency)
    kapt(Dependencies.Dagger.compiler)

    implementation(Dependencies.Room.ktx)

    implementation(Dependencies.Kotlin.Serialization.json)

    implementation(Dependencies.Network.retrofit)
    implementation(Dependencies.Network.okHttp)
    implementation(Dependencies.Network.serializer)
    implementation(Dependencies.Network.loggingInterceptor)
}
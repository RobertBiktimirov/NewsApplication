object Dependencies {
    object Versions {
        const val kotlin = "1.9.0"
        const val detektVersion = "1.23.0"
        const val navigationSafeArgsVersion = "2.6.0"
        const val googleServicesVersion = "4.3.15"
        const val firebaseBomVersion = "32.2.2"
        const val coroutinesVersion = "1.7.2"
    }

    object Kotlin {
        const val kotlinVersion = "1.9.0"
        const val kotlinGradlePlugin =
            "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion"
        const val kotlinPlugin = "org.jetbrains.kotlin.android"

        object Serialization {
            const val version = "1.5.1"
            const val kotlinSerializationPlugin =
                "org.jetbrains.kotlin:kotlin-serialization:$$kotlinVersion"
            const val core =
                "org.jetbrains.kotlinx:kotlinx-serialization-core:$version"
            const val json =
                "org.jetbrains.kotlinx:kotlinx-serialization-json:$version"
        }

        object Ksp {
            private const val version = "1.9.0-1.0.12"
            const val plugin = "com.google.devtools.ksp"
            const val gradlePlugin =
                "com.google.devtools.ksp:com.google.devtools.ksp.gradle.plugin:$version"
        }
    }

    object Android {
        const val androidGradlePlugin = "com.android.tools.build:gradle:8.1.0"
        const val androidPlugin = "com.android.application"
        const val androidLibPlugin = "com.android.library"
        const val coreKtx = "androidx.core:core-ktx:1.10.1"
        const val appcompat = "androidx.appcompat:appcompat:1.6.1"
        const val material = "com.google.android.material:material:1.9.0"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:2.1.4"
        private const val lifecycleVersion = "2.6.1"
        const val viewModelCompose =
            "androidx.lifecycle:lifecycle-viewmodel-compose:$lifecycleVersion"
        const val viewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion"
        const val runtimeKtx = "androidx.lifecycle:lifecycle-runtime-ktx:$lifecycleVersion"
        const val lifecycleCompose = "androidx.lifecycle:lifecycle-runtime-compose:$lifecycleVersion"
        const val fragmentKtx = "androidx.fragment:fragment-ktx:1.6.1"
        const val lifecycleExtensions = "androidx.lifecycle:lifecycle-extensions:2.2.0"
    }

    object Network {
        const val retrofit = "com.squareup.retrofit2:retrofit:2.9.0"
        const val okHttp = "com.squareup.okhttp3:okhttp:5.0.0-alpha.11"
        const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:4.11.0"
        const val serializer =
            "com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:0.8.0"
    }

    object Room {
        private const val version = "2.5.2"
        const val runtime = "androidx.room:room-runtime:$version"
        const val compiler = "androidx.room:room-compiler:$version"
        const val ktx = "androidx.room:room-ktx:$version"
    }

    object Coroutines {
        const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutinesVersion}"
    }

    object DataStore {
        private const val datastoreVersion = "1.1.0-alpha04"
        const val datastore = "androidx.datastore:datastore:$datastoreVersion"
        const val preferencesDatastore =
            "androidx.datastore:datastore-preferences:$datastoreVersion"
    }

    object Navigation {

    }

    object Dagger {
        private const val version = "2.46.1"
        const val dependency = "com.google.dagger:dagger:$version"
        const val compiler = "com.google.dagger:dagger-compiler:$version"
    }
}
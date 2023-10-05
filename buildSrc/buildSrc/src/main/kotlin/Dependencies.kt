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

    object Compose {
        private const val version = "1.4.3"
        const val compilerVersion = "1.5.0"
        const val ui = "androidx.compose.ui:ui:$version"
        const val ui_tooling = "androidx.compose.ui:ui-tooling:$version"
        const val material = "androidx.compose.material:material:$version"
        const val material3 = "androidx.compose.material3:material3:1.0.1"
        const val preview = "androidx.compose.ui:ui-tooling-preview:$version"
        const val runtime = "androidx.compose.runtime:runtime:$version"
        const val compiler = "androidx.compose.compiler:compiler:$compilerVersion"
        const val activity = "androidx.activity:activity-compose:1.7.2"
        const val statusbar = "com.google.accompanist:accompanist-systemuicontroller:0.30.1"
        const val coil = "io.coil-kt:coil-compose:2.0.0-rc01"
        const val pager = "com.google.accompanist:accompanist-pager:0.13.0"
        const val pagerIndicator = "com.google.accompanist:accompanist-pager-indicators:0.13.0"
    }

    object Network {
        const val retrofit = "com.squareup.retrofit2:retrofit:2.9.0"
        const val converterScalars = "com.squareup.retrofit2:converter-scalars:2.9.0"
        const val okHttp = "com.squareup.okhttp3:okhttp:5.0.0-alpha.11"
        const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:4.11.0"
        const val serializer =
            "com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:0.8.0"
        const val gson =
            "com.squareup.retrofit2:converter-gson:2.9.0"
        const val scalars =
            "com.squareup.retrofit2:converter-scalars:2.9.0"
    }

    object Picasso {
        const val picasso = "com.squareup.picasso:picasso:2.8"
    }

    object YandexDiv {
        private const val version = "27.0.0"
        const val div = "com.yandex.div:div:$version"
        const val divCore = "com.yandex.div:div-core:$version"
        const val divJson = "com.yandex.div:div-json:$version"
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

    object Utils {
        const val splashScreen = "androidx.core:core-splashscreen:1.0.1"
        const val glide = "com.github.skydoves:landscapist-glide:1.5.0"
    }

    object Testing {
        const val junit = "junit:junit:4.13.2"
        const val testJunit = "androidx.test.ext:junit:1.1.5"
        const val espressoCore = "androidx.test.espresso:espresso-core:3.5.1"
        const val mockk = "io.mockk:mockk:1.13.7"
        const val coroutinesTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutinesVersion}"
        const val turbine = "app.cash.turbine:turbine:1.0.0"
    }
}
plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
    id("kotlinx-serialization")
    kotlin("kapt")
    id("androidx.navigation.safeargs.kotlin")
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

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            jvmTarget = ProjectConfig.jvmTarget
        }
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

afterEvaluate {
    android.applicationVariants.forEach { variant ->
        val copyNavFromAppToNavigationModuleTask =
            tasks.findByName("copyNavFromAppToNavigationModule")
        val variantCapped = variant.name.replaceFirstChar { it.uppercaseChar() }
        val generateSafeArgsTask =
            tasks.findByName("generateSafeArgs$variantCapped")
        val removeNavFromAppModuleTask =
            tasks.findByName("removeNavFromAppModule")
        val kotlinCompileTask =
            tasks.findByName("compile${variantCapped}Kotlin") as org.jetbrains.kotlin.gradle.tasks.KotlinCompile

        generateSafeArgsTask?.finalizedBy(copyNavFromAppToNavigationModuleTask)
        kotlinCompileTask.dependsOn(copyNavFromAppToNavigationModuleTask)
        kotlinCompileTask.dependsOn(generateSafeArgsTask)
        kotlinCompileTask.finalizedBy(removeNavFromAppModuleTask)
    }
}

dependencies {

    implementation(project(":core:api"))
    implementation(project(":core:localStorage"))
    implementation(project(":core:di"))
    implementation(project(":core:utils"))
    implementation(project(":feature:main"))
    implementation(project(":feature:details"))
    implementation(project(":feature:splash"))
    implementation(project(":navigation"))

    implementation(Dependencies.Android.coreKtx)
    implementation(Dependencies.Android.appcompat)
    implementation(Dependencies.Android.material)

    implementation(Dependencies.Coroutines.android)

    implementation(Dependencies.Dagger.dependency)
    implementation(Dependencies.Navigation.fragmentKtx)
    implementation(Dependencies.Navigation.uiKtx)

    kapt(Dependencies.Dagger.compiler)

    implementation(Dependencies.Room.ktx)

    implementation(Dependencies.Kotlin.Serialization.json)

    implementation(Dependencies.Network.retrofit)
    implementation(Dependencies.Network.okHttp)
    implementation(Dependencies.Network.serializer)
    implementation(Dependencies.Network.loggingInterceptor)
}

tasks.register("copyNavFromAppToNavigationModule") {
    val inputArgsPath = "/build/generated/source/navigation-args"
    val outputArgsPath = "/build/generated/nav"
    val appNavigation = "${project(":app").projectDir.path}$inputArgsPath"
    val navigationPath = "${project(":navigation").projectDir.path}$outputArgsPath"
    val navigationPackage = "ru.intercommunication.newsapplication.navigation"
    val appPackage = "ru.intercommunication.newsapplication"
    doLast {
        fileTree(appNavigation)
            .filter { it.isFile && it.name.contains("Directions") }
            .forEach { file ->
                if (file.exists()) {
                    val lines = file.readLines().toMutableList()
                    if ("import ${navigationPackage}.R" !in lines) {
                        lines.add(2, "import ${navigationPackage}.R")
                    }
                    if ("package $navigationPackage" !in lines) {
                        lines.replaceAll { line ->
                            line.replace("package $appPackage", "package $navigationPackage")
                        }
                    }
                    file.writeText(lines.joinToString("\n"))
                }
            }

        val debugAppNavigation = "$appNavigation/debug"
        val releaseAppNavigation = "$appNavigation/release"
        val pathFrom = if (file(debugAppNavigation).exists()) debugAppNavigation
        else releaseAppNavigation

        copy {
            from(pathFrom)
            into(navigationPath)
        }
    }
}

tasks.register("removeNavFromAppModule") {
    val inputArgsPath = "/build/generated/source/navigation-args"
    val appNavigation = "${project(":app").projectDir.path}$inputArgsPath"
    val inputArgsPath2 =
        "/build/tmp/kotlin-classes/release/ru/intercommunication/newsapplication/navigation"
    val appNavigation2 = "${project(":app").projectDir.path}$inputArgsPath2"
    doLast {
        val folder = File(appNavigation)
        if (folder.exists()) {
            folder.deleteRecursively()
        }
        val folder2 = File(appNavigation2)
        if (folder2.exists()) {
            folder2.deleteRecursively()
        }
    }
}
import org.gradle.api.JavaVersion

object ProjectConfig {
    const val namespace = "ru.intercommunication.newsapplication"
    fun namespace(name: String? = null) = "$namespace.$name"

    const val compileSdk = 33
    const val applicationId = namespace
    const val minSdk = 26
    const val targetSdk = 33
    const val versionCode = 1
    const val versionName = "1.0"
    const val buildToolsVersion  = "33.0.2"

    val javaVersion = JavaVersion.VERSION_17
    const val jvmTarget = "17"
}
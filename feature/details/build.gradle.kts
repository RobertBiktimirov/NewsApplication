plugins {
    id("android-setup")
    id("org.jetbrains.kotlin.android")
}
android {
    namespace = ProjectConfig.namespace("feature.details")
}

dependencies {
    implementation(Dependencies.Navigation.fragmentKtx)
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
}
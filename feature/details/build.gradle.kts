plugins {
    id("android-setup")
    id(Dependencies.Kotlin.kotlinParcelize)
}
android {
    namespace = ProjectConfig.namespace("feature.details")
}

dependencies {


    implementation(project(":core:localStorage"))
    implementation(project(":core:di"))
    implementation(project(":core:utils"))
    implementation(project(":navigation"))

    implementation(Dependencies.Utils.Glide.glide)
    implementation(Dependencies.Android.viewModelKtx)
    implementation(Dependencies.Android.runtimeKtx)
    implementation(Dependencies.Android.fragmentKtx)
    implementation(Dependencies.Navigation.fragmentKtx)
}
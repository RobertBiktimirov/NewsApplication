plugins {
    id("android-setup")
}
android {
    namespace = ProjectConfig.namespace("feature.main")
}

dependencies {
    implementation(project(":core:localStorage"))
    implementation(project(":core:api"))
    implementation(project(":core:di"))
    implementation(project(":core:utils"))
    implementation(project(":navigation"))

    implementation(Dependencies.Utils.Glide.glide)
    implementation(Dependencies.Android.viewModelKtx)
    implementation(Dependencies.Android.runtimeKtx)
    implementation(Dependencies.Android.fragmentKtx)
    implementation(Dependencies.Navigation.fragmentKtx)
}
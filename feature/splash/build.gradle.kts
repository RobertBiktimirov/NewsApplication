plugins {
    id("android-setup")
    id(Dependencies.Navigation.navSafeArgsPlugin)
}
android {
    namespace = ProjectConfig.namespace("feature.splash")
}

dependencies {
    implementation(project(":navigation"))
    implementation(project(":core:di"))

    implementation(Dependencies.Navigation.fragmentKtx)
}
plugins {
    id("android-setup")
    id(Dependencies.Navigation.navSafeArgsPlugin)
}

android {
    namespace = ProjectConfig.namespace("navigation")
    sourceSets["main"].java.srcDir(file("build/generated/nav"))
}

dependencies {
    implementation(Dependencies.Navigation.fragmentKtx)
}
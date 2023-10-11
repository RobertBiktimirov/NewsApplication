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
}
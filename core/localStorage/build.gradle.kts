plugins {
    id("android-setup")
}

android {
    namespace = ProjectConfig.namespace("core.localStorage")
}

dependencies {
    ksp(Dependencies.Room.compiler)
    implementation(Dependencies.Room.runtime)
    implementation(Dependencies.Room.ktx)
}
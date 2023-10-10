plugins {
    id("android-setup")
}

android {
    namespace = ProjectConfig.namespace("core.api")
}

dependencies {
    implementation(Dependencies.Network.loggingInterceptor)
    implementation(Dependencies.Network.okHttp)
    implementation(Dependencies.Network.retrofit)
    implementation(Dependencies.Network.serializer)
}
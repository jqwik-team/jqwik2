plugins {
    `kotlin-dsl`
}

repositories {
    mavenCentral()
    gradlePluginPortal()
}

dependencies {
    implementation(libs.kotlin.gradlePlugin)
    implementation(libs.kotlin.powerAssertPlugin)
}
plugins {
    id("buildlogic.java-api-conventions")
}

description = "Jqwik2 core module"

dependencies {
    api(libs.apiguardian)
    //implementation(project(":utilities"))

    testImplementation(libs.junit.jupiter)
    testImplementation(libs.assertj.core)
}

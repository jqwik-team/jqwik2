plugins {
    id("buildlogic.kotlin-library-conventions")
}

description = "Jqwik2 Kotlin core support"

val artifactName = "jqwik2-kotlin-core"

dependencies {
    api(libs.apiguardian)
    implementation(libs.kotlin.stdlib)
    implementation(project(":core"))

    testImplementation(libs.kotlin.test.junit5)
    testImplementation(libs.junit.jupiter)
    testImplementation(libs.assertj.core)
}

tasks.jar {
    archiveBaseName.set(artifactName)
    archiveVersion.set("${rootProject.extra.get("jqwik2Version")}")
    manifest {
        attributes("Automatic-Module-Name" to "net.jqwik2.kotlin.core")
    }
}

publishLibrary {
    artifactId = artifactName
}


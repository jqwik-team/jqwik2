plugins {
    id("buildlogic.kotlin-library-conventions")
    kotlin("jvm") version "2.0.21"
}

description = "Jqwik2 Kotlin core support"

val artifactName = "jqwik2-kotlin-core"

dependencies {
    api(libs.apiguardian)
    implementation(project(":core"))

    testImplementation(libs.junit.jupiter)
    testImplementation(libs.assertj.core)
}

kotlin {
    jvmToolchain(21)
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


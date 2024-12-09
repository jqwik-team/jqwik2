plugins {
    id("buildlogic.java-api-conventions")
    id("buildlogic.java-library-conventions")
}

description = "Jqwik2 core module"

val artifactName = "jqwik2-core"

dependencies {
    api(libs.apiguardian)
    //implementation(project(":utilities"))

    testImplementation(libs.junit.jupiter)
    testImplementation(libs.assertj.core)
}

tasks.jar {
    archiveBaseName.set(artifactName)
    archiveVersion.set("${rootProject.extra.get("jqwik2Version")}")
    manifest {
        attributes("Automatic-Module-Name" to "net.jqwik2.core")
    }
}

publishLibrary {
    artifactId = artifactName
}

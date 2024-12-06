plugins {
    id("buildlogic.java-common-conventions")
    `java-library`
    `maven-publish`
    signing
}

java {
    withJavadocJar()
    withSourcesJar()
}

tasks.withType<Javadoc> {
    (options as StandardJavadocDocletOptions).addStringOption("Xdoclint:none", "-quiet")
}

val jqwik2Version: String = "${rootProject.extra.get("jqwik2Version")}"
val isSnapshotRelease = jqwik2Version.endsWith("SNAPSHOT")

publishing {
    repositories {
        maven {
            // hint: credentials are in ~/.gradle/gradle.properties
            val repoUsername: String = project.findProperty("tokenUsername") as? String ?: ""
            val repoPassword: String = project.findProperty("tokenPassword") as? String ?: ""

            credentials {
                username = repoUsername
                password = repoPassword
            }

            val releasesRepoUrl = "https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/"
            val snapshotsRepoUrl = "https://s01.oss.sonatype.org/content/repositories/snapshots/"
            url = uri(if (isSnapshotRelease) { snapshotsRepoUrl } else { releasesRepoUrl })
        }
    }
}

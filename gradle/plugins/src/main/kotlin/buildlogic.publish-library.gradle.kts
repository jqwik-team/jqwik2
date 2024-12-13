plugins {
    `maven-publish`
    signing
}

val jqwik2Version: String = "${rootProject.extra.get("jqwik2Version")}"
val isSnapshotRelease = jqwik2Version.endsWith("SNAPSHOT")


interface PublishLibraryExtension {
    val artifactId: Property<String>
}

val publishLibraryExtension = project.extensions.create<PublishLibraryExtension>("publishLibrary")
publishLibraryExtension.artifactId.convention("artifactId-not-set")

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
            url = uri(
                if (isSnapshotRelease) {
                    snapshotsRepoUrl
                } else {
                    releasesRepoUrl
                }
            )
        }
    }
    publications {
        create<MavenPublication>("jqwikModule") {
            groupId = "net.jqwik"
            artifactId = "placeholder"
            from(components["java"])
            pom {
                version = jqwik2Version
                groupId = "net.jqwik"
                name = artifactId
                description = "$description"
                url = "https://github.org/jqwik-team/jqwik2"
                licenses {
                    license {
                        name = "Eclipse Public License - v 2.0"
                        url = "http://www.eclipse.org/legal/epl-v20.html"
                    }
                }
                developers {
                    developer {
                        id = "jlink"
                        name = "Johannes Link"
                        email = "business@johanneslink.net"
                    }
                }
                scm {
                    connection = "scm:git:git://github.com/jqwik-team/jqwik2.git"
                    developerConnection = "scm:git:git://github.com/jqwik-team/jqwik2.git"
                    url = "https://github.com/jqwik-team/jqwik2"
                }
            }
            afterEvaluate {
                artifactId = publishLibraryExtension.artifactId.get()
            }
        }
    }
}

signing {
    if (!isSnapshotRelease) {
        sign(publishing.publications["jqwikModule"])
    }
}
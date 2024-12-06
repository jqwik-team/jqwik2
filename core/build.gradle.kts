plugins {
    id("buildlogic.java-api-conventions")
}

description = "Jqwik2 core module"

val jqwik2Version: String = "${rootProject.extra.get("jqwik2Version")}"
val isSnapshotRelease = isSnapshotRelease(jqwik2Version)
val artifactName = "jqwik2-core"

fun isSnapshotRelease(versionString: String): Boolean {
    return versionString.endsWith("SNAPSHOT")
}

dependencies {
    api(libs.apiguardian)
    //implementation(project(":utilities"))

    testImplementation(libs.junit.jupiter)
    testImplementation(libs.assertj.core)
}

tasks.jar {
    archiveBaseName.set("jqwik2-core")
    archiveVersion.set("${rootProject.extra.get("jqwik2Version")}")
    manifest {
        attributes("Automatic-Module-Name" to "net.jqwik2.core")
    }
}

publishing {
    publications {
        create<MavenPublication>("jqwik2Core") {
            groupId = "net.jqwik"
            artifactId = artifactName
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
        }
    }
}

signing {
    if (!isSnapshotRelease) {
        sign(publishing.publications["jqwik2Core"])
    }
}

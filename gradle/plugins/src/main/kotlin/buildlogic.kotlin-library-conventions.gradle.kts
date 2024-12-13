import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    java
    `java-library`
    id("buildlogic.publish-library")
    id("kotlin")
}

repositories {
    mavenCentral()
    maven {
        url = uri("https://s01.oss.sonatype.org/content/repositories/snapshots")
    }
}

dependencies {
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.named<Test>("test") {
    useJUnitPlatform {
        includeEngines("junit-jupiter")
    }

    include("**/*Example.class")
    include("**/*Examples.class")
    include("**/*Test.class")
    include("**/*Tests.class")

    reports {
        junitXml.required = true
    }

    testLogging.showStandardStreams = true
}

val javaVersion: String = System.getProperty("matrix.version") ?: "21"
val kotlinCompilerArgs = listOf(
    "-Xnullability-annotations=@org.jspecify.annotations:strict",
    "-Xemit-jvm-type-annotations" // Required for annotations on type variables
)

tasks.withType<KotlinCompile> {
    compilerOptions {
        freeCompilerArgs.set(kotlinCompilerArgs)
        javaParameters = true // Required to get correct parameter names in reporting
        jvmTarget = JvmTarget.fromTarget(javaVersion)
    }
}

// Currently Kotlin only supports up to Java 22.
// To allow Kotlin being built on newer Java versions, we need to set the target version for the Java libs to max 22
val jvmToolchainVersion = javaVersion.toInt()

kotlin {
    jvmToolchain(jvmToolchainVersion)
}

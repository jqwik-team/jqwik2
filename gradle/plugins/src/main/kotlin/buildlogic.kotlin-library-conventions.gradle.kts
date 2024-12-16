import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

fun alias(alias: String): Provider<MinimalExternalModuleDependency> {
    return versionCatalogs.named("libs").findLibrary(alias).orElseThrow()
}

plugins {
    `java-library`
    id("buildlogic.publish-library")
    kotlin("jvm")
    kotlin("plugin.power-assert")
}

repositories {
    mavenCentral()
    maven {
        url = uri("https://s01.oss.sonatype.org/content/repositories/snapshots")
    }
}

@OptIn(ExperimentalKotlinGradlePluginApi::class)
powerAssert {
    functions = listOf("kotlin.assert", "kotlin.test.assertTrue", "kotlin.test.assertEquals", "kotlin.test.assertNull")
}

dependencies {
    testRuntimeOnly(alias("junit.platform.launcher"))
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

val kotlinCompilerArgs = listOf(
    "-Xnullability-annotations=@org.jspecify.annotations:strict",
    "-Xemit-jvm-type-annotations" // Required for annotations on type variables
)

// Kotlin toolchain target is always Java 21 regardless of the actual Java version

tasks.withType<KotlinCompile> {
    compilerOptions {
        freeCompilerArgs.set(kotlinCompilerArgs)
        javaParameters = true // Required to get correct parameter names in reporting
        jvmTarget = JvmTarget.JVM_21
    }
}

kotlin {
    jvmToolchain(21)
}


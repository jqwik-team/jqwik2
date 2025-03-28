import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `java-library`
    id("buildlogic.publish-library")
    id("buildlogic.repositories")
    id("buildlogic.use-junit-platform")
    kotlin("jvm")
    kotlin("plugin.power-assert")
}

@OptIn(ExperimentalKotlinGradlePluginApi::class)
powerAssert {
    functions = listOf("kotlin.assert", "kotlin.test.assertTrue", "kotlin.test.assertEquals", "kotlin.test.assertNull")
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


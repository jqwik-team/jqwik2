plugins {
    java
    id("buildlogic.repositories")
    id("buildlogic.use-junit-platform")
}

val javaVersion: String = System.getProperty("matrix.version") ?: "21"
val javaVendor: String = System.getProperty("matrix.vendor") ?: ""

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(javaVersion)
        vendor = JvmVendorSpec.matching(javaVendor)
    }
    // jqwik2 has Java 21 as its base version
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

tasks.compileTestJava {
    // Enable source parameter names through reflection
    options.compilerArgs.add("-parameters")
}
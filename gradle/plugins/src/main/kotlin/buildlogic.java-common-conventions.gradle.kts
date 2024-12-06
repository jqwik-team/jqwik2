plugins {
    java
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

val javaVersion: String = System.getProperty("matrix.version") ?: "21"
val javaVendor: String = System.getProperty("matrix.vendor") ?: ""

// println("Using Java version: $javaVersion - $javaVendor")

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(javaVersion)
        vendor = JvmVendorSpec.matching(javaVendor)
    }

}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

tasks.compileTestJava {
    // Enable source parameter names through reflection
    options.compilerArgs.add("-parameters")
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

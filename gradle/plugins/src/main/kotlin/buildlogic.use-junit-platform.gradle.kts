plugins {
    java
}

fun alias(alias: String): Provider<MinimalExternalModuleDependency> {
    return versionCatalogs.named("libs").findLibrary(alias).orElseThrow()
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

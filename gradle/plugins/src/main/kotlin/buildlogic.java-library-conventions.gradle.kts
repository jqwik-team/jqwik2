plugins {
    id("buildlogic.java-common-conventions")
    `java-library`
}

java {
    withJavadocJar()
    withSourcesJar()
}
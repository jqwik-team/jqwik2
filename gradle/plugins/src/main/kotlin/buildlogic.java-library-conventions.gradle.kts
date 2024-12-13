plugins {
    id("buildlogic.java-common-conventions")
    id("buildlogic.publish-library")
    `java-library`
}

java {
    withJavadocJar()
    withSourcesJar()
}

tasks.withType<Javadoc> {
    (options as StandardJavadocDocletOptions).addStringOption("Xdoclint:none", "-quiet")
    isFailOnError = false
    include("**/api/**")
}

plugins {
    `java-library`
    `maven-publish`
    id("cesspool-java-conventions")
}

dependencies {
    api(project(":papi-replacer"))
    api(project(":yaml-commands"))
}
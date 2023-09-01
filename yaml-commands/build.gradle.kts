plugins {
    id("cesspool-java-conventions")
    id("cesspool-spigot-api-dependency")
}

dependencies {
    api(project(":papi-replacer"))
}
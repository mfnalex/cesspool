plugins {
    id("cesspool-java-conventions")
    id("cesspool-javadoc-conventions")
    id("cesspool-spigot-api-dependency")
}

dependencies {
    api(project(":shared"))
    api(project(":core"))
}
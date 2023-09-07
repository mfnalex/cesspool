plugins {
    id("cesspool-java-conventions")
    id("cesspool-javadoc-conventions")
    id("cesspool-spigot-api-dependency")
    //id("io.github.patrick.remapper") // TODO: Make this work HERE and not in every single submodule
}

repositories {
    mavenLocal()
    gradlePluginPortal()
}

dependencies {
    api(project(":nms:generic"))
}
plugins {
    id("cesspool-java-conventions")
    id("cesspool-javadoc-conventions")
    id("cesspool-spigot-api-dependency")
    //id("cesspool-lib-module")
}

dependencies {
    api(project(":shared"))
    api(project(":nms:generic"))
    api(project(":nms:versions:v1.20.1"))
}

group = "$group.nms"
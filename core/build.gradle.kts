plugins {
    id("cesspool-java-conventions")
    id("cesspool-javadoc-conventions")
    id("cesspool-spigot-api-dependency")
    //id("cesspool-lib-module")
}

dependencies {
    api(project(":shared"))
    api(project(":nms:aggregator"))
    api(project(":nms:generic"))
}
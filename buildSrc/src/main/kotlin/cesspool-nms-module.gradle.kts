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
    //api(project(":shared"))
    api(project(":nms:generic"))
    //compileOnly("io.github.patrick.remapper")
    //compileOnly("io.github.patrick-choe:mojang-spigot-remapper:1.4.0")
}


//
//@Suppress("UNCHECKED_CAST")
//fun useMojangMaps(spigotVersion: String) {
//    dependencies {
//        compileOnly("org.spigotmc:spigot-api:${spigotVersion}-R0.1-SNAPSHOT:remapped-mojang")
//    }
//
//    tasks.getByName("remap").configure<DefaultTask> {
//        val taskVersionProperty = properties["version"] as Property<String>
//        taskVersionProperty.set(spigotVersion)
//    }
//}

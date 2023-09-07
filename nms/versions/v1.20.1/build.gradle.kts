plugins {
    id("cesspool-nms-module")
    id("io.github.patrick.remapper") // TODO: Move this to the cesspool-nms-module plugin
}

dependencies {
    compileOnly("org.spigotmc:spigot:1.20.1-R0.1-SNAPSHOT:remapped-mojang")
}

tasks {
    remap {
        version.set("1.20.1")
    }
    jar {
        finalizedBy("remap")
    }
}

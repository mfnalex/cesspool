import java.io.FileFilter

//fun includeSub(name: String)  {
//    val moduleFolders: Array<File> = file(name).listFiles(FileFilter { it.isDirectory })
//        ?: throw RuntimeException("Couldn't find subprojects folder $name")
//
//    for(moduleFolder in moduleFolders) {
//        val moduleName: String = moduleFolder.name
//        include(moduleName)
//        project(":${moduleName}").projectDir = moduleFolder
//    }
//}

pluginManagement {
    repositories {
        gradlePluginPortal()
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.7.0"
    id("io.github.patrick.remapper") version "1.4.0" apply false
}

rootProject.name = "cesspool"

include("shared")
include("core")

// Import subprojects from modules folder
val moduleFolders: Array<File> = file("modules").listFiles(FileFilter { it.isDirectory })
    ?: throw RuntimeException("Couldn't find modules folder")

for(moduleFolder in moduleFolders) {
    val moduleName: String = moduleFolder.name
    include(moduleName)
    project(":${moduleName}").projectDir = moduleFolder
}

include("nms:generic")
include("nms:aggregator")
include("nms:versions:v1.20.1")
pluginManagement {
    repositories {
        gradlePluginPortal()
//        /*maven {
//            url = uri("https://repo.jeff-media.com/public")
//        }*/
        mavenLocal()
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.7.0"
}

rootProject.name = "cesspool"

include("shared")
include("core")
include("config")
include("papi-replacer")
include("yaml-commands")
include("cooldown")
include("mcversion")

include("reflection")

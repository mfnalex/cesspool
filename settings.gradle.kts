pluginManagement {
    repositories {
        gradlePluginPortal()
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
plugins {
    `java-library`
    `maven-publish`
    id("cesspool-java-conventions")
//    id("io.freefair.aggregate-javadoc") version "8.3"
//    id("io.freefair.aggregate-javadoc") version "8.3"
}

allprojects {
    repositories {
        mavenCentral()
        gradlePluginPortal()

        maven {
            name = "spigotmc"
            url = uri("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
        }

        maven {
            name = "extendedclip"
            url = uri("https://repo.extendedclip.com/content/repositories/placeholderapi/")
        }
    }
}

dependencies {
    // Option 1: List projects explicitly
    api(project(":papi-replacer"))
    api(project(":yaml-commands"))

    //Option 2: Add all java projects automatically
//    rootProject.subprojects.forEach { subproject ->
//        subproject.plugins.withId("java") {
//            javadoc(subproject)
//        }
//    }
}

tasks.register<Javadoc>("allJavadoc") {
    description = "Generates javadoc for all projects"
    group = "documentation"

    rootProject.subprojects.forEach { subproject ->
        subproject.plugins.withId("java") {
            subproject.tasks.withType<Javadoc>().forEach { javadoc ->
                source(javadoc.source)
                classpath += javadoc.classpath
            }
        }
    }
}
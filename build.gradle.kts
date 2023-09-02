plugins {
    `java-library`
    `maven-publish`
    id("cesspool-java-conventions")
    id("cesspool-javadoc-conventions")
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
    // Depend on all subprojects that use java
    rootProject.subprojects.forEach { subproject ->
        subproject.plugins.withId("java") {
            api(subproject)
        }
    }
}


val allJavadoc = tasks.register<Javadoc>("allJavadoc") {
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

    //finalizedBy("fixAllJavadocs")
}.get()


//tasks.register<FixJavadocTask>("fixAllJavadocs") {
//    description = "Fixes double javadoc annotations"
//    group = "documentation"
//
//    directory.set(allJavadoc.destinationDir)
//}

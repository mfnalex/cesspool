import com.jeff_media.fixjavadoc.FixJavadoc

plugins {
    `java-library`
    `maven-publish`
    id("cesspool-java-conventions")
    id("cesspool-javadoc-conventions")
    id("com.jeff-media.fix-javadoc-plugin") version ("1.19")
    id("io.github.patrick.remapper") version "1.4.0" apply false
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

allprojects {
    tasks.withType<FixJavadoc>().configureEach {
        newLineOnMethodParameters.set(true)
        keepOriginal.set(false)
        hideExternalLinksIcon.set(true)
    }
}
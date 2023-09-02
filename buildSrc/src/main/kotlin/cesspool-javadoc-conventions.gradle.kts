import com.jeff_media.cesspool.gradle.tasks.FixJavadoc

plugins {
    id("cesspool-java-conventions")
}

java {
    withJavadocJar()
    withSourcesJar()
}

tasks.withType<Javadoc> {
    val options = options as StandardJavadocDocletOptions
    options.links(
        "https://hub.spigotmc.org/javadocs/spigot/",
        "https://javadoc.io/doc/org.jetbrains/annotations/24.0.1"
    )
    finalizedBy("fixJavadoc")
}

tasks.register<FixJavadoc>("fixJavadoc") {
    description = "Fixes double javadoc annotations"
    group = "documentation"
    directory.set(tasks.getByName<Javadoc>("javadoc").destinationDir)
}